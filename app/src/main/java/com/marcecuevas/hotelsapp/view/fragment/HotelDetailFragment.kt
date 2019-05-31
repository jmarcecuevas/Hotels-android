package com.marcecuevas.hotelsapp.view.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.DTO.*
import com.marcecuevas.hotelsapp.utils.*
import com.marcecuevas.hotelsapp.view.adapter.AmenitiesAdapter
import com.marcecuevas.hotelsapp.view.adapter.CommentsAdapter
import com.marcecuevas.hotelsapp.view.adapter.HotelAdapter
import com.marcecuevas.hotelsapp.viewModel.HotelViewModel
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import kotlinx.android.synthetic.main.fragment_hotel_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HotelDetailFragment: GenericFragment(), OnMapReadyCallback{

    private val viewModelFactory: HotelViewModelFactory by instance()
    var hotelID: String? = null

    val adapter = CommentsAdapter(context)
    val aminitiesAdapter = AmenitiesAdapter()
    private var googleMap: GoogleMap? = null

    override fun layout(): Int = R.layout.fragment_hotel_detail

    override fun init() {
        showProgress()

        startMap()
        getArgs()
        styleViews()

        startObserving()
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

    private fun styleViews(){
        nameTextView.bold(context)
        ratingLabel.bold(context)
        reviewsLabel.bold(context)
        amenitiesLabel.bold(context)
        placeLabel.bold(context)
        priceBtn.bold(context)
    }

    private fun startMap(){
        fragmentMap.onCreate(null)
        fragmentMap.onResume()
        fragmentMap.getMapAsync(this)
    }

    private fun setupView(hotel: HotelDetailDTO) {
        setupPrice(hotel.price?.currency?.mask,
            hotel.price?.best)

        with(hotel.hotel){

            loadPicture(this?.mainPicture)

            setupName(this?.name)

            setupStars(this?.stars)

            setupAddress(this?.address)

            setupPlace(this?.address,this?.city)

            setupRating(this?.rating)

            setupDescription(this?.description)

            setupMap(this?.geoLocation)

            setupAmenities(this?.amenities)

            setupComments(this?.reviews)

            hideProgress()
        }
    }

    private fun setupDescription(description: String?){
        descriptionTextView.addShowMoreText(getString(R.string.showMore))
        descriptionTextView.addShowLessText(getString(R.string.showLess))
        descriptionTextView.setShowLessTextColor(R.color.colorPrimaryDark)
        descriptionTextView.setShowMoreColor(R.color.colorPrimaryDark)
        descriptionTextView.setShowingLine(3)
        descriptionTextView.text = description
    }

    private fun setupAddress(address: String?){
        placeTextView.text = address
    }

    private fun setupStars(stars: Int?){
        starsRatingBar.numStars = 5
        stars?.let {
            starsRatingBar.rating = it.toFloat()
        }
    }

    private fun setupAmenities(amenities: List<AmenityDTO>?){
        amenitiesRecyclerView.layoutManager = GridLayoutManager(context,2)
        amenitiesRecyclerView.adapter = aminitiesAdapter
        amenitiesRecyclerView.isNestedScrollingEnabled = false
        aminitiesAdapter.loadItems(amenities)
    }

    private fun loadPicture(url: String?){
        context?.let {
            Glide.with(it).load(url).into(imageview)

            imageview.setOnClickListener{_ ->
                navigateToImageViewer(url)
            }
        }
    }

    private fun setupName(name: String?){
        nameTextView.text = name
    }

    private fun setupComments(reviews: List<ReviewDTO>?){
        commentsRecyclerView.layoutManager = LinearLayoutManager(context)
        commentsRecyclerView.adapter = adapter
        commentsRecyclerView.isNestedScrollingEnabled = false
        adapter.loadItems(reviews)
    }

    private fun setupMap(location: GeolocationDTO?){
        val lat = location?.latitude?.toDouble()
        val lng = location?.longitude?.toDouble()

        ifLet(lat,lng){
            (lat,lng) ->
                googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
                googleMap?.addMarker(MarkerOptions().position(LatLng(lat,lng)))
                val cameraposition = CameraPosition.builder().target(LatLng(lat,lng)).zoom(12f).bearing(0f).build()
                googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraposition))
        }
    }

    private fun setupRating(rating: Double?){
        ratingTextView.text = rating.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setupPlace(address: String?, city: CityDTO?){
        fullPlaceTextView.text = "${address}, ${city?.name}, ${city?.country?.name}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupPrice(mask: String?, price: Int?){
        priceBtn.text = "${mask} ${price}"
    }

    fun getArgs(){
        hotelID = arguments?.let {
            HotelDetailFragmentArgs.fromBundle(it).hotelId }
    }

    override fun onMapReady(map: GoogleMap?) {
        this.googleMap = map
        MapsInitializer.initialize(context)
    }

    private fun navigateToImageViewer(url: String?){
        val directions = HotelDetailFragmentDirections.imageViewerFragment(url)
        findNavController().navigate(directions)
    }
}