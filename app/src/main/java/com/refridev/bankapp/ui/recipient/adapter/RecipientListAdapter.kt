package com.refridev.bankapp.ui.recipient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.refridev.bankapp.data.model.response.RecipientListResponse
import com.refridev.bankapp.databinding.ItemRecipientBinding

class RecipientListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<RecipientListResponse> = arrayListOf()
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bindView(items[position])
        }
    }

    override fun getItemCount(): Int {
        return if (items != null && items.isNotEmpty()) {
            items.size
        } else {
            0
        }
    }

    fun setItems(items: List<RecipientListResponse>){
        this.items.addAll(items)
    }

    inner class ViewHolder(private val binding: ItemRecipientBinding): RecyclerView.ViewHolder(binding.root){
       fun bindView(item: RecipientListResponse){

           binding.llRecipientName.text = item.recipientName
           binding.llRecipientAccount.text = item.accountNumber

           binding.llRecipient.setOnClickListener {
               if (itemClickListener != null) {
                   itemClickListener?.onItemClicked(item)
               }
           }
       }
    }

    interface OnItemClickListener {
        fun onItemClicked(recipientListResponse: RecipientListResponse)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }

}