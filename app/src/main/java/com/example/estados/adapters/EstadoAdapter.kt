package com.example.estados.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.estados.model.Estado
import com.example.estados.R
import com.example.estados.api.EstadoService
import kotlinx.android.synthetic.main.item_estado.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EstadoAdapter(val listener: EstadoAdapterListener) :
    RecyclerView.Adapter<EstadoAdapter.ViewHolder>() {

    private var estados = mutableListOf<Estado>()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(EstadoService::class.java)

    init {
        service.getAll().enqueue(object : Callback<List<Estado>> {
            override fun onFailure(call: Call<List<Estado>>, t: Throwable) {
                Log.e("teste", t.message, t)
            }

            override fun onResponse(call: Call<List<Estado>>, response: Response<List<Estado>>) {
                estados = response.body()!!.toMutableList()
                notifyDataSetChanged()
                Log.d("teste", "estados = " + estados)
            }
        })
    }

    override fun getItemCount() = estados.size;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_estado, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estado = estados[position]
        holder.fillView(estado, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun fillView(estado: Estado, position: Int){
            itemView.txtNome.text = estado.nome
            itemView.txtUF.text = estado.sigla

            itemView.setOnClickListener {
                listener.verDetalhes(estado)
            }
        }
    }
}