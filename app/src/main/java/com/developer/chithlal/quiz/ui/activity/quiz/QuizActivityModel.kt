package com.developer.chithlal.quiz.ui.activity.quiz

import com.developer.chithlal.quiz.model.Batch
import com.developer.chithlal.quiz.dependencyinjection.ComponentFactory
import com.developer.chithlal.quiz.network.NetworkHandler
import com.developer.chithlal.quiz.network.NetworkHandler.*

import javax.inject.Inject

class QuizActivityModel: QuizActivityContract.Model, Publisher {

   @Inject lateinit var  networkHandler: NetworkHandler
    private lateinit var presenter: QuizActivityContract.Presenter
    //network call for Batch
    override fun fetchBatch(presenter: QuizActivityContract.Presenter) {
        this.presenter = presenter
        ComponentFactory.getNetworkComponent().inject(this)
        networkHandler.publisher = this
        networkHandler.getBatches()
    }
    //On data loaded callback
    override fun publishData(result: Result) {
        presenter.onBatchArrived(result.data as Batch)
    }

    override fun onError(message: String) {
        presenter.showMessage(message)
    }
}