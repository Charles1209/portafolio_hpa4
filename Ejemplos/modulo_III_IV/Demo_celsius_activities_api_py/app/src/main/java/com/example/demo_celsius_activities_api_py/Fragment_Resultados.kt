package com.example.demo_celsius_activities_api_py

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment_Resultados : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = ResultadosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__resultados, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        //Obtenemos los resultados a traves del metodo asignado al GET dentro del DatabaseHelper
        val dbHelper = DatabaseHelper()
        dbHelper.getAllResults { results ->
            results?.let {
                adapter.setData(it)
                //it se refiere a la lista de resultados obtenidos de la base de datos dentro de la función lambda pasada como argumento al método getAllResults()
            }
        }

        return view
    }
}

class ResultadosAdapter(private val resultList: MutableList<Result> = mutableListOf()) : RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder>() {

    inner class ResultadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val resultadoTextView: TextView = itemView.findViewById(R.id.resultadoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultadoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_resultado, parent, false)
        return ResultadoViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ResultadoViewHolder, position: Int) {
        val result = resultList[position]
        holder.resultadoTextView.text = "Registro: ${result.id}, °${result.tipo}: ${result.resultado}"
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Result>) {
        resultList.clear()
        resultList.addAll(data)
        notifyDataSetChanged()
    }
}

