package com.example.myapplication

import java.util.Currency
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.internal.format
import ru.yoomoney.sdk.kassa.payments.Checkout
import ru.yoomoney.sdk.kassa.payments.Checkout.createTokenizationResult
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.Amount
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentMethodType
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentParameters
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.SavePaymentMethod
import java.math.BigDecimal


class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val title: TextView = findViewById(R.id.item_list_title_one)
        val text: TextView = findViewById(R.id.item_list_desc_one)
        val price: TextView = findViewById(R.id.item_list_price_one)
        val buyButton: Button = findViewById(R.id.button_buy)




        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
        price.text = format("%.2f ₽", intent.getDoubleExtra("itemPrice", 1.00))


        buyButton.setOnClickListener {
            val titleBuy = title.text.toString()
            val subtitle = text.text.toString()
            val currency = Currency.getInstance("RUB")
            val priceBigDecimal: BigDecimal = BigDecimal.valueOf(intent.getDoubleExtra("itemPrice", 1.00))
            val amount = Amount(priceBigDecimal, currency)
            val clientApplicationKey = "test_NDI1NTEwCGXYyeQl5BO01sat8d0raCv5kXCUZ2PIJq0"
            val shopId = "425510"
            val savePaymentMethod = SavePaymentMethod.USER_SELECTS

            val parameters = PaymentParameters(amount, titleBuy, subtitle,
                clientApplicationKey, shopId, savePaymentMethod,
                authCenterClientId = "test_5zGgWhmS-IB2d45MTYpR1oAzV0yT4OHvNy5_6jQ6nF0")
            val intent: Intent = Checkout.createTokenizeIntent(this, parameters)

            startActivityForResult(intent, 1)


        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            when (resultCode) {
                RESULT_OK -> {
                    // successful tokenization
                    val result = data?.let { createTokenizationResult(it) }
                    Toast.makeText(this, "Оплата прошла", Toast.LENGTH_LONG).show()
                }
                RESULT_CANCELED -> {
                    // user canceled tokenization
                    Toast.makeText(this, "Оплата НЕ прошла", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}