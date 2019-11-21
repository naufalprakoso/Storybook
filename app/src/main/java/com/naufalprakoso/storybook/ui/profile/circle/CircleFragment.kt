package com.naufalprakoso.storybook.ui.profile.circle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.data.Mock
import com.naufalprakoso.storybook.ui.user.UserActivity
import kotlinx.android.synthetic.main.fragment_user_circle.view.*

class CircleFragment : Fragment() {

    private lateinit var adapter: CircleAdapter

    companion object {
        fun newInstance(): Fragment {
            return CircleFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_circle, container, false)

        adapter = CircleAdapter {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(Const.USER_UID_KEY, it.id)
            startActivity(intent)
        }
        view.rv_users.setHasFixedSize(true)
        view.rv_users.layoutManager = LinearLayoutManager(context)
        view.rv_users.adapter = adapter

        loadUsers()

        return view
    }

    private fun loadUsers() {
        adapter.setUsers(Mock.mockUsers())
        adapter.notifyDataSetChanged()
    }

}
