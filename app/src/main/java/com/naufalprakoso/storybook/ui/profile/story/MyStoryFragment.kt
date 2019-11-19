package com.naufalprakoso.storybook.ui.profile.story


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.naufalprakoso.storybook.R

class MyStoryFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return MyStoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

}
