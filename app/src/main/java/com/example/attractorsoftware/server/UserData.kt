package com.example.attractorsoftware.server

import android.content.Context
import android.util.Log
import com.example.attractorsoftware.data.models.PersonModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class UserData {

    fun getUserData(context: Context): List<PersonModel> {
        val jsonFileString = getJsonDataFromAsset(context, "users.json")
        if (jsonFileString != null) {
            Log.e("data", jsonFileString)
        }
        val gson = Gson()
        val listPersonType = object : TypeToken<List<PersonModel>>() {}.type
        val persons: List<PersonModel> = gson.fromJson(jsonFileString, listPersonType)
        return persons
    }
    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}