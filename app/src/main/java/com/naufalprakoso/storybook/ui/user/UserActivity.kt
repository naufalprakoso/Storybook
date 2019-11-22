package com.naufalprakoso.storybook.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Story
import com.naufalprakoso.storybook.model.User
import com.naufalprakoso.storybook.ui.story.detail.DetailStoryActivity
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var adapter: StoryAdapter
    private val stories = arrayListOf<Story>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        adapter = StoryAdapter {
            val intent = Intent(this, DetailStoryActivity::class.java)
            intent.putExtra(Const.STORY_KEY, it)
            startActivity(intent)
        }
        rv_stories.setHasFixedSize(true)
        rv_stories.layoutManager = GridLayoutManager(this, 2)
        rv_stories.adapter = adapter

        val userId = intent.getStringExtra(Const.USER_UID_KEY)
        userId?.let {
            loadUserProfile(it)
            loadStories(it)
        }
    }

    private fun loadUserProfile(userId: String) {
        FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("id", userId).limit(1).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    user = it.result?.documents?.get(0)?.toObject(User::class.java) ?: User()
                    txt_name.text = user.name
                    txt_username.text = getString(R.string.profile_username, user.username)
                }
            }
    }

    private fun loadStories(userId: String) {
        FirebaseFirestore.getInstance().collection("stories")
            .whereEqualTo("userId", userId).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result?.documents!!) {
                        val id = document.id
                        val story = document.toObject(Story::class.java)
                        story?.id = id
                        story?.let { it1 -> stories.add(it1) }
                    }

                    adapter.setStories(stories)
                    adapter.notifyDataSetChanged()
                }
            }
    }
}
