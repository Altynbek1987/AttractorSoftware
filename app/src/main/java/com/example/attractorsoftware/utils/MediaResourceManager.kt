package com.example.attractorsoftware.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.attractorsoftware.data.models.GalleryList
import com.example.attractorsoftware.data.models.GalleryModel
import com.example.attractorsoftware.data.models.Mode

import java.util.concurrent.CancellationException
import kotlin.collections.ArrayList

class MediaResourceManager(
    private val context: Context,
) {
    var preSelectedUrls: List<Uri> = ArrayList()
    fun retrieveMedia(
        start: Int = 0,
        limit: Int = 0,
        mode: Mode = Mode.All,
    ): GalleryList<GalleryModel> {
        val cursor = context.getImageVideoCursor(mode)
        val list = ArrayList<GalleryModel>()
        val selectionList = ArrayList<GalleryModel>()

        try {
            if (cursor != null) {
                val contentUrl = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID)

                var end = if (limit == 0) cursor.count - start - 1 else limit
                if (cursor.count - start < limit) {
                    end = cursor.count - 1
                }
                if (start == 0) {
                    cursor.moveToFirst()
                }
                synchronized(context) {
                    for (i in start until end) {
                        try {
                            val path = try {
                                Uri.withAppendedPath(
                                    MediaStore.Files.getContentUri("external")!!,
                                    "" + cursor.getInt(contentUrl)
                                )
                            } catch (ex: Exception) {
                                Uri.EMPTY
                            }

                            GalleryModel(
                                contentUrl = path,
                            ).also {
                                if (preSelectedUrls.contains(it.contentUrl)) {
                                    selectionList.add(it)
                                }
                                list.add(it)
                            }

                        } catch (ex: java.lang.Exception) {
                            ex.printStackTrace()
                        }
                        cursor.moveToNext()
                    }
                    cursor.close()
                }
            }
        } catch (ex: CancellationException) {
            return GalleryList(list = ArrayList())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return GalleryList(list = list)
    }
}