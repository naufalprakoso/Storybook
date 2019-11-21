package com.naufalprakoso.storybook.ui.story.add.frame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.data.Const
import com.naufalprakoso.storybook.model.Frame
import com.naufalprakoso.storybook.ui.story.add.image.ResultImageActivity
import kotlinx.android.synthetic.main.activity_choose_frame.*

class ChooseFrameActivity : AppCompatActivity() {

    private lateinit var adapter: FrameAdapter
    private val frames = arrayListOf<Frame>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_frame)

        adapter = FrameAdapter {
            val intent = Intent(this, ResultImageActivity::class.java)
            intent.putExtra(Const.FRAME_KEY, it)
            startActivity(intent)
        }
        rv_frames.setHasFixedSize(true)
        rv_frames.layoutManager = GridLayoutManager(this, 2)
        rv_frames.adapter = adapter

        loadFrames()

        btn_back.setOnClickListener {
            finish()
        }
    }

    private fun loadFrames() {
        FirebaseFirestore.getInstance().collection("frames")
            .orderBy("datetime", Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result?.documents!!) {
                        val frame = document.toObject(Frame::class.java)
                        frame?.let { it1 -> frames.add(it1) }
                    }

                    adapter.setFrame(frames)
                    adapter.notifyDataSetChanged()
                }
            }
    }
}
