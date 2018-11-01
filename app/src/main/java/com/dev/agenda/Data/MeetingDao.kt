package com.dev.agenda.Data

import com.dev.agenda.Models.AgendaModel
import com.dev.agenda.Models.MeetingAgendaModel
import com.dev.agenda.Models.MeetingModel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface MeetingDao {


    @Query("SELECT * from MeetingModel")
    fun fetchAll(): LiveData<List<MeetingAgendaModel>>


    @Insert(onConflict = REPLACE)
    fun insertMeeting(model: MeetingModel)

    @Insert(onConflict = REPLACE)
    fun insertAgenda(model: AgendaModel)


    @Update
    fun updateMeeting(model: MeetingModel)

    @Delete
    fun deleteMeeting(model: MeetingModel)


    @Update
    fun updateAgenda(model: AgendaModel)

    @Delete
    fun deleteAgenda(model: AgendaModel)

}
