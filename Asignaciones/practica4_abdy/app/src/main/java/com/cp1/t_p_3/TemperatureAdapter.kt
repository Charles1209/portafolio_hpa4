package com.cp1.t_p_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TemperatureAdapter(private val temperatureList: List<Temperature>) :
    RecyclerView.Adapter<TemperatureAdapter.TemperatureViewHolder>() {

    // ViewHolder que describe cada fila del RecyclerView
    class TemperatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val temperatureTypeTextView: TextView = itemView.findViewById(R.id.textViewType)
        val temperatureValueTextView: TextView = itemView.findViewById(R.id.textViewValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperatureViewHolder {
        // Infla la vista de cada elemento
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_temperature, parent, false)
        return TemperatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemperatureViewHolder, position: Int) {
        // Obtiene los datos y los asigna a los TextViews
        val currentTemperature = temperatureList[position]
        holder.temperatureTypeTextView.text = currentTemperature.type
        holder.temperatureValueTextView.text = String.format("%.2f", currentTemperature.value)
    }

    override fun getItemCount(): Int {
        // Devuelve el número de elementos en la lista
        return temperatureList.size
    }
}