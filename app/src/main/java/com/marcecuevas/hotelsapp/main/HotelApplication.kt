package com.marcecuevas.hotelsapp.main

import android.app.Application
import com.marcecuevas.hotelsapp.data.repository.HotelRepository
import com.marcecuevas.hotelsapp.data.repository.HotelRepositoryImpl
import com.jakewharton.threetenabp.AndroidThreeTen
import com.marcecuevas.hotelsapp.data.network.*
import com.marcecuevas.hotelsapp.viewModel.HotelViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class HotelApplication : Application(),KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@HotelApplication))

        bind() from singleton { HotelREST(instance()) }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance())}
        bind<HotelNetworkDataSource>() with singleton { HotelNetworkDataSourceImpl(instance()) }
        bind<HotelRepository>() with singleton { HotelRepositoryImpl(instance()) }
        bind() from provider { HotelViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}