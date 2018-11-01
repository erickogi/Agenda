package com.dev.agenda.Data

import android.app.Application
import android.os.AsyncTask

import com.dev.agenda.Models.AgendaModel
import com.dev.agenda.Models.MeetingAgendaModel
import com.dev.agenda.Models.MeetingModel

import androidx.lifecycle.LiveData

class MeetingRepo(application: Application) {
    private val dao: MeetingDao
    private val meetings: LiveData<List<MeetingAgendaModel>>? = null
    private val meeting: LiveData<MeetingAgendaModel>? = null


    private val db: AGDatabase


    init {
        db = AGDatabase.getDatabase(application)!!
        dao = db.meetingDao()
    }


    fun fetchAll(): LiveData<List<MeetingAgendaModel>> {

        return dao.fetchAll()

    }


    fun insert(model: MeetingModel) {

        insertMeetingAsyncTask(dao).execute(model)
    }

    fun insert(model: AgendaModel) {

        insertAgendaAsyncTask(dao).execute(model)
    }

    fun update(model: MeetingModel) {

        updateMeetingAsyncTask(dao).execute(model)
    }

    fun update(model: AgendaModel) {

        updateAgendaAsyncTask(dao).execute(model)
    }

    fun delete(meetingModel: MeetingModel) {

        deleteMeetingAsyncTask(dao).execute(meetingModel)
    }

    fun delete(model: AgendaModel) {

        deleteAgendaAsyncTask(dao).execute(model)
    }


    internal class insertMeetingAsyncTask(private val mAsyncTaskDao: MeetingDao) : AsyncTask<MeetingModel, Void, Boolean>() {

        override fun doInBackground(vararg params: MeetingModel): Boolean? {
            try {
                mAsyncTaskDao.insertMeeting(params[0])
            } catch (nm: Exception) {

            }

            return true

        }

        override fun onPostExecute(aBoolean: Boolean?) {
            super.onPostExecute(aBoolean)


        }
    }

    private class insertAgendaAsyncTask internal constructor(private val mAsyncTaskDao: MeetingDao) : AsyncTask<AgendaModel, Void, Boolean>() {

        override fun doInBackground(vararg params: AgendaModel): Boolean? {
            try {
                mAsyncTaskDao.insertAgenda(params[0])
            } catch (nm: Exception) {

            }

            return true

        }

        override fun onPostExecute(aBoolean: Boolean?) {
            super.onPostExecute(aBoolean)


        }
    }


    private class updateMeetingAsyncTask internal constructor(private val mAsyncTaskDao: MeetingDao) : AsyncTask<MeetingModel, Void, Boolean>() {

        override fun doInBackground(vararg params: MeetingModel): Boolean? {
            try {
                mAsyncTaskDao.updateMeeting(params[0])
            } catch (nm: Exception) {

            }

            return true

        }

        override fun onPostExecute(aBoolean: Boolean?) {
            super.onPostExecute(aBoolean)


        }
    }

    private class updateAgendaAsyncTask internal constructor(private val mAsyncTaskDao: MeetingDao) : AsyncTask<AgendaModel, Void, Boolean>() {

        override fun doInBackground(vararg params: AgendaModel): Boolean? {
            try {
                mAsyncTaskDao.updateAgenda(params[0])
            } catch (nm: Exception) {

            }

            return true

        }

        override fun onPostExecute(aBoolean: Boolean?) {
            super.onPostExecute(aBoolean)


        }
    }


    private class deleteMeetingAsyncTask internal constructor(private val mAsyncTaskDao: MeetingDao) : AsyncTask<MeetingModel, Void, Boolean>() {

        override fun doInBackground(vararg params: MeetingModel): Boolean? {
            try {
                mAsyncTaskDao.deleteMeeting(params[0])
            } catch (nm: Exception) {

            }

            return true

        }

        override fun onPostExecute(aBoolean: Boolean?) {
            super.onPostExecute(aBoolean)


        }
    }

    private class deleteAgendaAsyncTask internal constructor(private val mAsyncTaskDao: MeetingDao) : AsyncTask<AgendaModel, Void, Boolean>() {

        override fun doInBackground(vararg params: AgendaModel): Boolean? {
            try {
                mAsyncTaskDao.deleteAgenda(params[0])
            } catch (nm: Exception) {

            }

            return true

        }

        override fun onPostExecute(aBoolean: Boolean?) {
            super.onPostExecute(aBoolean)


        }
    }


}
