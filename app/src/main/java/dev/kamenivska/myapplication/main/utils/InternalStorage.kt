package dev.kamenivska.myapplication.main.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


fun Bitmap.saveToInternalStorage(context: Context) {
    // path to /data/data/yourapp/app_data/imageDir
    val directory = context.getDir("imageDir", Context.MODE_PRIVATE)
    // Create imageDir
    val mypath = File(directory, "profile.jpg")

    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(mypath)
        // Use the compress method on the BitMap object to write image to the OutputStream
        this.compress(Bitmap.CompressFormat.PNG, 100, fos)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            fos!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun loadImageFromStorage(path: String): Bitmap? {
    return try {
        val f = File(path, "profile.jpg")
        BitmapFactory.decodeStream(FileInputStream(f))
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}