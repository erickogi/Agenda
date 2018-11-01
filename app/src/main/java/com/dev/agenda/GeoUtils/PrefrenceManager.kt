package com.dev.agenda.GeoUtils

import android.content.Context
import android.content.SharedPreferences

internal class PrefrenceManager(
        // Context

        var _context: Context) {

    // Shared Preferences

    var pref: SharedPreferences

    // Editor for Shared preferences

    var editor: SharedPreferences.Editor

    // Shared pref mode
    var PRIVATE_MODE = 0


    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setLastLon(latLon: String) {
        editor.putString(lastLon, latLon)
        editor.commit()
    }

    fun getLastLon(): String? {
        return pref.getString(lastLon, null)
    }

    fun setLastLat(latLon: String) {
        editor.putString(lastLat, latLon)
        editor.commit()
    }

    fun getLastLat(): String? {
        return pref.getString(lastLat, null)
    }

    companion object {
        private val PREF_NAME = "lishaboraprefs"

        private val lastLat = "lastLat"
        private val lastLon = "lastLon"
    }


}
