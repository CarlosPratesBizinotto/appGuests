package com.example.appguests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appguests.databinding.RowGuestBinding
import com.example.appguests.model.GuestModel
import com.example.appguests.view.listerner.OnGuestListener
import com.example.appguests.view.viewholder.GuestViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    //Pra cada item esse onCreate é chamado
    //Esse cria
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, listener)
    }

    //Faço a cola, atribuo os valores para o layout
    //Esse atribui a lista
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

  // Tamanho da listagem
    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updatedGuests(list: List<GuestModel>){
        guestList = list
        notifyDataSetChanged()


    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener

    }

}