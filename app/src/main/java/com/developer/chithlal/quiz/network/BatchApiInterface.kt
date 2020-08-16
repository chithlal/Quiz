package com.developer.chithlal.quiz.network

import com.developer.chithlal.quiz.model.Batch
import com.developer.chithlal.quiz.model.QuizData
import retrofit2.Call
import retrofit2.http.GET

interface QuizApiInterface {
    @GET("b00b070fc2461a68383b")
    fun getBatch(): Call<Batch>

    @GET("c454bbb861f9ab1e9309")
    fun getQuiz(): Call<QuizData>

}