package es.etg.pmdm.aplicacionfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import es.etg.pmdm.aplicacionfinal.data.AppDatabase
import es.etg.pmdm.aplicacionfinal.databinding.ActivityMenuBinding
import es.etg.pmdm.aplicacionfinal.network.RetrofitClient
import kotlinx.coroutines.launch

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var db: AppDatabase

    private val PREFS = "prefs"
    private val KEY_PRIMERA_VEZ = "primera_vez"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val primeraVez = prefs.getBoolean(KEY_PRIMERA_VEZ, true)

        if (primeraVez) {
            cargarDesdeRestYGuardarEnRoom()
            prefs.edit().putBoolean(KEY_PRIMERA_VEZ, false).apply()
        } else {
            cargarDesdeRoom()
        }

        binding.btRefres.setOnClickListener {
            cargarDesdeRoom()   // SOLO ROOM, NUNCA REST
        }
    }

    private fun cargarDesdeRoom() {
        lifecycleScope.launch {
            val datos = db.itemDao().getAll()
            val listaStrings = datos.map { "${it.titulo} - ${it.descripcion}" }

            val adapter = ArrayAdapter(
                this@MenuActivity,
                android.R.layout.simple_list_item_1,
                listaStrings
            )

            binding.listView.adapter = adapter
        }
    }

    private fun cargarDesdeRestYGuardarEnRoom() {
        lifecycleScope.launch {

            val datosRest = RetrofitClient.api.getItems()

            db.itemDao().deleteAll()
            db.itemDao().insertAll(datosRest)

            cargarDesdeRoom()
        }
    }
}
