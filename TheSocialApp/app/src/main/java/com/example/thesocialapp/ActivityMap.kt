
package com.example.thesocialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.thesocialapp.databinding.ActivityMapBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.firebase.auth.FirebaseAuth
import android.graphics.Bitmap

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable

import android.R
import android.content.Context

import androidx.annotation.DrawableRes

import com.google.android.gms.maps.model.BitmapDescriptor




class ActivityMap : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
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
        val Rise = LatLng(30.62193, -96.34220)
        val Rec = LatLng(30.60798, -96.34289)
        val Commons = LatLng(30.61545,-96.33623)



        mMap.addMarker(MarkerOptions().position(Evans).title("Evans Library").icon(
            BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_library_books_24)))
//        mMap.addMarker(MarkerOptions().position(Kyle).title("Kyle Field").icon(
//            BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_sports_football_24)))
//        mMap.addMarker(MarkerOptions().position(Rise).title("The Rise at Northgate").icon(
//            BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_outdoor_grill_24)))
//        mMap.addMarker(MarkerOptions().position(Rec).title("Recreation Center").icon(
//            BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_sports_basketball_24)))
//        mMap.addMarker(MarkerOptions().position(Commons).title("The Commons").icon(
//            BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_cake_24)))
//


        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MSC, 16f))
    }
    private fun bitmapDescriptorFromVector(
        context: Context,
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor? {
        val background = ContextCompat.getDrawable(context, R.drawable.ic_input_get)
        background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(
            40,
            20,
            vectorDrawable.intrinsicWidth + 40,
            vectorDrawable.intrinsicHeight + 20
        )
        val bitmap = Bitmap.createBitmap(
            background.intrinsicWidth,
            background.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        background.draw(canvas)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
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
}