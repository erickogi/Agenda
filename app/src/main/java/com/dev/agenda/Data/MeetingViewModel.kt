package com.dev.agenda.Data

import android.app.Application
import android.os.AsyncTask

import com.dev.agenda.Models.AgendaModel
import com.dev.agenda.Models.MeetingAgendaModel
import com.dev.agenda.Models.MeetingModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MeetingViewModel(application: Application) : AndroidViewModel(application) {
    internal var repo: MeetingRepo

    init {
        repo = MeetingRepo(application)
    }

    fun fetchAll(): LiveData<List<MeetingAgendaModel>> {
        return repo.fetchAll()
    }


    fun insert(model: MeetingModel) {

        repo.insert(model)
    }

    fun insert(model: AgendaModel) {

        repo.insert(model)
    }

    fun update(model: MeetingModel) {

        repo.update(model)
    }

    fun update(model: AgendaModel) {

        repo.update(model)
    }

    fun delete(meetingModel: MeetingModel) {

        repo.delete(meetingModel)
    }

    fun delete(model: AgendaModel) {

        repo.delete(model)
    }

}
