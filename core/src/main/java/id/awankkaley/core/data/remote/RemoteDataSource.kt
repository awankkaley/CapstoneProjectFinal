package id.awankkaley.core.data.remote

import android.util.Log
import id.awankkaley.core.data.remote.network.ApiResponse
import id.awankkaley.core.data.remote.network.ApiService
import id.awankkaley.core.data.remote.response.PopularItem
import id.awankkaley.core.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getPopular(): Flow<ApiResponse<List<PopularItem>>> {
        return flow {
            try {
                val response = apiService.getPopular(Util.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovies(query: String?): Flow<ApiResponse<List<PopularItem>>> {
        return flow {
            try {
                val response = apiService.searchMovies(Util.API_KEY, query.toString())
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

