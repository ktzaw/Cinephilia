package com.ktz.cinephilia.data.source.remote

import com.ktz.cinephilia.BuildConfig

object TmdbApiConfigs {

    object Base {
        const val BASE_URL = BuildConfig.BASE_URL
        const val API_VERSION = "3"
    }

    object Movies {
        const val TOP_RATED = "movie/top_rated"
        const val NOW_PLAYING = "movie/now_playing"
        const val POPULAR = "movie/popular"
        const val UPCOMING = "movie/upcoming"
    }

    object Param {
        const val PARAM_API_KEY = "api_key"
        const val PARAM_PAGE = "page"
    }
}
