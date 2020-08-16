package com.developer.chithlal.quiz.dependencyinjection

import com.developer.chithlal.quiz.dependencyinjection.DaggerNetworkComponent
import com.developer.chithlal.quiz.dependencyinjection.NetworkComponent
import com.developer.chithlal.quiz.dependencyinjection.RetrofitModule

class ComponentFactory {
    companion object{
        fun getNetworkComponent(): NetworkComponent{
            return DaggerNetworkComponent
                .builder()
                .retrofitModule(RetrofitModule())
                .build()
        }
        fun getFragmentComponent(): FragmentComponent{
            return DaggerFragmentComponent
                .builder()
                .quizListFragmentModule(QuizListFragmentModule())
                .build()
        }
    }
}