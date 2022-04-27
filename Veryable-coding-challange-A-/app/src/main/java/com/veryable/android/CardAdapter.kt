package com.veryable.android

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_details.*


class CardAdapter(val context: Context, val list: List<BankDetails>): RecyclerView.Adapter<CardAdapter.ViewHolder>() {
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

    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        val model = list[position]

        holder.accName.text = model.account_name
        holder.desc.text = model.desc
        holder.accountType.text = "Card: Instant"
        holder.imgBank.setImageResource(R.drawable.ic_card)

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