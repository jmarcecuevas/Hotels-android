package com.marcecuevas.hotelsapp.view.fragment

import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R
import kotlinx.android.synthetic.main.fragment_image_viewer.*

class ImageViewerFragment: GenericFragment() {

    override fun layout(): Int = R.layout.fragment_image_viewer

    override fun init() {

        loadImage()
    }

    private fun loadImage(){
        val url = arguments?.let {
            ImageViewerFragmentArgs.fromBundle(it).pictureUrl}
        context?.let {
            Glide.with(it).load(url).into(imageView)
        }
    }
}