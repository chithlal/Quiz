package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.ui.activity.quizsession.QuizSessionContract
import com.developer.chithlal.quiz.ui.activity.quizsession.QuizSessionModel
import com.developer.chithlal.quiz.ui.activity.quizsession.QuizSessionPresenter
import dagger.Module
import dagger.Provides

@Module
class QuizSessionModule {

    @Provides
    fun provideQuizSessionPresenter(model: QuizSessionContract.Model): QuizSessionContract.Presenter
            = QuizSessionPresenter(model)

    @Provides
    fun provideModel(): QuizSessionContract.Model = QuizSessionModel()
}