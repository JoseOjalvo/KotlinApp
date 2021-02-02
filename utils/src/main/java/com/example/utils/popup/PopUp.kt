package com.example.utils.popup

import android.app.AlertDialog
import android.content.Context
import com.example.utils.popup.listener.PopUpListener


/**
 *   @author Jose Alejandro Ojalvo
 *   @since 02/02/2021
 *   @email jojalvo@topdoctors.es
 *
 *   Handles popup behaviour, being an object allows an easier implementation in the required
 *   classes, all utils should be like this.
 */
object PopUp {

// =================================================================================================
// Pop Up methods
// =================================================================================================

    fun showDialogInformation(
            title: String,
            message: String,
            positiveButton: String,
            context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButton) { _, _ ->
            //Nothing to do
        }

        builder.show()
    }

    fun showDialogPositiveNegativeButton(
            title: String,
            message: String,
            negativeButton: String,
            positiveButton: String,
            context: Context,
            popUpListener: PopUpListener?
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeButton) { _, _ ->
            popUpListener?.onNegativeButtonClick()
        }
        builder.setPositiveButton(positiveButton) { _, _ ->
            popUpListener?.onPositiveButtonClick()
        }

        builder.show()
    }

    fun showDialogPositiveNegativeButton(
            title: String,
            message: String,
            negativeButton: String,
            positiveButton: String,
            context: Context,
            positiveFunction: () -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeButton) { _, _ ->
            Unit
        }
        builder.setPositiveButton(positiveButton) { _, _ ->
            positiveFunction()
        }

        builder.show()
    }
}