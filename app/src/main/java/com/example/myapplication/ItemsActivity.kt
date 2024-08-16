package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()



        items.add(Item(1,
            "jj",
            "Jack & Jones oversized t-shirt with cherub backprint in black",
            "Jack & Jones",
            "T-Shirts & Vests by Jack & Jones Your new go-to Crew neck Short sleeves Graphic print to chest and back Oversized fit",
            1.99))
        items.add(Item(2,
            "dickies_icon_logo_hoodie_in_grey",
            "Dickies icon logo hoodie in grey",
            "Dickies",
            "Hoodies & Sweatshirts by Dickies Dress code: casj Branded design Drawstring hood Pouch pocket Regular fit",
            3299.99))
        items.add(Item(3,
            "jj_cargo_trouser",
            "Jack & Jones Intelligence loose fit cargo trouser in black",
            "Jack & Jones",
            "Trousers & Chinos by Jack & Jones Your sign to stop scrolling Regular rise Belt loops Functional pockets Relaxed fit",
            2199.99))
        items.add(Item(4,
            "wftw_chain",
            "WFTW Accessories Chain in gold",
            "WFTW",
            "Accessories by WFTW Your outfit's pick-me-up Curb chain Faux-gem details Lobster clasp",
            1199.99))
        items.add(Item(5,
            "timberland_boots",
            "Timberland euro sprint hiker boots in black nubuck leather",
            "Timberland",
            "Shoes, Boots & Trainers by Timberland Add-to-bag material Lace-up fastening Padded cuff Signature Timberland branding Round toe Chunky sole Lugged tread",
            9499.99))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)
    }
}