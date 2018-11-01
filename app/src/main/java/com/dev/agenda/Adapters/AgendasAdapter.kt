package com.dev.agenda.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dev.agenda.Models.AgendaModel
import com.dev.agenda.R
import com.dev.agenda.Utils.TextUtils
import com.dev.lishabora.Utils.DateTimeUtils

import java.util.LinkedList
import java.util.Objects

import androidx.recyclerview.widget.RecyclerView
import org.joda.time.DateTime

class AgendasAdapter(private val context: Context, private var modelList: List<AgendaModel>?) : RecyclerView.Adapter<AgendaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        var itemView: View? = null

        itemView = LayoutInflater.from(parent.context).inflate(R.layout.agenda_card, parent, false)

        return AgendaViewHolder(itemView!!)
    }


    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {

        val model = modelList!![position]
        holder.txtAgendaTitle.text = model.agendaTitle
        holder.txtAgendaDescription.text = model.agendaDescription

    }


    override fun getItemCount(): Int {
        return if (null != modelList) modelList!!.size else 0
    }


    fun refresh(modelList: List<AgendaModel>) {
        this.modelList = modelList
        notifyDataSetChanged()

    }
}
