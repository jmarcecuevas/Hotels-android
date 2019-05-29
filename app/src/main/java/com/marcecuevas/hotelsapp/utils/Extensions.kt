package com.marcecuevas.hotelsapp.utils

import android.content.Context
import android.widget.TextView
import com.marcecuevas.hotelsapp.view.fragment.MainFragment
import uk.co.chrisjenx.calligraphy.CalligraphyUtils

fun TextView.fontVariable(context: Context, variable: FontVariable){
    CalligraphyUtils.applyFontToTextView(context,this,Font.instance.pathForVariable(variable))
}