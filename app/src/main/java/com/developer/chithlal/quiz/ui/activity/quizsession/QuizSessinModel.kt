package com.developer.chithlal.quiz.ui.activity.quizsession

import com.developer.chithlal.quiz.dependencyinjection.ComponentFactory
import com.developer.chithlal.quiz.model.QuizData
import com.developer.chithlal.quiz.ui.adapter.Answer
import com.developer.chithlal.quiz.network.NetworkHandler
import javax.inject.Inject

class QuizSessionModel: QuizSessionContract.Model,
    NetworkHandler.Publisher {

    private lateinit var presenter: QuizSessionContract.Presenter

    @Inject
    lateinit var networkHandler: NetworkHandler
    // fetch quiz from api
    override fun getQuiz(presenter: QuizSessionContract.Presenter) {
        this.presenter = presenter
        ComponentFactory.getNetworkComponent().inject(this)
        networkHandler.publisher = this
        networkHandler.getQuiz()
    }

    //Update firabase Collection on progress change
    override fun updateFirbaseDatabase(answerList: Array<Answer?>) {
        networkHandler.pushFirebaseData(answerList)
    }

    // network callback
    override fun publishData(result: NetworkHandler.Result) {
       presenter.onQuizReady(result.data as QuizData)
    }

    override fun onError(message: String) {
       presenter.showMessage(message)
    }

}