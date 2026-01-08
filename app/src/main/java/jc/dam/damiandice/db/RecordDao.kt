package jc.dam.damiandice.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import jc.dam.damiandice.db.entity.Record;


@Dao
interface RecordDao {
    @Insert
    suspend fun insert(record: Record)

    @Query("SELECT * FROM record_table ORDER BY score DESC LIMIT 1") // se obtiene el record más alto
    suspend fun getRecord(): Record?
}