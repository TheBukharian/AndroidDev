package com.example.androiddevfest

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_maps.lightBtnMap
import kotlinx.android.synthetic.main.activity_speakers.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG: String = MapsActivity::class.java.simpleName
    private lateinit var mMap: GoogleMap
    lateinit var saveData : SaveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
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

        try {
            // Customise the styling of the base map using a JSON object

            saveData = SaveData(this)
            if (saveData.loadDarkModeState()) {
                 mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.dark_map
                    )
                )
                setTheme(R.style.DarkTheme)
                lightBtnMap.setImageResource(R.drawable.darklight)
                actBarMap.setBackgroundResource(R.drawable.act_darkoption)
            } else {
                 mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.maplight
                    )
                )
                setTheme(R.style.AppTheme)
                lightBtnMap.setImageResource(R.drawable.light)
                actBarMap.setBackgroundResource(R.drawable.actlight)
            }
        }catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)

        }
        arrowMap.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        shareBtnMap.setOnClickListener{
            try{
                val shareIntent =Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"GDG APP by TheBukharian")
                var shareMessage = "\nLet`s try this GDG application:\n\n"
                shareMessage = shareMessage+ "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID+ "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share with "))
            }
            catch (e:Exception){
                Log.d("MainActivity","Couldn` t load the web site")
            }
        }
        lightBtnMap.setOnClickListener {
            if (!saveData.loadDarkModeState()) {
                saveData.setDarkNodeState(true)
                setTheme(R.style.DarkTheme)
                lightBtnMap.setImageResource(R.drawable.darklight)
                actBarMap.setBackgroundResource(R.drawable.act_darkoption)
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)


            }else{
                saveData.setDarkNodeState(false)
                setTheme(R.style.AppTheme)
                lightBtnMap.setImageResource(R.drawable.light)
                actBarMap.setBackgroundResource(R.drawable.actlight)
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }


            val tashkent = LatLng(41.289574, 69.221680)
            mMap.addMarker(MarkerOptions().position(tashkent).title("Dev Fest"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(tashkent))
    }
}