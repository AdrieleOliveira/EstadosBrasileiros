package com.example.estados.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.estados.R
import com.example.estados.api.EstadoService
import com.example.estados.model.Cidade
import com.example.estados.model.Estado
import kotlinx.android.synthetic.main.item_cidade.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetalheAdapter(val estado: Estado) :
    RecyclerView.Adapter<DetalheAdapter.ViewHolder>() {

    private var cidades = mutableListOf<Cidade>()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(EstadoService::class.java)

    init {
        service.getMunicipios(estado!!.sigla).enqueue(object: Callback<List<Cidade>> {
            override fun onFailure(call: Call<List<Cidade>>, t: Throwable) {
                Log.e("teste", t.message, t)
            }

            override fun onResponse(call: Call<List<Cidade>>, response: Response<List<Cidade>>) {
                cidades = response.body()!!.toMutableList()
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount() = cidades.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_cidade, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cidade = cidades[position]
        holder.fillView(cidade, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun fillView(cidade: Cidade, position: Int){
            itemView.txtCidade.text = cidade.nome
        }
    }
}