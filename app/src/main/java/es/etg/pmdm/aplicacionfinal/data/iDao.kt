package es.etg.pmdm.aplicacionfinal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM Item")
    suspend fun getAll(): List<Item>

    @Insert
    suspend fun insertAll(items: List<Item>)

    @Query("DELETE FROM Item")
    suspend fun deleteAll()
}
