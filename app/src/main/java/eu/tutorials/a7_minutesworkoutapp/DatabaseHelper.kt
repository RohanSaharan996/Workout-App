package eu.tutorials.a7_minutesworkoutapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "mydatabase", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // TODO: Handle database schema changes
    }

    fun register(name: String, password: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("password", password)
        db.insert("users", null, values)
    }


    @SuppressLint("Range")
    fun login(name: String, password: String): User? {
        val db = readableDatabase
        val cursor = db.query(
            "users",
            null,
            "name = ? AND password = ?",
            arrayOf(name, password),
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            val user = User(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("password"))
            )
            cursor.close()
            return user
        } else {
            cursor.close()
            return null // This will return null if the user is not found.
        }
    }

    @SuppressLint("Range")
    fun getLatestUserData(): User? {
        val db = readableDatabase
        val query = "SELECT * FROM users ORDER BY id DESC LIMIT 1"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val user = User(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("password"))
            )
            cursor.close()
            return user
        } else {
            cursor.close()
            return null // This will return null if no user data is found.
        }
    }
}
class User(
    val id: Int,
    val name: String,
    val password: String
)