package com.developer.chithlal.quiz.network

import android.content.ContentValues.TAG
import android.util.Log
import com.developer.chithlal.quiz.model.Batch
import com.developer.chithlal.quiz.model.QuizData
import com.developer.chithlal.quiz.model.UserProgress
import com.developer.chithlal.quiz.ui.adapter.Answer
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class NetworkHandler @Inject constructor(val apiInterface: QuizApiInterface) {
    /*@Inject
    lateinit var apiInterface: QuizApiInterface*/
    class Result(val data:Any){}
    lateinit var  publisher: Publisher


    fun getBatches(){
        val call: Call<Batch> = apiInterface.getBatch()

        call.enqueue(object :Callback<Batch>{
            override fun onFailure(call: Call<Batch>, t: Throwable) {
                publisher.onError("Error"+t.localizedMessage)
            }

            override fun onResponse(call: Call<Batch>, response: Response<Batch>) {
                if (response.isSuccessful) {
                    val result =
                        Result(
                            response.body()!!
                        )
                    publisher.publishData(result)
                }
                else{
                    publisher.onError("Error:"+"Something went wrong!")
                }
            }

        })
    }
    fun getQuiz(){
        val call: Call<QuizData> = apiInterface.getQuiz()

        call.enqueue(object :Callback<QuizData>{
            override fun onFailure(call: Call<QuizData>, t: Throwable) {
                publisher.onError("Error"+t.localizedMessage)
            }

            override fun onResponse(call: Call<QuizData>, response: Response<QuizData>) {
                if (response.isSuccessful) {
                    val result =
                        Result(
                            response.body()!!
                        )
                    publisher.publishData(result)
                }
                else{
                    publisher.onError("Error:"+"Something went wrong!")
                }
            }

        })

    }

    fun pushFirebaseData(answerList: Array<Answer?>) {
        //updating the progress to firebase
        val db = FirebaseFirestore.getInstance()
        db.collection("Answers").add(UserProgress(answerList.toList()))
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }

    interface Publisher{
    fun publishData(result: Result)
    fun onError(message: String)
}
}