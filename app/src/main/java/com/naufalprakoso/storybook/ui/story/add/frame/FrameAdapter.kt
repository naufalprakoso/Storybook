package com.naufalprakoso.storybook.ui.story.add.frame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.Frame
import kotlinx.android.synthetic.main.item_frame.view.*

class FrameAdapter(
    private val callback: (Frame) -> Unit
) : RecyclerView.Adapter<FrameAdapter.ViewHolder>() {

    private val frames = arrayListOf<Frame>()

    fun setFrame(frames: ArrayList<Frame>) {
        this.frames.addAll(frames)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_frame, parent, false))

    override fun getItemCount(): Int = frames.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(frames[position], callback)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(frame: Frame, callback: (Frame) -> Unit) {
            Glide.with(itemView.context).load(frame.image).into(itemView.img_frame)
            itemView.txt_title.text = frame.title

            itemView.setOnClickListener {
                callback(frame)
            }
        }
    }

}