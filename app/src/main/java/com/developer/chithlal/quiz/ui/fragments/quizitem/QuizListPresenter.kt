package com.developer.chithlal.quiz.ui.fragments.quizitem

class QuizListPresenter : QuizListContract.Presenter {
    override fun setupView(view: QuizListContract.View) {
        val list = view.getQuizData().ongoingList
        if (list.isNotEmpty())
            view.showList(list)
        else view.showEmptyQuiz()
    }
}