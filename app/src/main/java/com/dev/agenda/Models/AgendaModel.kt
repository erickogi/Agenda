package com.dev.agenda.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AgendaModel {

    @PrimaryKey(autoGenerate = true)
    var agendaId: Int = 0
    var meetingCode: String? = null
    var agendaTitle: String? = null
    var agendaDescription: String? = null
}
