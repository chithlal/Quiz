package com.developer.chithlal.quiz.ui.activity.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developer.chithlal.quiz.R
import com.developer.chithlal.quiz.databinding.ActivityResultBinding
import com.developer.chithlal.quiz.model.Quiz
import com.developer.chithlal.quiz.model.QuizResult
import com.developer.chithlal.quiz.util.DefaultConfig.Companion.ARG_RESULT


class ResultActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val resultIntent = intent
         val result :QuizResult?
        if (resultIntent!=null){
            result = resultIntent.getSerializableExtra(ARG_RESULT) as QuizResult
        }
        else result  = null

        showResult(result)

    }

    private fun showResult(result: QuizResult?) {
        mBinding.tvCorrectAnswer.text = result?.correct.toString()
        mBinding.tvWrongAnswer.text = result?.wrong.toString()
        mBinding.tvTime.text = result?.time
        mBinding.tvAttempted.text = result?.attemptes.toString()
        mBinding.tvTotal.text = result?.score.toString()
    }
}