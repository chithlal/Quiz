package com.developer.chithlal.quiz.ui.activity.quiz

import com.developer.chithlal.quiz.App
import com.developer.chithlal.quiz.model.Batch

interface QuizActivityContract {

    interface View{
        fun showMessage(message: String)
        fun onConnectivityUpdate()
        fun applyView(batch: Batch)
        fun getApp(): App

    }

    interface Presenter{
        fun setupView(view: View)
        fun onBatchArrived(batch: Batch)
        fun showMessage(message: String)
        fun getApplication():App
    }

    interface Model{
        fun fetchBatch(presenter: Presenter)
    }
}