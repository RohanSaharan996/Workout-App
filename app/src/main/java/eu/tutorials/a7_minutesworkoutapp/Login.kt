package com.example.mydiary

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myownapp.register
import eu.tutorials.a7_minutesworkoutapp.Choice
import eu.tutorials.a7_minutesworkoutapp.DatabaseHelper
import eu.tutorials.a7_minutesworkoutapp.R


class login : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var uname: TextView
    private lateinit var pwd: TextView
    private lateinit var ln: Button
    private lateinit var create: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uname = findViewById(R.id.usern)
        pwd = findViewById(R.id.pwd)
        ln = findViewById(R.id.login)
        create = findViewById(R.id.create)

        databaseHelper = DatabaseHelper(this)
        val  latestUser=databaseHelper.getLatestUserData()
        if(latestUser != null) {
            uname.text = latestUser.name
            pwd.text = latestUser.password
        }

        ln.setOnClickListener {
            val name = uname.text.toString()
            val password = pwd.text.toString()

            val user = databaseHelper.login(name, password)

            if (user != null) {
                // The user exists and the password is correct
                val intent = Intent(this, Choice::class.java)
                startActivity(intent)
                Toast.makeText(this,"Welcome !!",Toast.LENGTH_SHORT).show()
                setTitle("Hi $name")

            } else {
                Toast.makeText(this,"First register",Toast.LENGTH_SHORT).show()
            }
        }

        create.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
    }
}
