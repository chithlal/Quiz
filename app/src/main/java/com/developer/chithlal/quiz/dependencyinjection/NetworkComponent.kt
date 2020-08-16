package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.ui.activity.quiz.QuizActivityModel
import com.developer.chithlal.quiz.ui.activity.quizsession.QuizSessionModel
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface NetworkComponent {

   /* @Component.Factory
    interface Factory {
        // Takes an instance of AppComponent when creating
        // an instance of LoginComponent
        fun create(): NetworkComponent
    }*/

    fun inject(model:QuizActivityModel)
    fun inject(model: QuizSessionModel)
}