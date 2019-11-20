package com.naufalprakoso.storybook.ui.story.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.naufalprakoso.storybook.R
import com.naufalprakoso.storybook.model.Image
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_image_slider.view.*

class ImageSliderAdapter : SliderViewAdapter<ImageSliderAdapter.ViewHolder>() {

    private val images = arrayListOf<Image>()

    fun setImages(images: ArrayList<Image>) {
        this.images.addAll(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_image_slider,
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder?.bindItem(images[position], position.plus(1), images.size)
    }

    override fun getCount(): Int = images.size

    class ViewHolder(v: View) : SliderViewAdapter.ViewHolder(v) {
        private val imgSlider: ImageView = v.iv_auto_image_slider
        private val txtSlider: TextView = v.tv_auto_image_slider
        private val context = v.context

        fun bindItem(image: Image, position: Int, arrSize: Int) {
            Glide.with(context).load(image.image).into(imgSlider)
            txtSlider.text = context.getString(R.string.slider_position, position, arrSize)
        }
    }

}