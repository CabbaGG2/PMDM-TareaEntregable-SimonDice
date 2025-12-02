package jc.dam.damiandice

import java.time.LocalDateTime
import java.util.Date

class ControllerK: Controller{
    fun submitScore(score: Int, newDate: Date): Int {
        Record.updateRecord(score, LocalDateTime.now())
        return Record.getRecord()
    }

    fun getRecord(): Record {
        return Record
    }

}