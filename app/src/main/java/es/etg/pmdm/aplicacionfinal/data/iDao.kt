package es.etg.pmdm.aplicacionfinal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    suspend fun getAll(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lista: List<Item>)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}
