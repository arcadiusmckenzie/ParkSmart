package com.example.parksmart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment()  {

    private val callback = OnMapReadyCallback { googleMap ->

        val koinangestreet = LatLng(-1.283220, 36.818285)
        val kirinyagard = LatLng(-1.279856, 36.822450)
        val hailleselassie = LatLng(-1.2921,36.8219)
        googleMap.addMarker(MarkerOptions().position(hailleselassie).title("Marker in Haille Selasie"))
        googleMap.addMarker(MarkerOptions().position(koinangestreet).title("Marker in Koinange Street"))
        googleMap.addMarker(MarkerOptions().position(kirinyagard).title("Marker in Kirinyaga Road"))

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hailleselassie))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(kirinyagard))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(koinangestreet))

        googleMap.setOnMarkerClickListener{

            val intent = Intent(activity, ParkingActivity::class.java)
            startActivity(intent)
            false
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}