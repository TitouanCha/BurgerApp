package com.example.burgerapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.burgerapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflater le layout du fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialiser la carte
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Ajouter des marqueurs pour les entrepôts
        val entrepot = LatLng(48.8567, 2.3510) // Coordonnées exemple
        val entrepot2 = LatLng(48.8584, 2.2945) // Autre entrepôt exemple

        mMap.addMarker(MarkerOptions().position(entrepot).title("Entrepot_1"))
        mMap.addMarker(MarkerOptions().position(entrepot2).title("Entrepot_2"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(entrepot, 15f))
    }
}
