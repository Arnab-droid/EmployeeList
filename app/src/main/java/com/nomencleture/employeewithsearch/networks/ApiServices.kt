package com.nomencleture.employeewithsearch.networks

import com.nomencleture.employeewithsearch.models.EmployeeModels
import com.nomencleture.employeewithsearch.models.EmployeeModelsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface ApiService {

    companion object {
        // Set per build flavor (prod/uat) via BuildConfig.BASE_URL — see app/build.gradle.kts
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("users")
    suspend fun getAllEmployees(
    ): Response<EmployeeModels>
}