package com.makeup.makeupapptest.util

class EventLiveData : OneTimeLiveData<Unit>() {

    fun post() {
        super.postValue(Unit)
    }
}