package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class ProfileRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("restaurant_prefs", Context.MODE_PRIVATE)

    fun getRestaurantName(): String = prefs.getString("name", "Ocean Delight Seafood") ?: "Ocean Delight Seafood"
    fun getAddress(): String = prefs.getString("address", "Jl. Pantai Indah Kapuk No. 88, Jakarta") ?: "Jl. Pantai Indah Kapuk No. 88, Jakarta"
    fun getDescription(): String = prefs.getString("description", "Ocean Delight Seafood menyajikan kelezatan hidangan laut segar terbaik dengan bumbu rempah nusantara dan internasional. Pengalaman kuliner tepi pantai yang tak terlupakan.") ?: "Ocean Delight Seafood menyajikan kelezatan hidangan laut segar terbaik dengan bumbu rempah nusantara dan internasional. Pengalaman kuliner tepi pantai yang tak terlupakan."
    fun getOpenHours(): String = prefs.getString("open_hours", "11:00 - 23:00") ?: "11:00 - 23:00"

    fun saveProfile(name: String, address: String, description: String, openHours: String) {
        prefs.edit().apply {
            putString("name", name)
            putString("address", address)
            putString("description", description)
            putString("open_hours", openHours)
            apply()
        }
    }
}
