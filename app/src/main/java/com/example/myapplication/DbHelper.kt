package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(private val context: Context, private val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, password TEXT, coins INT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("password", user.pass)
        values.put("coins", user.coins)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    @SuppressLint("Recycle")
    fun getUser(login: String, pass: String): Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND password = '$pass'", null)

        return result.moveToFirst()
    }

    fun setCoins(coins: Int, login: String) {
        val db = this.writableDatabase
        db!!.execSQL("INSERT INTO users(coins) VALUES $coins WHERE login = '$login'")
    }


    fun getCoins(login: String): Int {
        val db = this.readableDatabase
        val selection = "login = ?"
        val selectionArgs = arrayOf(login)
        val cursor = db.query(
            "users",
            arrayOf("coins"),
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        if (cursor.moveToFirst()) {
            // Только если запрос вернул данные
            val coinsIndex = cursor.getColumnIndex("coins")
            if (coinsIndex != -1) {
                val result = cursor.getInt(coinsIndex)
                cursor.close()
                return result
            } else {
                // Обработка случая, когда столбец не найден
                Log.e("DatabaseError", "Столбец 'coins' не найден в таблице")
                return 0 // Или другое значение по умолчанию
            }
        } else {
            // Обработка случая, когда пользователь не найден
            Log.e("DatabaseError", "Пользователь с логином '$login' не найден")
            return 0
        }
    }


}