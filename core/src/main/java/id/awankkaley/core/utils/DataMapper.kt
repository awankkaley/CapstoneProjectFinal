package id.awankkaley.core.utils

import id.awankkaley.core.data.local.FavoriteEntity
import id.awankkaley.core.data.local.PopularEntity
import id.awankkaley.core.data.remote.response.PopularItem
import id.awankkaley.core.domain.model.Popular

object DataMapper {
    fun mapResponsesToEntities(input: List<PopularItem>): List<PopularEntity> {
        val popularList = ArrayList<PopularEntity>()
        input.map {
            val popular = PopularEntity(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                adult = it.adult,
                voteCount = it.voteCount
            )
            popularList.add(popular)
        }
        return popularList
    }

    fun mapEntitiesToDomain(input: List<PopularEntity>): List<Popular> =
        input.map {
            Popular(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                adult = it.adult,
                voteCount = it.voteCount
            )
        }


    fun mapDomainToEntity(it: Popular) = PopularEntity(
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        title = it.title,
        genreIds = it.genreIds,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        releaseDate = it.releaseDate,
        popularity = it.popularity,
        voteAverage = it.voteAverage,
        id = it.id,
        adult = it.adult,
        voteCount = it.voteCount
    )
    fun mapDomainToFavEntity(it: Popular) = FavoriteEntity(
        overview = it.overview,
        originalLanguage = it.originalLanguage,
        originalTitle = it.originalTitle,
        video = it.video,
        title = it.title,
        genreIds = it.genreIds,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        releaseDate = it.releaseDate,
        popularity = it.popularity,
        voteAverage = it.voteAverage,
        id = it.id,
        adult = it.adult,
        voteCount = it.voteCount
    )


    fun mapResponseToDomain(input: List<PopularItem>): List<Popular> =
        input.map {
            Popular(
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.id,
                adult = it.adult,
                voteCount = it.voteCount
            )
        }
}