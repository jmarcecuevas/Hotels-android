package com.marcecuevas.hotelsapp.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_hotel_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HotelDetailFragment: GenericFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HotelViewModelFactory by instance()
    private lateinit var viewModel: HotelViewModel

    var hotelID: String? = null

    override fun layout(): Int {
        return R.layout.fragment_hotel_detail
    }

    override fun init() {
        getArgs()

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        hotelID?.let { viewModel.getHotelDetail(it) }

        viewModel.hotelDetail.observe(this, Observer {
            it?.let {
                setupView(it)
            }
        })
    }

    private fun setupView(hotel: HotelDetailDTO) {
        with(hotel.hotel){
            context?.let {
                Glide.with(it).load(mainPicture).into(imageview)
                starsRatingBar.rating = rating.toFloat()
                nameTextView.text = name

                descriptionTextView.text = description
                descriptionTextView.setTrimMode(0)
                descriptionTextView.setTrimLines(2)
            }
        }
    }

    fun getArgs(){
        hotelID = arguments?.let {
            HotelDetailFragmentArgs.fromBundle(it).hotelId }
    }

}