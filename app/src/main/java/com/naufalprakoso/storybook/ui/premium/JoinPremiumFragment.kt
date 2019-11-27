package com.naufalprakoso.storybook.ui.premium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.naufalprakoso.storybook.R

class JoinPremiumFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return JoinPremiumFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_join_premium, container, false)
        return view
    }

}
