package com.naufalprakoso.storybook.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.Story
import kotlinx.android.synthetic.main.item_my_story.view.*

class ExploreAdapter(
    private val callback: (Story) -> Unit
) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    private val stories = arrayListOf<Story>()

    fun setStories(stories: ArrayList<Story>) {
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
        holder.bindItem(stories[position], callback)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(story: Story, callback: (Story) -> Unit) {
            Glide.with(itemView.context)
                .load(story.featuredImage)
                .into(itemView.img_story)
            itemView.txt_title.text = story.title
            itemView.txt_likes.text = itemView.context.getString(R.string.story_likes, story.likes)

            itemView.card_img.setOnClickListener {
                callback(story)
            }
        }
    }

}