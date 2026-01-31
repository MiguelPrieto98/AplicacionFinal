package es.etg.pmdm.aplicacionfinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import es.etg.pmdm.aplicacionfinal.data.AppDatabase
import es.etg.pmdm.aplicacionfinal.data.Item
import es.etg.pmdm.aplicacionfinal.databinding.ActivityMenuBinding
import kotlinx.coroutines.launch

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var db: AppDatabase   // ← Declaración correcta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. ViewBinding correcto para activity_menu.xml
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Inicializar Room ANTES de usarla
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mi_db"
        ).build()

        // 3. Ahora sí puedes usar db
        lifecycleScope.launch {

            db.itemDao().deleteAll()

            db.itemDao().insertAll(
                listOf(
                    Item(1, "Prueba 1", "Descripción 1"),
                    Item(2, "Prueba 2", "Descripción 2")
                )
            )

            val datos = db.itemDao().getAll()
            println("ROOM FUNCIONA: $datos")
        }
    }
}
