package com.example.utils.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.utils.R
import com.example.utils.popup.PopUp
import com.google.android.material.snackbar.Snackbar


/**
 *   @author Jose Alejandro Ojalvo
 *   @since 02/02/2021
 *   @email jojalvo@topdoctors.es
 *
 *   Handles permission actions and behaviours
 */
object PermissionUtils {
    private const val REQUEST_EXTERNAL_STORAGE = 1000
    private const val REQUEST_CAMERA = 2000
    const val REQUEST_LOCATION = 3000

    fun requestExternal(context: Activity) {
        if (!checkExternal(context)) {
            ActivityCompat.requestPermissions(
                context, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    fun requestLocation(context: Activity) {
        if (!checkLocation(context)) {
            ActivityCompat.requestPermissions(
                context, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION
            )
        }
    }

    fun requestCamera(context: Activity) {
        if (!checkCamera(context)) {
            ActivityCompat.requestPermissions(
                context, arrayOf(
                    Manifest.permission.CAMERA
                ),
                REQUEST_CAMERA
            )
        }
    }

    fun checkExternal(context: Context): Boolean {
        return !(ActivityCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
                != PackageManager.PERMISSION_GRANTED)
    }

    fun checkCamera(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkLocation(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Checks if the [permission] we're looking for is already accepted, if not then it will ask
     * the user to gran the permission, if the user denies it and selects "don't ask me again"
     * it will prompt a snackbar (since Toast is deprecated) with an action button to redirect the
     * user to the settings.
     */
    fun checkPermission(
        requestCode: Int,
        grantResults: IntArray,
        context: Context,
        activity: Activity,
        view: View,
        permission: String,
        granFunction: () -> Unit
    ) {
        if (requestCode == 0 && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            granFunction()
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, permission
                )
            ) {
                PopUp.showDialogPositiveNegativeButton(
                    context.getString(R.string.td_dialog_permission_denied_title),
                    context.getString(R.string.td_dialog_permission_denied_body),
                    context.getString(R.string.td_dialog_permission_denied_sure),
                    context.getString(R.string.td_dialog_permission_denied_re_try),
                    context
                ) {
                    granFunction()
                }
            } else {
                val snackbar: Snackbar =
                    Snackbar.make(
                        view,
                        context.getString(R.string.td_dialog_permission_denied_again),
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction(
                    context.getString(R.string.td_dialog_permission_denied_settings)
                ) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri =
                        Uri.fromParts(
                            "package",
                            activity.packageName,
                            null
                        )
                    intent.data = uri
                    activity.startActivity(intent)
                }
                snackbar.show()
            }

        }
    }
}