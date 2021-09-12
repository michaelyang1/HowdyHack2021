package com.example.thesocialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.thesocialapp.databinding.ActivityMapsBinding
//import com.google.android.libraries.places.api.Places
//import com.google.android.libraries.places.api.model.Place
//import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
//import com.google.android.libraries.places.api.net.PlacesClient

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
//    private lateinit var placesClient: PlacesClient

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
//    private var locationPermissionGranted = false
//
//    // The geographical location where the device is currently located. That is, the last-known
//    // location retrieved by the Fused Location Provider.
//    private var lastKnownLocation: Location? = null
//    private var likelyPlaceNames: Array<String?> = arrayOfNulls(0)
//    private var likelyPlaceAddresses: Array<String?> = arrayOfNulls(0)
//    private var likelyPlaceAttributions: Array<List<*>?> = arrayOfNulls(0)
//    private var likelyPlaceLatLngs: Array<LatLng?> = arrayOfNulls(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val Evans = LatLng(30.616586, -96.339496)
        val MSC = LatLng(30.611885, -96.342063)
        val Kyle = LatLng(30.610364, -96.341214)

        mMap.addMarker(MarkerOptions().position(Evans).title("Evans Library"))
        mMap.addMarker(MarkerOptions().position(MSC).title("Memorial Student Center"))
        mMap.addMarker(MarkerOptions().position(Kyle).title("Kyle Field"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MSC, 16f))
    }
}