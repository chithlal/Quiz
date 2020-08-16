package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App){
    @Provides
    @Singleton
    fun provideContext() = app
}
