package es.etg.pmdm.aplicacionfinal.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id: Int,
    val nombre: String,
    val descripcion: String
)
