package com.example.estados

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estados.adapters.EstadoAdapter
import com.example.estados.adapters.EstadoAdapterListener
import com.example.estados.model.Estado
import kotlinx.android.synthetic.main.fragment_estados.*
import java.io.Serializable

class EstadosFragment : Fragment(), EstadoAdapterListener {

    private lateinit var adapter: EstadoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estados, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = EstadoAdapter(this)
        listEstados.adapter = adapter
        listEstados.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    override fun verDetalhes(estado: Estado) {
        val bundle = Bundle()
        bundle.putSerializable("estado", estado as Serializable)
        findNavController().navigate(R.id.estadosToDetalhe, bundle)
    }

}