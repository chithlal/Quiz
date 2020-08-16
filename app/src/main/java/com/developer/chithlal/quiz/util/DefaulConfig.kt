package com.developer.chithlal.quiz.util

class DefaultConfig {

    companion object {
        val BASE_URL = "https://api.npoint.io/"
        val ARG_QUIZ_DATA = "QUIZ_DATA"
        val ARG_RESULT = "RESULT"
        enum class QUIZ_TYPE{
            ONGOING,
            UPCOMING,
            COMPLETED
        }
        enum class QUESTION_TYPE{
            MCQ,SCQ
        }

    }
}