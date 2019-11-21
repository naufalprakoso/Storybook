package com.naufalprakoso.storybook.ui.profile.circle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.User
import kotlinx.android.synthetic.main.item_user_circle.view.*

class CircleAdapter(
    private val callback: (User) -> Unit
) : RecyclerView.Adapter<CircleAdapter.ViewHolder>() {

    private val users = arrayListOf<User>()

    fun setUsers(users: ArrayList<User>) {
        this.users.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_circle,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(users[position], callback)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(user: User, callback: (User) -> Unit) {
            Glide.with(itemView.context).load(R.drawable.story3).into(itemView.img_user)
            itemView.txt_name.text = user.name
            itemView.txt_username.text =
                itemView.context.getString(R.string.profile_username, user.username)

            itemView.setOnClickListener {
                callback(user)
            }
        }
    }

}