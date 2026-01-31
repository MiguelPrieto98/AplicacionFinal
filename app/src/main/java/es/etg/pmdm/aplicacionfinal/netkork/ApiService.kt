package es.etg.pmdm.aplicacionfinal.network

import retrofit2.http.GET
import es.etg.pmdm.aplicacionfinal.data.Item

interface ApiService {
    @GET("items.json")
    suspend fun getItems(): List<Item>
}
