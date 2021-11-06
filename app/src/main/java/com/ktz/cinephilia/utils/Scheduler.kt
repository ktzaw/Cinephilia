package com.ktz.cinephilia.utils

import io.reactivex.Scheduler

interface Scheduler {

    fun mainThread(): Scheduler
    fun io(): Scheduler

}