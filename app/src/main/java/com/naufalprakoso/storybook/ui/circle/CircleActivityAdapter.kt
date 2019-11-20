package com.naufalprakoso.storybook.ui.circle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.CircleActivity
import kotlinx.android.synthetic.main.item_circle_activity.view.*

class CircleActivityAdapter : RecyclerView.Adapter<CircleActivityAdapter.ViewHolder>() {

    private val activities = arrayListOf<CircleActivity>()

    fun setActivities(activities: ArrayList<CircleActivity>) {
        this.activities.addAll(activities)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_circle_activity,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(activities[position])
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(activity: CircleActivity) {
            itemView.txt_date.text = activity.datetime
            itemView.txt_username.text = itemView.context.getString(R.string.profile_username, activity.username)
            itemView.txt_activity.text = activity.activity
        }
    }

}