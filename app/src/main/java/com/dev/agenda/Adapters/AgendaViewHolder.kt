package com.dev.agenda.Adapters

import android.view.View
import android.widget.TextView

import com.dev.agenda.R

import androidx.recyclerview.widget.RecyclerView


class AgendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var itemVew: View
    var txtAgendaTitle: TextView
    var txtAgendaDescription: TextView


    init {
        this.itemVew = itemView
        this.itemVew = itemView

        txtAgendaTitle = itemView.findViewById(R.id.txt_agenda_title)
        txtAgendaDescription = itemView.findViewById(R.id.txt_agenda)


    }


}
