package com.example.weatherapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by joseojalvo on 2020-08-20
 */

/**
 * Makes a view visible
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Disables a view
 */
fun View.gone() {
    this.visibility = View.GONE
}

/**
 * Inflates a layout
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}


