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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val label = findViewById<TextView>(R.id.mainLabel)
        val userLogin: EditText = findViewById(R.id.userLogin)
        val userEmail: EditText = findViewById(R.id.userEmail)
        val userPass: EditText = findViewById(R.id.userPassword)
        val regBut: Button = findViewById(R.id.regButton)

        regBut.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()
            if (login == "" || email == "" || pass == "")
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
            else {
                val user = User(login, email, pass)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Регистрация завершена", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ItemsActivity::class.java)
                startActivity(intent)

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }
        }

        val linkToAuth: TextView = findViewById(R.id.ToAuth)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, UserAutorization::class.java)
            startActivity(intent)
        }
    }
}