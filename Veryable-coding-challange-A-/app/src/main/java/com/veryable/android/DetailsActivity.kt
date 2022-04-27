package com.veryable.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val bundle = intent.extras
        val message = bundle!!.getString("data")
        val messages = message!!.split(",")

        if(messages[0] == "bank")
            img.setImageResource(R.drawable.ic_bank)

        else
            img.setImageResource(R.drawable.ic_card)

        imgArrow.setOnClickListener {
            onBackPressed()
        }

        btnDone.setOnClickListener {
            onBackPressed()
        }

        accName.text = messages[1]
        description.text = messages[2]
    }
}