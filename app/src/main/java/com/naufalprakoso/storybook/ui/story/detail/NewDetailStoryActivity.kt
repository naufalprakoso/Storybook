package com.naufalprakoso.storybook.ui.story.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Image
import com.naufalprakoso.storybook.model.Story
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.activity_new_detail_story.fab
import kotlinx.android.synthetic.main.activity_new_detail_story.img_slider

class NewDetailStoryActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: ImageSliderAdapter
    private var story: Story? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_detail_story)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black))
            Snackbar.make(it, "Like this story", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        story = intent.getParcelableExtra(Const.STORY_KEY)

        sliderAdapter = ImageSliderAdapter()
        img_slider.setIndicatorAnimation(IndicatorAnimations.NONE)
        img_slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        img_slider.scrollTimeInSec = 10
        img_slider.startAutoCycle()
        img_slider.sliderAdapter = sliderAdapter

        loadImages()
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
