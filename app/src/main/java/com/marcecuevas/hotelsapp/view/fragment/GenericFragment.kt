package com.marcecuevas.hotelsapp.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcecuevas.hotelsapp.data.model.Error

abstract class GenericFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(layout(),container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun layout(): Int

    abstract fun init()

    fun showError(error: Error){
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(error.title)
                .setMessage(error.message)
                .setCancelable(false)
                .setPositiveButton("OK"){dialog,which ->
                    dialog.dismiss()
                }
                .create().show()
        }
    }
}