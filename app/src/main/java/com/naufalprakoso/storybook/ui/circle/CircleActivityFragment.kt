package com.naufalprakoso.storybook.ui.circle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Mock
import kotlinx.android.synthetic.main.fragment_activity_circle.view.*

class CircleActivityFragment : Fragment() {

    private lateinit var adapter: CircleActivityAdapter

    companion object {
        fun newInstance(): Fragment {
            return CircleActivityFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activity_circle, container, false)

        adapter = CircleActivityAdapter()

        view.rv_activities.setHasFixedSize(true)
        view.rv_activities.layoutManager = LinearLayoutManager(context)
        view.rv_activities.adapter = adapter

        loadActivities()

        return view
    }

    private fun loadActivities() {
        adapter.setActivities(Mock.mockCircleActivities())
        adapter.notifyDataSetChanged()
    }

}
