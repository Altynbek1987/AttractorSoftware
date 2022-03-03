package com.example.attractorsoftware.utils

import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.attractorsoftware.data.models.Mode

fun Context.getImageVideoCursor(mode: Mode): Cursor? {
    val projection = when (mode) {

        else -> (MediaStore.Files.FileColumns.MEDIA_TYPE)
    }
    return contentResolver
        .query(
            MediaStore.Files.getContentUri("external")!!, arrayOf(
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
            ),
            projection, null, MediaStore.Images.Media.DATE_MODIFIED
        )
}

fun Fragment.hasPermissionCheckAndRequest(
    requestPermissionLauncher: ActivityResultLauncher<Array<String>>,
    permission: Array<String>,
): Boolean {
    for (per in permission) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                per
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(permission)
            return false
        }
    }
    return true
}