package com.example.utils.popup.listener

/**
 *   @author Jose Alejandro Ojalvo
 *   @since 02/02/2021
 *   @email jojalvo@topdoctors.es
 *
 *   Listener designed to handle the popup buttons behaviour, this must be implemented on the
 *   class that implements a popup and the buttons should have custom actions.
 */
interface PopUpListener {

    fun onPositiveButtonClick()

    fun onNegativeButtonClick()

    fun onItemClick(text: String)

}