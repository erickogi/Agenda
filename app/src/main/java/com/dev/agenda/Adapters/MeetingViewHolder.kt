package com.dev.agenda.Adapters

import android.view.View
import android.widget.TextView

import com.dev.agenda.R

import androidx.recyclerview.widget.RecyclerView


class MeetingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var itemVew: View
    var statusView: View
    var statusLine: View
    var txtMeetingTitle: TextView
    var txtDayTime: TextView
    var txtStatus: TextView
    var txtLocationTitle: TextView
    var txtAgendasCount: TextView


    init {
        this.itemVew = itemView
        statusLine = itemView.findViewById(R.id.view_meeting_status_line)
        statusView = itemView.findViewById(R.id.view_meeting_status)
        this.itemVew = itemView

        txtMeetingTitle = itemView.findViewById(R.id.txt_meeting_title)
        txtLocationTitle = itemView.findViewById(R.id.txt_location_title)
        txtDayTime = itemView.findViewById(R.id.txt_day_time)
        txtAgendasCount = itemView.findViewById(R.id.txt_agendas)
        txtStatus = itemView.findViewById(R.id.txt_meeting_status)


    }


}
