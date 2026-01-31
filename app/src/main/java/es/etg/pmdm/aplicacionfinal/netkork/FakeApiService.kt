package es.etg.pmdm.aplicacionfinal.network

import es.etg.pmdm.aplicacionfinal.data.Item

class FakeApiService : ApiService {

    override suspend fun getItems(): List<Item> {
        return listOf(
            Item(1, "Fake 1", "Dato simulado"),
            Item(2, "Fake 2", "Dato simulado")
        )
    }
}
