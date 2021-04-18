package com.gotenna.ukcrime.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gotenna.ukcrime.R
import com.gotenna.ukcrime.data.Crime
import com.gotenna.ukcrime.ui.crimes.CrimesActivity

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var crime: Crime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Set up the action bar with back button and no title
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = resources.getString(R.string.detail_action_bar_title)

        // Init all the text views
        val textViewTitle: TextView = findViewById(R.id.detail_title)
        val textViewDate: TextView = findViewById(R.id.detail_date)
        val textViewLat: TextView = findViewById(R.id.detail_lat)
        val textViewLong: TextView = findViewById(R.id.detail_long)
        val textViewStreet: TextView = findViewById(R.id.detail_street)
        val textViewCategory: TextView = findViewById(R.id.detail_category)
        val textViewStatus: TextView = findViewById(R.id.detail_status)


        crime = intent.getSerializableExtra(CrimesActivity.EXTRA_CRIME) as Crime

        textViewTitle.text = getString(R.string.crime_number) + crime.id.toString()
        textViewDate.text = crime.month
        if (crime.location != null) {
            textViewLat.text = crime.location.latitude
            textViewLong.text = crime.location.longitude
            textViewStreet.text = crime.location.street.name
        } else {
            textViewLat.text = getString(R.string.not_found)
            textViewLong.text = getString(R.string.not_found)
            textViewStreet.text = getString(R.string.not_found)
        }
        textViewCategory.text = crime.category
        if (crime.outcome_status != null) {
            textViewStatus.text = crime.outcome_status.category
        } else {
            textViewStatus.text = getString(R.string.not_found)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        if (crime.location != null) {
            mapFragment.getMapAsync(this)
        } else {
            supportFragmentManager.beginTransaction().hide(mapFragment).commit()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Switch on menu item to finish if the user presses the back button
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if (crime.location != null) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            crime.location.latitude.toDouble(),
                            crime.location.longitude.toDouble()
                        )
                    )
                    .title(crime.id.toString())
            )
        }
    }

}