package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserAutorization : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_autorization)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userLogin: EditText = findViewById(R.id.userLogin_auth)
        val userPass: EditText = findViewById(R.id.userPassword_auth)
        val ButAuth: Button = findViewById(R.id.Button_auth)
        val linkToReg: TextView = findViewById(R.id.ToReg)

        linkToReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ButAuth.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if (login == "" || pass == "")
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()

            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, pass)

                if (isAuth) {
                    Toast.makeText(this, "Привет, $login", Toast.LENGTH_SHORT).show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)

                }
                else {
                    Toast.makeText(this, "Пользователь $login не найден", Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}