package com.example.myownapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.mydiary.login
import eu.tutorials.a7_minutesworkoutapp.DatabaseHelper
import eu.tutorials.a7_minutesworkoutapp.R
import java.util.*

class register : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var sname: TextView
    private lateinit var pswd: TextView
    private lateinit var reg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        sname = findViewById(R.id.sname)
        pswd = findViewById(R.id.pswd)
        reg = findViewById(R.id.reg)

        databaseHelper = DatabaseHelper(this)

        reg.setOnClickListener {
            val name = sname.text.toString()
            val password = pswd.text.toString()

            databaseHelper.register(name, password)

            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
    }
}
