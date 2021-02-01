package com.example.presentation.ui.features.activities.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *   @author Jose Alejandro Ojalvo
 *   @since 01/02/2021
 *   @email jojalvo@topdoctors.es
 *
 *   Extend this binding activity to use view binding. View binding is a feature that allows you to
 *   more easily write code that interacts with views. Once view binding is enabled in a module,
 *   it generates a binding class for each XML layout file present in that module. An instance
 *   of a binding class contains direct references to all views that have an ID in the
 *   corresponding layout.
 *   In most cases, view binding replaces findViewById.
 **/
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

// =================================================================================================
// Binding
// =================================================================================================

    protected lateinit var binding: VB

// =================================================================================================
// Attach Binding
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = this.setBinding(layoutInflater)
        setContentView(this.binding.root)
    }

// =================================================================================================
// To override Binding
// =================================================================================================

    /**
     * Use this method to inflate the biding
     * @param inflater the layout inflater
     * @return the fragment activity
     */
    abstract fun setBinding(inflater: LayoutInflater): VB
}