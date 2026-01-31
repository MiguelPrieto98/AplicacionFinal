package es.etg.pmdm.aplicacionfinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey val id: Int,
    val titulo: String,
    val descripcion: String
)
