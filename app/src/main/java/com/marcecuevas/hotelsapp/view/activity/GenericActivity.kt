package com.marcecuevas.hotelsapp.view.activity

import android.app.AlertDialog
import android.os.Bundle
import com.marcecuevas.hotelsapp.R
import com.marcecuevas.hotelsapp.data.model.Error
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marcecuevas.hotelsapp.view.fragment.GenericFragment

abstract class GenericActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout())
        init()
    }

    abstract fun layout(): Int

    abstract fun init()

    abstract fun fragmentLayoutID(): Int

    fun addFragmentWithBackStack(myNewFragmentClass: Class<out GenericFragment>, withBackstack: Boolean): Fragment {
        val myNewFragment: Fragment = Fragment.instantiate(applicationContext, myNewFragmentClass.name)
        val newFragment = myNewFragment.javaClass.name
        val t = supportFragmentManager.beginTransaction()
        if (withBackstack) {
            t.setCustomAnimations(
                R.anim.pull_in_right,
                R.anim.push_out_left,
                R.anim.pull_in_left,
                R.anim.push_out_right
            )
            t.addToBackStack(newFragment)
        }
        t.add(fragmentLayoutID(), myNewFragment, newFragment)
        t.commit()
        return myNewFragment
    }

    fun replaceFragmentWithBackStack(myNewFragmentClass: Class<out GenericFragment>, withBackstack: Boolean?, bundle: Bundle?): Fragment {
        val myNewFragment = Fragment.instantiate(applicationContext, myNewFragmentClass.name)
        val newFragment = myNewFragment.javaClass.name
        val t = supportFragmentManager.beginTransaction()
        myNewFragment.arguments = bundle
        if (withBackstack!!) {
            t.setCustomAnimations(
                R.anim.pull_in_right,
                R.anim.push_out_left,
                R.anim.pull_in_left,
                R.anim.push_out_right
            )
            t.addToBackStack(newFragment)
        }
        t.replace(fragmentLayoutID(), myNewFragment, newFragment)
        t.commit()
        return myNewFragment
    }

    fun replaceFragmentWithBackStack(myNewFragmentClass: Class<out GenericFragment>, withBackstack: Boolean?): Fragment {
        return this.replaceFragmentWithBackStack(myNewFragmentClass, withBackstack, null)
    }

    fun showError(error: Error){
        AlertDialog.Builder(this)
            .setTitle(error.title)
            .setMessage(error.message)
            .setCancelable(false)
            .setPositiveButton("OK"){dialog,which ->
                dialog.dismiss()
            }
            .create().show()
    }
}