package eu.tutorials.a7_minutesworkoutapp

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class storemaps : AppCompatActivity() {
    lateinit var store:EditText
    lateinit var storeAdd:TextView
    lateinit var storeloc:TextView
    lateinit var storeLati:TextView
    lateinit var storeLong:TextView
    lateinit var btnfss:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storemaps)
        store=findViewById(R.id.enterloc)
        storeAdd = findViewById(R.id.tvadd)
        storeloc =findViewById(R.id.tvloc)
        storeLati=findViewById(R.id.lati)
        storeLong=findViewById(R.id.longit)
        btnfss=findViewById(R.id.findSNS)

        val databaseHelper =DatabaseHelper(this)
        // Fetch the username to display as title
        val username = databaseHelper.getLatestUserData()
        if (username != null) {
            setTitle("Hi ${username.name}")
        }

        btnfss.setOnClickListener(){
         if(store.text.toString().isEmpty()){
             Toast.makeText(this,"Enter Shop N Store Name",Toast.LENGTH_SHORT).show()
         }
            else{
                getLocationFromStore(store.text.toString())
         }
        }
    }

    private fun getLocationFromStore(location: String) {
        val geocoder = Geocoder(this)
        val list:List<Address> = geocoder.getFromLocationName(location,5)!!
        if(list.isNullOrEmpty()){
            return
        }
        storeLati.text = "Latitude\n${list[0].latitude}"
        storeLong.text = "Longitude\n${list[0].longitude}"
        storeloc.text = "Locality \n${list[0].locality}"
        storeAdd.text = "Address\n${list[0].getAddressLine(0)}"

    }
}