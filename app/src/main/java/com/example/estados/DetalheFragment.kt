package com.example.estados

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estados.adapters.DetalheAdapter
import com.example.estados.model.Estado
import kotlinx.android.synthetic.main.fragment_detalhe.*
import kotlinx.android.synthetic.main.fragment_detalhe.view.*

class DetalheFragment : Fragment() {

    private lateinit var adapter: DetalheAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalhe, container, false)

        val estado = arguments?.getSerializable("estado") as Estado

        view.txtEstado.text = estado.nome
        view.txtSigla.text = estado.sigla
        view.txtRegiao.text = estado.regiao.nome

        adapter = DetalheAdapter(estado)
        view.listCidades.adapter = adapter
        view.listCidades.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        return view
    }
}