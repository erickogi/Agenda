package com.dev.agenda.Views

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle

import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.dev.agenda.GeoUtils.TrackerService
import com.dev.agenda.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Result
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*

import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST = 1
     lateinit var googleApiClient: GoogleApiClient
    private var fragment: Fragment? = null

    internal fun setUpView(name: String) {

        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment!!).addToBackStack(null).commit()
        }

        try {
            this.title = name
        } catch (nm: Exception) {
            nm.printStackTrace()
        }

    }

    internal fun popOutFragments() {
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
        this.title = "Farmers"
    }

    private fun setUpMainFragment() {

        fragment = FragmentList()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment as FragmentList, "fragmentMain").commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUp()
        setUpMainFragment()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun settings() {

        //if (googleApiClient == null) {
            googleApiClient = GoogleApiClient.Builder(applicationContext).addApi(LocationServices.API).build()
            this.googleApiClient.connect()

            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = (30 * 1000).toLong()
            locationRequest.fastestInterval = (5 * 1000).toLong()
            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)


            builder.setAlwaysShow(true)


            val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())

            result.setResultCallback({ results : LocationSettingsResult ->

                val status = results.getStatus()

                val state = results.getLocationSettingsStates()

                when (status.getStatusCode()) {

                    LocationSettingsStatusCodes.SUCCESS -> {
                    }

                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->


                        try {

                            status.startResolutionForResult(this, 1000)

                        } catch (e: IntentSender.SendIntentException) {

                        }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }

            } as ResultCallback<LocationSettingsResult>)
       // }
    }

    private fun startTrackerService() {
        startService(Intent(this, TrackerService::class.java))
    }

    private fun setUp() {

        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

//            settings()

        }

        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService()
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST)
        }
    }

}
