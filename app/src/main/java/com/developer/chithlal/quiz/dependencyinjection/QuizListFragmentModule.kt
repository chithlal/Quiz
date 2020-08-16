package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.ui.fragments.quizitem.QuizListPresenter
import dagger.Module
import dagger.Provides

@Module
class QuizListFragmentModule{
    @Provides
    fun providePresenter() = QuizListPresenter()
}