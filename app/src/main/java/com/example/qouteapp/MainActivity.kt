package com.example.qouteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        val textView = findViewById<TextView>(R.id.textView)
        GlobalScope.launch {
            val result = quotesApi.getQuotes()
            if (result.isSuccessful) {
                val quotes = result.body()
                if (quotes != null && quotes.isNotEmpty()) {
                    runOnUiThread {
                        textView.text = quotes[0] // Display the first quote in the TextView
                    }
                }
            }
        }
    }
}