package com.example.mytzilleri

import android.app.Application
import com.example.mytzilleri.chat.DataRepositoryChat
import com.example.mytzilleri.product.DataRepository

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DataRepository.init(this)
        DataRepositoryChat.init(this)
    }
}