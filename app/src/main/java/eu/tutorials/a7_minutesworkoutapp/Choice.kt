package eu.tutorials.a7_minutesworkoutapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Choice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)
        val clickCard:CardView = findViewById(R.id.card_view)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val databaseHelper =DatabaseHelper(this)
        // Fetch the username to display as title
        val username = databaseHelper.getLatestUserData()
        if (username != null) {
            setTitle("Hi ${username.name}")
        }
        fab.setOnClickListener {
            // Open the Google Maps activity
            val intent = Intent(this,storemaps::class.java)
            startActivity(intent)
        }
        clickCard.setOnClickListener(){
            val ExerciseIntent =Intent(this,MainActivity::class.java)
            startActivity(ExerciseIntent)
        }

    }
}