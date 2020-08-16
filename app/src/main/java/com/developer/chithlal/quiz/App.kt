package com.developer.chithlal.quiz

import android.app.Application
import com.developer.chithlal.quiz.dependencyinjection.*

@Suppress("DEPRECATION")
class App: Application() {
    val component: AppComponent by lazy {

        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .quizActivityModule(QuizActivityModule())
            .quizSessionModule(QuizSessionModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}