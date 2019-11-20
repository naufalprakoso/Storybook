package com.naufalprakoso.storybook.ui.story.detail

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Comment
import com.naufalprakoso.storybook.model.Image
import com.naufalprakoso.storybook.model.Story
import com.naufalprakoso.storybook.ui.user.UserActivity
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.activity_detail_story.*
import kotlinx.android.synthetic.main.content_detail_story.*

class DetailStoryActivity : AppCompatActivity() {

    private lateinit var adapter: CommentAdapter
    private lateinit var sliderAdapter: ImageSliderAdapter
    private var story: Story? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { view ->
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black))
        }

        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange: Int? = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout?.totalScrollRange
                }

                if (scrollRange?.plus(verticalOffset) == 0) {
                    toolbar_layout.title = getString(R.string.title_activity_detail_story)
                    isShow = true
                } else if (isShow) {
                    toolbar_layout.title = " "
                    isShow = false
                }
            }
        })

        story = intent.getParcelableExtra(Const.STORY_KEY)

        txt_username.text = story?.username
        txt_datetime.text = story?.datetime?.substring(0, 10)
        txt_likes.text = getString(R.string.story_likes, story?.likes)
        txt_title.text = story?.title

        sliderAdapter = ImageSliderAdapter()
        img_slider.setIndicatorAnimation(IndicatorAnimations.NONE)
        img_slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        img_slider.scrollTimeInSec = 10
        img_slider.startAutoCycle()
        img_slider.sliderAdapter = sliderAdapter

        loadImages()

        adapter = CommentAdapter {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra(Const.USER_UID_KEY, it.userId)
            startActivity(intent)
        }
        rv_comments.setHasFixedSize(true)
        rv_comments.layoutManager = LinearLayoutManager(this)
        rv_comments.adapter = adapter

        loadComments()
    }

    private fun loadComments() {
        val comments = arrayListOf<Comment>()

        FirebaseFirestore.getInstance().collection("stories").document(story?.id.toString())
            .collection("comments")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result?.documents!!) {
                        val comment = document.toObject(Comment::class.java)
                        comment?.let { it1 -> comments.add(it1) }
                    }

                    adapter.setComments(comments)
                    adapter.notifyDataSetChanged()
                }
            }
    }

    private fun loadImages() {
        val images = arrayListOf<Image>()

        FirebaseFirestore.getInstance().collection("stories").document(story?.id.toString())
            .collection("images")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (!it.result?.isEmpty!!) {
                        for (document in it.result?.documents!!) {
                            val image = document.toObject(Image::class.java)
                            image?.let { it1 -> images.add(it1) }
                        }

                        sliderAdapter.setImages(images)
                        sliderAdapter.notifyDataSetChanged()
                    } else {
                        images.add(Image(story?.featuredImage!!))
                        sliderAdapter.setImages(images)
                        sliderAdapter.notifyDataSetChanged()
                    }
                }
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
