package com.developer.chithlal.quiz.model
import com.developer.chithlal.quiz.util.DefaultConfig
import com.google.gson.annotations.SerializedName


data class QuizData(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("quiz")
    val quiz: Quiz
)

data class Data(
    @SerializedName("body")
    val body: String,
    @SerializedName("chapter")
    val chapter: Chapter,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("difficulty")
    val difficulty: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("isMultiple")
    val isMultiple: Boolean,
    @SerializedName("isPassage")
    val isPassage: Boolean,
    @SerializedName("negativeMarks")
    val negativeMarks: Int,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("orgId")
    val orgId: String,
    @SerializedName("partialMarks")
    val partialMarks: Int,
    @SerializedName("positiveMarks")
    val positiveMarks: Int,
    @SerializedName("questionType")
    val questionType: String,
    @SerializedName("solution")
    val solution: String,
    @SerializedName("subject")
    val subject: Subject,
    @SerializedName("subtopics")
    val subtopics: List<Subtopic>
){
    fun getQuestionType():DefaultConfig.Companion.QUESTION_TYPE{
       return if(questionType.equals("SCQ"))
            DefaultConfig.Companion.QUESTION_TYPE.SCQ
        else DefaultConfig.Companion.QUESTION_TYPE.MCQ
    }
}

data class Quiz(
    @SerializedName("course")
    val course: String,
    @SerializedName("courseId")
    val courseId: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("instructions")
    val instructions: List<String>,
    @SerializedName("isSuffle")
    val isSuffle: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("orgId")
    val orgId: String,
    @SerializedName("public")
    val `public`: Boolean,
    @SerializedName("published")
    val published: Boolean,
    @SerializedName("quizType")
    val quizType: Int
)

data class Chapter(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class Option(
    @SerializedName("correct")
    val correct: Boolean,
    @SerializedName("_id")
    val id: String,
    @SerializedName("value")
    val value: String,
    var isSelected:Int
)

data class Subject(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class Subtopic(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)