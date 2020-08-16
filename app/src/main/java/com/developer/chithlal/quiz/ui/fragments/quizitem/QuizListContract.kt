package com.developer.chithlal.quiz.ui.fragments.quizitem

import com.developer.chithlal.quiz.model.OngoingData
import com.developer.chithlal.quiz.model.QuizItem

interface QuizListContract {

    interface View{

        fun showList(quizList:List<QuizItem>)
        fun showMessage(message: String)
        fun getQuizData():OngoingData
        fun showEmptyQuiz()
    }
    interface Presenter{

        fun setupView(view: View)
    }
}