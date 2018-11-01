package com.dev.agenda.Data


import android.content.Context


import com.dev.agenda.Models.AgendaModel
import com.dev.agenda.Models.MeetingModel

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MeetingModel::class, AgendaModel::class), version = 1)
abstract class AGDatabase : RoomDatabase() {

    abstract fun meetingDao(): MeetingDao

    companion object {

        private var INSTANCE: AGDatabase? = null
        fun getDatabase(context: Context): AGDatabase? {

            if (INSTANCE == null) {
                synchronized(AGDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AGDatabase::class.java, "ag_database")
                                .fallbackToDestructiveMigration()

                                .allowMainThreadQueries()
                                .build()

                    }
                }
            }
            return this.INSTANCE
        }
    }


}
