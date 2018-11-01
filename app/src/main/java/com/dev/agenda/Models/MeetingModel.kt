package com.dev.agenda.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MeetingModel {

    @PrimaryKey(autoGenerate = true)
    var meetingId: Int = 0
    var code: String? = null
    var title: String? = null
    var subTitle: String? = null

    var status: Int=0;

    var location: String? = null
    var locationDescription: String? = null

    var startTimeStamp: String? = null
    var endTimeStamp: String? = null

    var lat: String? = null
    var lon: String? = null

    var userCode: String? = null
}
