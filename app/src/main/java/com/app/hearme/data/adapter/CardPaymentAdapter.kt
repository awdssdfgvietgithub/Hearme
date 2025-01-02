package com.app.hearme.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.hearme.R
import com.app.hearme.model.CardPayment
import com.app.hearme.view.fragments.profile_settings.PaymentFragment

class CardPaymentAdapter(context: PaymentFragment, arrayList: ArrayList<CardPayment>): RecyclerView.Adapter<CardPaymentAdapter.ViewHolder>() {
    private val context: Context
    private val arrayList: ArrayList<CardPayment>
    private var isNewRadioButtonChecked = false
    private var lastCheckedPosition = -1

    init {
        this.context = context.requireContext()
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardPayment = arrayList[position]
        holder.itemView.findViewById<TextView>(R.id.tvNameCard).text = cardPayment.name
        holder.itemView.findViewById<ImageView>(R.id.imgCard).setImageResource(cardPayment.img)

        if (isNewRadioButtonChecked) {
            holder.itemView.findViewById<RadioButton>(R.id.rdoCard).isChecked = cardPayment.isSelected
        } else {
            if (holder.adapterPosition == 0) {
                holder.itemView.findViewById<RadioButton>(R.id.rdoCard).isChecked = true
                lastCheckedPosition = 0
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.findViewById<RadioButton>(R.id.rdoCard).setOnClickListener {
                handleRadioButtonCheck(adapterPosition)
            }

        }
    }

    private fun handleRadioButtonCheck(adapterPosition: Int) {
        isNewRadioButtonChecked = true
        arrayList[lastCheckedPosition].isSelected = false
        arrayList[adapterPosition].isSelected = true
        lastCheckedPosition = adapterPosition
        notifyDataSetChanged()
    }

}