package com.developer.chithlal.quiz.ui.activity.quiz

import com.developer.chithlal.quiz.App
import com.developer.chithlal.quiz.model.Batch


class QuizActivityPresenter(val model: QuizActivityContract.Model): QuizActivityContract.Presenter {
    private lateinit var view: QuizActivityContract.View
    override fun setupView(view: QuizActivityContract.View) {
       this.view = view
        model.fetchBatch(this)
    }

    override fun onBatchArrived(batch: Batch) {
        view.applyView(batch)
    }

    override fun showMessage(message: String) {
       view.showMessage(message)
    }

    override fun getApplication(): App {
        return view.getApp()
    }
}