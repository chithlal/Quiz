package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.ui.fragments.quizitem.OngoingFragment
import dagger.Component

@Component(modules = [QuizListFragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: OngoingFragment)
}