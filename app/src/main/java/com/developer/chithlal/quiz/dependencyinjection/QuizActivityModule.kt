package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.ui.activity.quiz.QuizActivityContract
import com.developer.chithlal.quiz.ui.activity.quiz.QuizActivityModel
import com.developer.chithlal.quiz.ui.activity.quiz.QuizActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class QuizActivityModule {

    @Provides
    fun providePresenter(model: QuizActivityContract.Model): QuizActivityContract.Presenter{
        return QuizActivityPresenter(model)
    }

    @Provides
    fun proivideModel(): QuizActivityContract.Model{
        return QuizActivityModel()
    }
}