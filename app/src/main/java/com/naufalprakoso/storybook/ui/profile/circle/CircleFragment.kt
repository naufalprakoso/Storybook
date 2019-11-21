package com.naufalprakoso.storybook.ui.profile.circle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.naufalprakoso.storybook.R

class CircleFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return CircleFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_circle, container, false)
    }

}
