package com.ktz.cinephilia.data.source.remote.api

import com.ktz.cinephilia.BuildConfig

object TmdbApiConfigs {

    object Base {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    object Movies {
        const val TOP_RATED = "movie/top_rated"
        const val NOW_PLAYING = "movie/now_playing"
        const val POPULAR = "movie/popular"
        const val UPCOMING = "movie/upcoming"
        const val GENRE = "genre/movie/list"
    }

    object TV {
        const val TOP_RATED_TV = "tv/top_rated"
        const val AIRING_TODAY = "tv/airing_today"
        const val POPULAR = "tv/popular"
    }

    object Param {
        const val PARAM_API_KEY = "api_key"
        const val PARAM_PAGE = "page"
    }
}
