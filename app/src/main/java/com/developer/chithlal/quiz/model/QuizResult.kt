package com.developer.chithlal.quiz.model

import com.developer.chithlal.quiz.ui.adapter.Answer
import java.io.Serializable

data class QuizResult(
    val score: Int,
    val correct: Int,
    val wrong: Int,
    val time: String,
    val attemptes: Int
) : Serializable

data class UserProgress(val answers:List<Answer?>)