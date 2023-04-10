package com.example.appguests.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appguests.databinding.RowGuestBinding
import com.example.appguests.model.GuestModel
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root)  {

    //Ligação entre os elementos da interface com os dados
    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

    }

}