package com.naufalprakoso.storybook.ui.story


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalprakoso.storybook.R

class AddStoryFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return AddStoryFragment();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_story, container, false)
    }


}
