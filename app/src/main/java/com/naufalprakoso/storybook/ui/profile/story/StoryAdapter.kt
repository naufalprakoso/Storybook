package com.naufalprakoso.storybook.ui.profile.story

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.Story
import kotlinx.android.synthetic.main.item_my_story.view.*

class StoryAdapter : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private val stories = ArrayList<Story>()

    fun setStories(stories: List<Story>) {
        this.stories.addAll(stories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_my_story,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = stories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(stories[position])
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(story: Story) {
            Glide.with(itemView.context)
                .load(story.featuredImage)
                .into(itemView.img_story)
        }
    }

}