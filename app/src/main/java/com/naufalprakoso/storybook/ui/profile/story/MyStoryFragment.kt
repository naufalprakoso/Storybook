package com.naufalprakoso.storybook.ui.profile.story

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Story
import com.naufalprakoso.storybook.ui.story.detail.DetailStoryActivity
import kotlinx.android.synthetic.main.fragment_post.view.*

class MyStoryFragment : Fragment() {

    private lateinit var adapter: StoryAdapter
    private val stories = arrayListOf<Story>()

    companion object {
        fun newInstance(): Fragment {
            return MyStoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)

        adapter = StoryAdapter {
            val intent = Intent(context, DetailStoryActivity::class.java)
            intent.putExtra(Const.STORY_KEY, it)
            startActivity(intent)
        }
        view.rv_stories.setHasFixedSize(true)
        view.rv_stories.layoutManager = GridLayoutManager(context, 2)
        view.rv_stories.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadStories()
    }

    private fun loadStories() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

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
