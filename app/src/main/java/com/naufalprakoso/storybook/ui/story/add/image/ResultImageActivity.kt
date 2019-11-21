package com.naufalprakoso.storybook.ui.story.add.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Frame
import kotlinx.android.synthetic.main.activity_result_image.*

class ResultImageActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_image)

        val frame = intent?.getParcelableExtra<Frame>(Const.FRAME_KEY)

        Glide.with(this).load(frame?.image).into(img_result)
        initialButton()

        btn_choose_image.setOnClickListener(this)
        btn_add_story.setOnClickListener(this)
        btn_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> finish()
            R.id.btn_choose_image -> {

            }
            R.id.btn_add_story -> {

            }
        }
    }

    private fun initialButton() {
        btn_add_story.visibility = View.GONE
        btn_choose_image.visibility = View.VISIBLE
    }

    private fun finalButton() {
        btn_add_story.visibility = View.VISIBLE
        btn_choose_image.visibility = View.GONE
    }
}
