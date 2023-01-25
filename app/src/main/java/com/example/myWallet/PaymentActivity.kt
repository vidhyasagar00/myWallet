package com.example.myWallet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val amount = findViewById<EditText>(R.id.amount)
        val id = findViewById<EditText>(R.id.upiId)
        findViewById<TextView>(R.id.btnPay).setOnClickListener { payUsingUpi(amount.text.toString(), id.text.toString())}
    }


    fun payUsingUpi(amount: String?, upiId: String?) {
        val uri: Uri = Uri.parse("upi://pay").buildUpon()
            .appendQueryParameter("pa", upiId)
//            .appendQueryParameter("pn", name)
            .appendQueryParameter("am", amount)
//            .appendQueryParameter("tn", "note")
            .appendQueryParameter("cu", "INR")
            .build()
        val upiPayIntent = Intent(Intent.ACTION_VIEW)
        upiPayIntent.data = uri

        // will always show a dialog to user to choose an app
        val chooser = Intent.createChooser(upiPayIntent, "Pay with")

        // check if intent resolves
        if (null != chooser.resolveActivity(packageManager)) {
            startActivityForResult(chooser, 1111)
        } else {
            Toast.makeText(this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show()
        }
    }
}