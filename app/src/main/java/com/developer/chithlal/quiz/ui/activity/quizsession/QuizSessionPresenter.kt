package com.developer.chithlal.quiz.ui.activity.quizsession

import android.os.CountDownTimer
import com.developer.chithlal.quiz.model.Data
import com.developer.chithlal.quiz.model.QuizData
import com.developer.chithlal.quiz.model.QuizResult
import com.developer.chithlal.quiz.model.QuizTime
import com.developer.chithlal.quiz.ui.adapter.Answer
import java.util.*

class QuizSessionPresenter(val model: QuizSessionContract.Model) : QuizSessionContract.Presenter {

    private var isNetworkAvailble: Boolean = true
    private lateinit var elapsedTime: String
    private lateinit var timer: CountDownTimer
    private lateinit var mData: QuizData
    private lateinit var view: QuizSessionContract.View
    private lateinit var quizList: List<Data>
    private var currentQustionNumber = -1
    private lateinit var answerList: Array<Answer?>

    override fun onStartClicked() {
        view.updateProgressBar(true)
        model.getQuiz(this)
    }

    override fun setupView(view: QuizSessionContract.View) {
        this.view = view
        view.applyView()
    }

    //When Quiz data loaded from network
    override fun onQuizReady(data: QuizData) {
        view.updateProgressBar(false)
        view.startQuiz()
        mData = data
        quizList = data.data
        answerList = Array(quizList.size, { null })
        startTimer((mData.quiz.duration * 60000).toLong())
        onNextQstnClicked(null)
    }
    override fun onQuizFinishClicked() {
        if (isNetworkAvailble) {
            val result = calculateScore()
            view.finishQuiz(result)
            model.updateFirbaseDatabase(answerList)
        } else view.showDialog("Please connect to Internet to finish test")
    }
    override fun showMessage(message: String) {
        view.showMessage(message)
    }

    override fun onNextQstnClicked(answer: Answer?) {
        if (answer != null) {
            answerList[answer.qNo - 1] = answer
            model.updateFirbaseDatabase(answerList)
        }
        if (currentQustionNumber < quizList.size) {
            currentQustionNumber++
            view.onNextQuestion(
                quizList.get(currentQustionNumber),
                currentQustionNumber + 1, answerList[currentQustionNumber]
            )
        } else view.showMessage("Please click Finish to See result!")

    }

    //Delegates Button clicks to show previous question
    override fun onPreviousQstnClicked() {
        if (currentQustionNumber > 0) {
            currentQustionNumber--
            view.onNextQuestion(
                quizList.get(currentQustionNumber),
                currentQustionNumber + 1, answerList[currentQustionNumber]
            )
        } else view.showMessage("First Question!")
    }

    override fun onNetworkChanged(isConnected: Boolean) {
        isNetworkAvailble = isConnected
        view.showNetworkError(isConnected)
    }

    //Starts timer
    fun startTimer(time: Long) {
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val min = (millisUntilFinished / 1000) / 60
                val sec = (millisUntilFinished / 1000) % 60

                view.updateRemainingTime(QuizTime(min, sec))
                val eTimeSec = time - millisUntilFinished
                val eMin = (eTimeSec / 1000) / 60
                val eSec = (eTimeSec / 1000) % 60
                elapsedTime = "$eMin mins $eSec sec"
            }

            override fun onFinish() {
                onQuizFinishClicked()
            }
        }
        timer.start()
    }
    private fun calculateScore(): QuizResult {
        var total = 0
        var correct = 0
        var wrong = 0
        var attempts = 0
        for (item in answerList) {

            if (item != null) {
                attempts++
                val qNo = item.qNo
                val answes = item.answerList
                val options = quizList[qNo].options
                val correctAnswerSet = HashSet<Int>()
                for ((i, option) in options.withIndex()) {
                    if (option.correct)
                        correctAnswerSet.add(i)
                }
                if (correctAnswerSet.containsAll(answes)) {
                    total += 4
                    correct++
                } else {
                    total -= 1
                    wrong++
                }
            }
        }

        return QuizResult(total, correct, wrong, elapsedTime, attempts)
    }

}