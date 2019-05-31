package com.marcecuevas.hotelsapp.view.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.AmenityDTO
import com.marcecuevas.hotelsapp.data.model.DTO.GeolocationDTO
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDetailDTO
import com.marcecuevas.hotelsapp.data.model.DTO.ReviewDTO
import com.marcecuevas.hotelsapp.utils.FontVariable
import com.marcecuevas.hotelsapp.utils.fontVariable
import com.marcecuevas.hotelsapp.view.adapter.AmenitiesAdapter
import com.marcecuevas.hotelsapp.view.adapter.CommentsAdapter
import com.marcecuevas.hotelsapp.view.adapter.HotelAdapter
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import kotlinx.android.synthetic.main.fragment_hotel_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HotelDetailFragment: GenericFragment(), KodeinAware, OnMapReadyCallback{

    override val kodein by closestKodein()
    private val viewModelFactory: HotelViewModelFactory by instance()

    var hotelID: String? = null
    private lateinit var adapter: CommentsAdapter
    private lateinit var aminitiesAdapter: AmenitiesAdapter
    private var googleMap: GoogleMap? = null

    override fun layout(): Int {
        return R.layout.fragment_hotel_detail
    }

    override fun init() {
        getArgs()

        startObserving()

        styleViews()

        startMap()
    }

    private fun startObserving(){
        val viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HotelViewModel::class.java)

        hotelID?.let { viewModel.getHotelDetail(it) }

        viewModel.hotelDetail.observe(this, Observer {
            it?.let {
                setupView(it)
            }
        })
    }

    private fun startMap(){
        fragmentMap.onCreate(null)
        fragmentMap.onResume()
        fragmentMap.getMapAsync(this)
    }

    private fun styleViews(){
        context?.let {
            nameTextView.fontVariable(it, FontVariable.bold)
            ratingTextView.fontVariable(it, FontVariable.bold)
            ratingLabel.fontVariable(it, FontVariable.bold)
            reviewsLabel.fontVariable(it, FontVariable.bold)
            amenitiesLabel.fontVariable(it, FontVariable.bold)
            placeLabel.fontVariable(it,FontVariable.bold)
            priceBtn.fontVariable(it, FontVariable.bold)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(hotel: HotelDetailDTO) {
        priceBtn.text = "${hotel.price.currency.mask} ${hotel.price.best}"

        with(hotel.hotel){
            context?.let {
                Glide.with(it).load(mainPicture).into(imageview)
                starsRatingBar.rating = rating.toFloat()
                nameTextView.text = name
                placeTextView.text = "${city.name}, ${city.country.name}"

                descriptionTextView.addShowMoreText(getString(R.string.showMore))
                descriptionTextView.addShowLessText(getString(R.string.showLess))
                descriptionTextView.setShowLessTextColor(R.color.colorPrimaryDark)
                descriptionTextView.setShowMoreColor(R.color.colorPrimaryDark)
                descriptionTextView.setShowingLine(3)
                descriptionTextView.text = description


                ratingTextView.text = rating.toString()
                fullPlaceTextView.text = "${address}, ${city.name}, ${city.country.name}"

                setupComments(reviews)

                setupAmenities(amenities)

                setupMap(geoLocation)
            }
        }
    }

    private fun setupAmenities(amenities: List<AmenityDTO>){
        context?.let {
            aminitiesAdapter = AmenitiesAdapter(it)
            amenitiesRecyclerView.layoutManager = GridLayoutManager(it,2)
            amenitiesRecyclerView.adapter = aminitiesAdapter
            amenitiesRecyclerView.isNestedScrollingEnabled = false
            aminitiesAdapter.loadItems(amenities)
        }
    }

    private fun setupMap(location: GeolocationDTO){
        val lat = location.latitude.toDouble()
        val lng = location.longitude.toDouble()

        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap?.addMarker(MarkerOptions().position(LatLng(lat,lng)))
        val cameraposition = CameraPosition.builder().target(LatLng(lat,lng)).zoom(12f).bearing(0f).build()
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraposition))
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

    override fun onMapReady(map: GoogleMap?) {
        this.googleMap = map
        MapsInitializer.initialize(context)
    }
}