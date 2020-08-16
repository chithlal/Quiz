package com.developer.chithlal.quiz.ui.activity.quizsession

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import com.developer.chithlal.quiz.App
import com.developer.chithlal.quiz.databinding.ActivityQuizSessionBinding
import com.developer.chithlal.quiz.model.*
import com.developer.chithlal.quiz.network.ConnectivityReceiver
import com.developer.chithlal.quiz.ui.activity.result.ResultActivity
import com.developer.chithlal.quiz.ui.adapter.Answer
import com.developer.chithlal.quiz.ui.adapter.QuestionAdapter
import com.developer.chithlal.quiz.util.DefaultConfig
import com.developer.chithlal.quiz.util.DefaultConfig.Companion.ARG_QUIZ_DATA
import com.developer.chithlal.quiz.util.DefaultConfig.Companion.ARG_RESULT
import javax.inject.Inject
@Suppress("DEPRECATION")


class QuizSessionActivity : AppCompatActivity(),
    QuizSessionContract.View,
    ConnectivityReceiver.ConnectivityChangeListener {

    private var quizDetail: QuizDetail? = null
    private lateinit var mBinding: ActivityQuizSessionBinding
    private lateinit var adapter: QuestionAdapter
    private var currQstnNumber = 0

    @Inject
    lateinit var presenter: QuizSessionContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityQuizSessionBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        quizDetail = if (intent != null) {
            intent.getSerializableExtra(ARG_QUIZ_DATA) as QuizDetail
        } else null
        (application as App).component.inject(this)
        presenter.setupView(this)
    }

    override fun onResume() {
        super.onResume()
        //registering Connectivity change listener
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun applyView() {
        mBinding.layoutNormal.visibility = VISIBLE
        mBinding.quizPage.quizPageRoot.visibility = GONE

        if (quizDetail != null) {
            mBinding.textViewQuizTitle.text = quizDetail!!.name
            val duration = quizDetail!!.duration.toString() + " min"
            mBinding.textViewQuizDuration.text = duration
        }

        mBinding.buttonClose.setOnClickListener {
            finish()
        }



        mBinding.buttonStartQuiz.setOnClickListener {
            val isTermsChecked = mBinding.checkBoxTerms.isChecked
            if (isTermsChecked) {
                presenter.onStartClicked()
            }
        }

        mBinding.quizPage.buttonNext.setOnClickListener {
            val answer = adapter.getCurrentAnswer()
            answer?.qNo = currQstnNumber
            presenter.onNextQstnClicked(answer)
        }

        mBinding.quizPage.buttonPrev.setOnClickListener {

            presenter.onPreviousQstnClicked()
        }

        mBinding.quizPage.buttonFinish.setOnClickListener {
            presenter.onQuizFinishClicked()
        }
    }

    override fun startQuiz() {
        mBinding.layoutNormal.visibility = GONE
        mBinding.quizPage.quizPageRoot.visibility = VISIBLE
        // Register broadcast receiver for Connectivity change
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(CONNECTIVITY_ACTION)
        )
    }

    //to update the question and options
    override fun onNextQuestion(question: Data, qstnNumber: Int, answer: Answer?) {

        currQstnNumber = qstnNumber
        mBinding.quizPage.tvQuizType.text = question.questionType
        mBinding.quizPage.webviewQuestion.loadData(question.body, "text/html", "UTF-8")
        mBinding.quizPage.tvQNumber.text = "$qstnNumber"

        setupOptions(question.options, question.getQuestionType(), answer)

    }


    override fun showNetworkError(isConnected: Boolean) {

        if (isConnected) {
            mBinding.quizPage.tvNetworkMessage.visibility = GONE
        } else mBinding.quizPage.tvNetworkMessage.visibility = VISIBLE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, LENGTH_SHORT).show()
    }

    //Called when quiz is about to finish
    override fun finishQuiz(result: QuizResult) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(ARG_RESULT, result)
        startActivity(intent)
        finish()
    }

    //Time update for view
    override fun updateRemainingTime(time: QuizTime) {
        val timeText ="${time.min} min : ${time.second} sec"
        mBinding.quizPage.tvTimer.text = timeText
    }

    override fun showDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun updateProgressBar(show: Boolean) {
        if (show) {
            mBinding.progressBar.visibility = VISIBLE
            mBinding.buttonStartQuiz.visibility = GONE
        }
        else{
            mBinding.progressBar.visibility = GONE
            mBinding.buttonStartQuiz.visibility = VISIBLE
        }
    }

    //Setup options and recyclerview
    fun setupOptions(
        optionList: List<Option>,
        qType: DefaultConfig.Companion.QUESTION_TYPE,
        answer: Answer?
    )
    {

        mBinding.quizPage.rvOptions.layoutManager = LinearLayoutManager(this)
        adapter = QuestionAdapter(optionList, qType, this, answer)
        val animator: ItemAnimator = mBinding.quizPage.rvOptions.itemAnimator!!
        if (animator is SimpleItemAnimator) {
            (animator ).supportsChangeAnimations = false
        }
        mBinding.quizPage.rvOptions.itemAnimator!!.changeDuration = 0
        adapter.setHasStableIds(true)
        mBinding.quizPage.rvOptions.adapter = adapter
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        presenter.onNetworkChanged(isConnected)
    }

}