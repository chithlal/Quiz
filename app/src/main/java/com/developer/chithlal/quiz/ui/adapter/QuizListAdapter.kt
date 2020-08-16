package com.developer.chithlal.quiz.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.combineMeasuredStates
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.developer.chithlal.quiz.databinding.CardQuizBinding
import com.developer.chithlal.quiz.model.QuizDetail
import com.developer.chithlal.quiz.model.QuizItem
import com.developer.chithlal.quiz.ui.activity.quizsession.QuizSessionActivity
import com.developer.chithlal.quiz.util.DefaultConfig
import com.developer.chithlal.quiz.util.DefaultConfig.Companion.ARG_QUIZ_DATA

class QuizListAdapter(
    val quizList: List<QuizItem>,
    val mQuizType: DefaultConfig.Companion.QUIZ_TYPE,
    private val mContext: Context
): RecyclerView.Adapter<QuizListAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = CardQuizBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuizViewHolder(binding)
         }

    override fun getItemCount(): Int {
       return quizList.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quizItem = quizList.get(position)
        val quizDetail:QuizDetail
        if(quizItem.quizDetails.isNotEmpty()) {
            quizDetail = quizItem.quizDetails[0]

            holder.textViewQuizName.text = quizDetail.name
            val duration = "${quizDetail.duration} Mins"
            holder.textViewDuration.text = duration
            val validity = quizItem.scheduleEnd
            holder.textViewValidity.text = validity
            if (mQuizType == DefaultConfig.Companion.QUIZ_TYPE.COMPLETED ||
                mQuizType == DefaultConfig.Companion.QUIZ_TYPE.UPCOMING
            )
                holder.buttonStart.visibility = GONE
            holder.buttonStart.setOnClickListener{
                val intent = Intent(mContext,QuizSessionActivity::class.java)
                intent.putExtra(ARG_QUIZ_DATA,quizDetail)
                mContext.startActivity(intent)
            }
        }


    }

    class QuizViewHolder(binding: CardQuizBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewQuizName = binding.textViewQuizName
        val textViewDuration = binding.textViewQuizTime
        val textViewValidity = binding.textViewValidity
        val buttonStart = binding.buttonStartQuiz
        val buttonOption = binding.buttonMenu

    }


}