package me.gigajet.haicottien.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChangeApi {
    @GET("api/leftcol")
    suspend fun getLeftCol(): Changes

    @GET("api/rightcol")
    suspend fun getRightCol(): Changes

    @GET("api/leftcol/{year}/{month}")
    suspend fun getLeftColAtMonth(
        @Path("year") year: Int,
        @Path("month") month: Int,
    ): Changes

    @GET("api/rightcol/{year}/{month}")
    suspend fun getRightColAtMonth(
        @Path("year") year: Int,
        @Path("month") month: Int,
    ): Changes

    @POST("api/leftcol")
    suspend fun newLeftChange(
        @Body change: Change
    ): Change

    @POST("api/rightcol")
    suspend fun newRightChange(
        @Body change: Change
    ): Change
}
