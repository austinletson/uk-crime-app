package com.gotenna.ukcrime.ui.crimes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gotenna.ukcrime.R
import com.gotenna.ukcrime.data.Crime

class CrimesAdapter (private val crimes: List<Crime>, private val onCrimeClick: (Crime) -> Unit) : RecyclerView.Adapter<CrimesAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var schoolTextView: TextView = v.findViewById(R.id.crime_item_id)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_crime, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return crimes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onCrimeClick(crimes[position]) }
        holder.schoolTextView.text = "Crime # " + crimes[position].id
    }
}