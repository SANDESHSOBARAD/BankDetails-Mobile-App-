package com.veryable.android

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.BankAdapter.*

class BankAdapter(val context: Context, val list: List<BankDetails>): RecyclerView.Adapter<BankAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val accName: TextView = view.findViewById(R.id.accountName)
        val desc: TextView = view.findViewById(R.id.desc)
        val accountType: TextView = view.findViewById(R.id.accountType)
        val linearLayout: LinearLayout = view.findViewById(R.id.linearLayout)
        val imgBank: ImageView = view.findViewById(R.id.imgBank)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_bank_details, parent, false))
    }

    override fun onBindViewHolder(holder: BankAdapter.ViewHolder, position: Int) {
        val model = list[position]

        // sets the text to the textview from our itemHolder class
        holder.accName.text = model.account_name
        holder.desc.text = model.desc
        holder.accountType.text = "Bank Account: ACH - Same Day"
        holder.imgBank.setImageResource(R.drawable.ic_bank)

        holder.linearLayout.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)

            var data: String = model.account_type+","+model.account_name+","+model.desc
            intent.putExtra("data", data);
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}