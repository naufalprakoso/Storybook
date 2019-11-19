package com.naufalprakoso.storybook.ui.story.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(
    private val callback: (Comment) -> Unit
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val comments = arrayListOf<Comment>()

    fun setComments(comments: ArrayList<Comment>) {
        this.comments.addAll(comments)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comment,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(comments[position], callback)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(comment: Comment, callback: (Comment) -> Unit) {

            println("LogDetailAdapter: $comment")

            itemView.txt_username.text = comment.username
            itemView.txt_comment.text = comment.comment
            itemView.txt_datetime.text = comment.datetime.substring(0, 10)

            itemView.setOnClickListener {
                callback(comment)
            }
        }
    }
}