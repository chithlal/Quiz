package com.developer.chithlal.quiz.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Batch(
    @SerializedName("batch")
    val batch: BatchX
): Serializable

data class BatchX(
    @SerializedName("completed")
    val completed: List<QuizItem>,
    @SerializedName("ongoing")
    val ongoing: List<QuizItem>,
    @SerializedName("upcoming")
    val upcoming: List<QuizItem>
): Serializable

data class QuizItem(
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("quizDetails")
    val quizDetails: List<QuizDetail>,
    @SerializedName("scheduleEnd")
    val scheduleEnd: String,
    @SerializedName("scheduleStart")
    val scheduleStart: String
): Serializable

data class Ongoing(
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("quizDetails")
    val quizDetails: List<QuizDetailX>,
    @SerializedName("scheduleEnd")
    val scheduleEnd: String,
    @SerializedName("scheduleStart")
    val scheduleStart: String
): Serializable

data class QuizDetail(
    @SerializedName("courseId")
    val courseId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("instructions")
    val instructions: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("orgId")
    val orgId: String,
    @SerializedName("published")
    val published: Boolean,
    @SerializedName("quizMarks")
    val quizMarks: Int,
    @SerializedName("quizType")
    val quizType: Int,
    @SerializedName("settings")
    val settings: Settings,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
): Serializable

data class Settings(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("isSuffle")
    val isSuffle: Boolean,
    @SerializedName("public")
    val `public`: Boolean,
    @SerializedName("security")
    val security: Boolean,
    @SerializedName("sendMessageToParents")
    val sendMessageToParents: Boolean,
    @SerializedName("sendMessageToStudent")
    val sendMessageToStudent: Boolean,
    @SerializedName("solutionReleaseAt")
    val solutionReleaseAt: Any,
    @SerializedName("solutionReleaseType")
    val solutionReleaseType: Int
): Serializable

data class QuizDetailX(
    @SerializedName("courseId")
    val courseId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("instructions")
    val instructions: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("orgId")
    val orgId: String,
    @SerializedName("published")
    val published: Boolean,
    @SerializedName("quizMarks")
    val quizMarks: Int,
    @SerializedName("quizType")
    val quizType: Int,
    @SerializedName("settings")
    val settings: SettingsX,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
): Serializable


data class SettingsX(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("isSuffle")
    val isSuffle: Boolean,
    @SerializedName("public")
    val `public`: Boolean,
    @SerializedName("security")
    val security: Boolean,
    @SerializedName("sendMessageToParents")
    val sendMessageToParents: Boolean,
    @SerializedName("sendMessageToStudent")
    val sendMessageToStudent: Boolean,
    @SerializedName("solutionReleaseAt")
    val solutionReleaseAt: Any,
    @SerializedName("solutionReleaseType")
    val solutionReleaseType: Int
): Serializable

data class OngoingData(
    val ongoingList: List<QuizItem>
):Serializable