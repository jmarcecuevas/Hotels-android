package com.marcecuevas.hotelsapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R

class SliderViewPager(private val context : Context) : PagerAdapter() {

    private var layoutInflater : LayoutInflater? = null
    val images = arrayOf(R.drawable.four,
                                    R.drawable.three,R.drawable.five, R.drawable.paris)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view ===  `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater?.let {
            it.inflate(R.layout.item_swiper_image,null)
        }

        val image = view?.findViewById<View>(R.id.imageview) as ImageView

        Glide.with(context).load(images[position]).into(image)

        val viewPager = container as ViewPager
        viewPager.addView(view,0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}