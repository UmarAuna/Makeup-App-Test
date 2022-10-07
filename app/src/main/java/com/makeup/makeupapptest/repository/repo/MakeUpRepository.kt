package com.makeup.makeupapptest.repository.repo

import com.makeup.makeupapptest.home.models.ProductListItem
import com.makeup.makeupapptest.repository.local.MakeUpDAO
import com.makeup.makeupapptest.repository.remote.MakeUpApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class MakeUpRepository : KoinComponent {

    private val makeUpApiService: MakeUpApiService by inject()
    private val makeUpDAO: MakeUpDAO by inject()

    suspend fun getMakeUp(): Response<List<ProductListItem>> {
        return try {
            withContext(Dispatchers.IO) {
                val makeup = makeUpApiService.getMakeUp()
                makeup.body()?.let {
                    makeUpDAO.deleteAll()
                    makeUpDAO.add(it)
                }
                makeup
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}
