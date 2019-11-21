package com.naufalprakoso.storybook.ui.story.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.ui.story.add.frame.ChooseFrameActivity
import kotlinx.android.synthetic.main.fragment_add_story.view.*

class AddStoryFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return AddStoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_story, container, false)

        val imgBg =
            "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/bg%2Fadd_story.jpeg?alt=media&token=7631028c-a045-49bf-9192-681e5ba63460"
        val imgBg2 =
            "https://firebasestorage.googleapis.com/v0/b/storybook-c8071.appspot.com/o/bg%2Fadd_story_2.jpeg?alt=media&token=1923e298-341f-42ee-b94e-ba4f6df5f18c"
        context?.let { Glide.with(it).load(imgBg).into(view.img_bg_story) }
        context?.let { Glide.with(it).load(imgBg2).into(view.img_bg_story_2) }

        view.btn_add_story.setOnClickListener {
            val intent = Intent(context, ChooseFrameActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}
