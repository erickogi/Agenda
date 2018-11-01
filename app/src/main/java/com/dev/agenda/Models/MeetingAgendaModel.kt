package com.dev.agenda.Models

import androidx.room.Embedded
import androidx.room.Relation

class MeetingAgendaModel {

    @Embedded
    lateinit var meetingModel: MeetingModel


    @Relation(parentColumn = "code", entityColumn = "meetingCode")
    lateinit var agendaModels: List<AgendaModel>

}
