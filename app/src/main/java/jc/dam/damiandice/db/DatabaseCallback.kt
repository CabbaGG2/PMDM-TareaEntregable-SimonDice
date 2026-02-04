
package jc.dam.damiandice.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import jc.dam.damiandice.db.entity.Record
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseCallback(
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch(Dispatchers.IO) {
            RecordDB.INSTANCE?.let { database ->
                populateDatabase(database.recordDao())
            }
        }
    }

    private suspend fun populateDatabase(recordDao: RecordDao) {
        val initialRecord = Record(
            name = "pepe",
            score = 0,
            time = "00:00",
            timestamp = System.currentTimeMillis()
        )
        recordDao.insert(initialRecord)
    }
}
