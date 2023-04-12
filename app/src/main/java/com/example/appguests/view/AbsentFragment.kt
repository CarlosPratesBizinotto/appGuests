package com.example.appguests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appguests.constants.DataBaseConstants
import com.example.appguests.databinding.FragmentAbsentBinding
import com.example.appguests.view.adapter.GuestsAdapter
import com.example.appguests.view.listerner.OnGuestListener
import com.example.appguests.viewmodel.GuestsViewModel


class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        //Definir o layout
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //Definir um Adapter -> Que "cola"  a tela com a lista
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                //Nesse caso quando a Activity for iniciar, ela ira iniciar com as informações mais na linhas de cima
                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }

        }
        adapter.attachListener(listener)
        observe()

        return binding.root


    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            //Lista de convidados
            adapter.updatedGuests(it)

        }
    }

}