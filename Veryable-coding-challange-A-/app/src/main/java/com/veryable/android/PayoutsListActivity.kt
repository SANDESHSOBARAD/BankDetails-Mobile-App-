package com.veryable.android

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.veryable.android.databinding.ActivityPayoutsListBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayoutsListBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)

        var bankList: List<BankDetails>

        val retrofit = Retrofit.Builder()
            .baseUrl("https://veryable-public-assets.s3.us-east-2.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: ApiCall = retrofit.create(ApiCall::class.java)
        val recyclerBank = binding.recyclerBankAcc
        val recyclerCard = binding.recyclerCards

        val call: Call<List<BankDetails>> = api.bankDetails
        val context = this

        call.enqueue(object : Callback<List<BankDetails>> {

            override fun onResponse(call: Call<List<BankDetails>>, response: Response<List<BankDetails>>) {
                var bankList: MutableList<BankDetails> = arrayListOf()
                var cardList: MutableList<BankDetails> = arrayListOf()


                var responseList = response.body()!!

                for(item in responseList) {
                    if(item.account_type == "bank")
                        bankList.add(item)
                    else
                        cardList.add(item)
                }
                Toast.makeText(context, "Api Called Successfully", Toast.LENGTH_SHORT).show()

                linearLayoutManager = LinearLayoutManager(context)
                recyclerBank.layoutManager = linearLayoutManager
                recyclerBank.adapter = bankList?.let { BankAdapter(context, it) }

                linearLayoutManager = LinearLayoutManager(context)
                recyclerCard.layoutManager = linearLayoutManager
                recyclerCard.adapter = cardList?.let { CardAdapter(context, it) }

            }

            override fun onFailure(call: Call<List<BankDetails>>, t: Throwable) {
                Log.e("TAG", "onFailure: "+t.message )
            }
        })
    }
}