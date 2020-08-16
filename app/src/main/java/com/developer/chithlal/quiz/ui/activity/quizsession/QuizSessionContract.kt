package com.developer.chithlal.quiz.ui.activity.quizsession

import com.developer.chithlal.quiz.model.Data
import com.developer.chithlal.quiz.model.QuizData
import com.developer.chithlal.quiz.model.QuizResult
import com.developer.chithlal.quiz.model.QuizTime
import com.developer.chithlal.quiz.ui.adapter.Answer

interface QuizSessionContract {

    interface View {
        fun applyView()
        fun startQuiz()
        fun onNextQuestion(question:Data, qstnNumber: Int,answer:Answer?)

        fun showNetworkError(isConnected:Boolean)
        fun showMessage(message: String)
        fun finishQuiz(result: QuizResult)
        fun updateRemainingTime(time:QuizTime)
        fun showDialog(message: String)
    }

    interface Presenter{
        fun onStartClicked()
        fun onQuizFinishClicked()
        fun setupView(view: View)
        fun onQuizReady(data: QuizData)
        fun showMessage(message: String)
        fun onNextQstnClicked(answer: Answer?)
        fun onPreviousQstnClicked()
        fun onNetworkChanged(isConnected: Boolean)
    }
    interface Model{
        fun getQuiz(presenter : Presenter)
        fun updateFirbaseDatabase(answerList: Array<Answer?>)
    }
}