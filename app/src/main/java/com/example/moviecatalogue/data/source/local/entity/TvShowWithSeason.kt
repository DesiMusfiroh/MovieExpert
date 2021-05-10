package com.example.moviecatalogue.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TvShowWithSeason (
    @Embedded
    var mTvShow : TvShowEntity,

    @Relation(parentColumn = "id", entityColumn = "tvShowId")
    var mSeasons : List<SeasonEntity>
)