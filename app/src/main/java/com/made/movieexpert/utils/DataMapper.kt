package com.made.movieexpert.utils

import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.data.source.remote.response.MovieResponse
import com.made.movieexpert.data.source.remote.response.TvShowResponse
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.model.TvShow

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                it.id,
                it.name,
                it.desc,
                it.poster,
                it.backdrop,
                it.date,
                it.popularity,
                it.rating
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.name,
                it.desc,
                it.poster,
                it.backdrop,
                it.date,
                it.popularity,
                it.rating,
                it.favorited,
            )
        }

    fun mapMovieDomainToEntity(it: Movie) = MovieEntity(
        it.id,
        it.name,
        it.desc,
        it.poster,
        it.backdrop,
        it.date,
        it.popularity,
        it.rating
    )


    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                    it.id,
                    it.name,
                    it.desc,
                    it.poster,
                    it.backdrop,
                    it.date,
                    it.popularity,
                    it.rating
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                TvShow(
                        it.id,
                        it.name,
                        it.desc,
                        it.poster,
                        it.backdrop,
                        it.date,
                        it.popularity,
                        it.rating,
                        it.favorited
                )
            }

    fun mapTvShowDomainToEntity(it: TvShow) = TvShowEntity(
            it.id,
            it.name,
            it.desc,
            it.poster,
            it.backdrop,
            it.date,
            it.popularity,
            it.rating
    )
}