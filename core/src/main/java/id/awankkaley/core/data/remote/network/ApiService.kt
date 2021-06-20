package id.awankkaley.core.data.remote.network

import id.awankkaley.core.util.Util
import id.awankkaley.core.data.remote.response.PopularListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Util.popular)
    suspend fun getPopular(
        @Query("api_key") string: String
    ): PopularListResponse

    @GET(Util.search)
    suspend fun searchMovies(
        @Query("api_key") key: String,
        @Query("query") search: String

    ): PopularListResponse

}
