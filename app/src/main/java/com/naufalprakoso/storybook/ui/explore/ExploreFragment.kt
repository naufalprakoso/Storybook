package com.naufalprakoso.storybook.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.Story
import kotlinx.android.synthetic.main.fragment_explore.view.*

class ExploreFragment : Fragment() {

    private lateinit var adapter: ExploreAdapter

    companion object {
        fun newInstance(): Fragment {
            return ExploreFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        adapter = ExploreAdapter {

        }
        view.rv_stories.setHasFixedSize(true)
        view.rv_stories.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        view.rv_stories.adapter = adapter

        return view
    }

    private fun loadData() {
        val stories = ArrayList<Story>()
        val storage = FirebaseFirestore.getInstance().collection("")
    }

}
