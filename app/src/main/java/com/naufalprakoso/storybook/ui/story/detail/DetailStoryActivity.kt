package com.naufalprakoso.storybook.ui.story.detail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Comment
import com.naufalprakoso.storybook.model.Story
import kotlinx.android.synthetic.main.activity_detail_story.*
import kotlinx.android.synthetic.main.content_detail_story.*

class DetailStoryActivity : AppCompatActivity() {

    private lateinit var adapter: CommentAdapter
    private var story: Story? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        story = intent.getParcelableExtra(Const.STORY_KEY)

        Glide.with(this).load(story?.featuredImage).into(img_main)

        txt_username.text = story?.username
        txt_datetime.text = story?.datetime?.substring(0, 10)
        txt_likes.text = getString(R.string.story_likes, story?.likes)
        txt_title.text = story?.title

        adapter = CommentAdapter {

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
}
