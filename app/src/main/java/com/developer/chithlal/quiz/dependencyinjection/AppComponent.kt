package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.App
import com.developer.chithlal.quiz.ui.activity.quiz.QuizActivity
import com.developer.chithlal.quiz.ui.activity.quizsession.QuizSessionActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,QuizActivityModule::class,QuizSessionModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(activity: QuizActivity)
    fun inject(activity: QuizSessionActivity)
}