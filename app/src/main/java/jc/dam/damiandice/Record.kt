package jc.dam.damiandice

import java.time.LocalDateTime
import java.util.Date

object Record {
    var record: Int = 0
    var fecha: LocalDateTime = LocalDateTime.now()


    fun updateRecord(newRecord: Int, newDate: LocalDateTime) {
        if (newRecord > record) {
            record = newRecord
            fecha = newDate
        }
    }
}