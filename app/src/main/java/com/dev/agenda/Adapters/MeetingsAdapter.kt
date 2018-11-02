package com.dev.agenda.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dev.agenda.Models.MeetingAgendaModel
import com.dev.agenda.R
import com.dev.agenda.Utils.TextUtils
import com.dev.lishabora.Utils.DateTimeUtils

import java.util.LinkedList
import java.util.Objects

import androidx.recyclerview.widget.RecyclerView
import org.joda.time.DateTime

class MeetingsAdapter(private val context: Context, private var modelList: List<MeetingAgendaModel>?) : RecyclerView.Adapter<MeetingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        var itemView: View? = null

        itemView = LayoutInflater.from(parent.context).inflate(R.layout.meeting_card, parent, false)

        return MeetingViewHolder(itemView!!)
    }


    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {

        val model = modelList!![position]
        holder.txtMeetingTitle.text = model.meetingModel.title
        if (model.meetingModel.status == 0) {
            val c = context.resources.getColor(R.color.colorPrimary)
            holder.txtStatus.text = "Completed"
            holder.txtStatus.setTextColor(c)
            holder.statusView.setBackgroundColor(c)
            holder.statusLine.setBackgroundColor(c)
        } else {
            val c = context.resources.getColor(R.color.colorOrange)
            holder.txtStatus.text = "On-Going"
            holder.txtStatus.setTextColor(c)
            holder.statusView.setBackgroundColor(c)
            holder.statusLine.setBackgroundColor(c)
        }

        val x = model.meetingModel.startTimeStamp
        val arr = DateTimeUtils.getDateAndTime(x!!)

        try {
            if (DateTimeUtils.isToday(Objects.requireNonNull<DateTime>(DateTimeUtils.conver2Date(x)))) {
                val day = "Today"
                holder.txtDayTime.text = String.format("%s  %s", day, arr[1])

            } else {
                holder.txtDayTime.text = TextUtils.makeSectionOfTextBold(arr[0], arr[1])

            }
        }catch (e:Exception){

        }
        var agendas = 0
        try {
            if (model.agendaModels != null) {
                agendas = model.agendaModels.size
            }

        }catch (e:Exception){

        }
        holder.txtAgendasCount.text = String.format("Agenda(s)  %s", agendas.toString())
        holder.txtLocationTitle.text = model.meetingModel.location


    }


    override fun getItemCount(): Int {
        return if (null != modelList) modelList!!.size else 0
    }


    fun refresh(modelList: List<MeetingAgendaModel>) {
        this.modelList = modelList
        notifyDataSetChanged()

    }
}
