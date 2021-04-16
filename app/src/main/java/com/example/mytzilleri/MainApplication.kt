package com.example.mytzilleri

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DataRepository.init(this)
        DataRepositoryChat.init(this)
    }
}