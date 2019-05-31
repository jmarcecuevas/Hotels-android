package com.marcecuevas.hotelsapp.view.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.DTO.ReviewDTO
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.fontVariable
import com.marcecuevas.hotelsapp.view.adapter.CommentsAdapter
import com.marcecuevas.hotelsapp.view.adapter.HotelAdapter
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_hotel_detail.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_hotel.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HotelDetailFragment: GenericFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HotelViewModelFactory by instance()
    private lateinit var viewModel: HotelViewModel

    var hotelID: String? = null
    private lateinit var adapter: CommentsAdapter

    override fun layout(): Int {
        return R.layout.fragment_hotel_detail
    }

    override fun init() {
        getArgs()

        styleViews()

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        hotelID?.let { viewModel.getHotelDetail(it) }

        viewModel.hotelDetail.observe(this, Observer {
            it?.let {
                setupView(it)
            }
        })
    }

    private fun styleViews(){
        context?.let {
            nameTextView.fontVariable(it, FontVariable.bold)
            ratingTextView.fontVariable(it, FontVariable.bold)
            ratingLabel.fontVariable(it, FontVariable.bold)
            reviewsLabel.fontVariable(it, FontVariable.bold)
        }
        nameTextView.textSize = 16f
        placeTextView.textSize = 14f
        descriptionTextView.textSize = 14f
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(hotel: HotelDetailDTO) {
        with(hotel.hotel){
            context?.let {
                Glide.with(it).load(mainPicture).into(imageview)
                starsRatingBar.rating = rating.toFloat()
                nameTextView.text = name
                placeTextView.text = "${city.name}, ${city.country.name}"
                descriptionTextView.text = description
                ratingTextView.text = rating.toString()

                setupComments(reviews)
            }
        }
    }

    private fun setupComments(reviews: List<ReviewDTO>){
        context?.let {
            adapter = CommentsAdapter(it)
            commentsRecyclerView.layoutManager = LinearLayoutManager(context)
            commentsRecyclerView.adapter = adapter
            commentsRecyclerView.isNestedScrollingEnabled = false
            adapter.loadItems(reviews)
        }
    }

    fun getArgs(){
        hotelID = arguments?.let {
            HotelDetailFragmentArgs.fromBundle(it).hotelId }
    }

}