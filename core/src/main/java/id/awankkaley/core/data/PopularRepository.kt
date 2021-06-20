package id.awankkaley.core.data


import id.awankkaley.core.data.local.LocalDataSource
import id.awankkaley.core.data.remote.RemoteDataSource
import id.awankkaley.core.data.remote.network.ApiResponse
import id.awankkaley.core.data.remote.response.PopularItem
import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.domain.repository.IPopularRepository
import id.awankkaley.core.utils.AppExecutors
import id.awankkaley.core.utils.DataMapper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PopularRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPopularRepository {

    override fun getAllPopular(): Flow<Resource<List<Popular>>> =
        object : NetworkBoundResource<List<Popular>, List<PopularItem>>() {
            override fun loadFromDB(): Flow<List<Popular>> {
                return localDataSource.getAllPopular().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Popular>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PopularItem>>> =
                remoteDataSource.getPopular()

            override suspend fun saveCallResult(data: List<PopularItem>) {
                val popularList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPopular(popularList)
            }
        }.asFlow()

    override fun searchMovies(query: String): Flow<Resource<List<Popular>>> {
        return flow {
            emit(Resource.Loading())
            val apiResponse = remoteDataSource.searchMovies(query)
            apiResponse.collect {
                when (it) {
                    is ApiResponse.Success -> {
                        val data = DataMapper.mapResponseToDomain(it.data)
                        emit(Resource.Success(data))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Error<List<Popular>>("EMPTY"))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error<List<Popular>>(it.errorMessage))
                    }
                }
            }

        }
    }


    override fun getFavoritePopular(): Flow<List<Popular>> {
        return localDataSource.getFavoritePopular().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoritePopular(popular: Popular, state: Boolean) {
        val popularEntity = DataMapper.mapDomainToEntity(popular)
        appExecutors.diskIO().execute { localDataSource.setFavoritePopular(popularEntity, state) }
    }
}

