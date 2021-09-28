
package com.example.thesocialapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.thesocialapp.databinding.ActivityMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.*
import com.google.firebase.auth.FirebaseAuth

class ActivityMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // used to retrieve current user location on map
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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
        val TheRise = LatLng(30.62193, -96.34220)
        val Rec = LatLng(30.60723, -96.34331)
        val Sbisa = LatLng(30.61719, -96.34394)

        mMap.addMarker(MarkerOptions().position(Evans).title("Evans Study Group").icon(
            bitmapDescriptorFromVector(baseContext, R.drawable.ic_baseline_library_books_24)))
        mMap.addMarker(MarkerOptions().position(MSC).title("HowdyHack").icon(
            bitmapDescriptorFromVector(baseContext, R.drawable.ic_baseline_computer_24)))
        mMap.addMarker(MarkerOptions().position(Kyle).title("Career Fair at Kyle Field").icon(
            bitmapDescriptorFromVector(baseContext, R.drawable.ic_baseline_work_24)))
        mMap.addMarker(MarkerOptions().position(TheRise).title("Grill at Northgate").icon(
            bitmapDescriptorFromVector(baseContext, R.drawable.ic_baseline_outdoor_grill_24)))
        mMap.addMarker(MarkerOptions().position(Rec).title("Intramural Basketball").icon(
            bitmapDescriptorFromVector(baseContext, R.drawable.ic_baseline_sports_basketball_24)))
        mMap.addMarker(MarkerOptions().position(Sbisa).title("Sbisa Food Festival").icon(
            bitmapDescriptorFromVector(baseContext, R.drawable.ic_baseline_fastfood_24)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MSC, 16f))

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_map, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_feed) {
            intent = Intent(this, ActivityFeed::class.java)
            startActivity(intent)
            return true;
        }
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut()
            intent = Intent(this, Login::class.java)
            startActivity(intent)
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    override fun onMarkerClick(p0: Marker?) = false

    /** Retrieve user permission for current location */
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }

    /** Convert a vector asset to bitmap descriptor for customized event marker on map */
    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}