package com.example.burgerapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.burgerapp.Data.FoodsModel
import com.example.burgerapp.Data.LocationsModel
import com.example.burgerapp.FoodServices
import com.example.burgerapp.LocServices
import com.example.burgerapp.R
import com.example.burgerapp.Retrocfitclient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var burgersLoc: LocationsModel

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

        getFoodLoc(googleMap)
    }

    private fun addBurgerMarker(burgersLoc: LocationsModel,map: GoogleMap){
        for( loc in burgersLoc) {
            val latLng = LatLng(loc.lat, loc.lng)
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
            )
        }
    }

    private fun getFoodLoc(map: GoogleMap) {
        val foodService: LocServices =
            Retrocfitclient.client.create(LocServices::class.java)

        val call = foodService.getAllLocation()

        call.enqueue(
            object : Callback<LocationsModel> {
                override fun onResponse(call: Call<LocationsModel>, response: Response<LocationsModel>) {
                    if(response.isSuccessful) {
                        response.body()?.let{
                            Log.d("HomeFragment", "Response: $it")
                            burgersLoc = it
                            addBurgerMarker(burgersLoc, map)
                        }
                    }
                }

                override fun onFailure(call: Call<LocationsModel>, t: Throwable) {
                    Log.d("HomeFragment", "Error: ${t.message}")
                }

            }
        )

    }
}
