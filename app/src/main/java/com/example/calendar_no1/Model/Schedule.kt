package com.example.calendar_no1.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Timestamp

@Entity
data class Schedule(
    @PrimaryKey var id: Int,
    val title: String?,
    val place: String?,
    val memo: String?,
    val date: Date,
    @ColumnInfo(name = "start_date_time") val startDateTime: String?,
    @ColumnInfo(name = "end_date_time") val endDateTime: String?,
    val timestamp: Timestamp
)