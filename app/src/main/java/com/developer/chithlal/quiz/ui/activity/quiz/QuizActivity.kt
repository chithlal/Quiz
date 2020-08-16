package com.developer.chithlal.quiz.ui.activity.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.developer.chithlal.quiz.App
import com.developer.chithlal.quiz.databinding.ActivityQuizBinding
import com.developer.chithlal.quiz.model.Batch
import com.developer.chithlal.quiz.model.OngoingData
import com.developer.chithlal.quiz.ui.adapter.ViewPagerAdapter
import com.developer.chithlal.quiz.ui.fragments.quizitem.OngoingFragment
import com.developer.chithlal.quiz.util.DefaultConfig
import javax.inject.Inject

class QuizActivity : AppCompatActivity(),
    QuizActivityContract.View {
    @Inject
    lateinit var presenter: QuizActivityContract.Presenter
    private lateinit var activityQuizBinding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getApp().component.inject(this)
        activityQuizBinding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(activityQuizBinding.root)
        setSupportActionBar(activityQuizBinding.toolbar)

        presenter.setupView(this)
        setUpTabs()
    }

    private fun setUpTabs() {

    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun onConnectivityUpdate() {
        TODO("Not yet implemented")
    }

    override fun applyView(batch: Batch) {
       Log.d("QuizActivity: ","Quiz:"+batch.batch.ongoing[0].quizDetails[0].name)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(
            OngoingFragment.newInstance(OngoingData(batch.batch.ongoing),
            DefaultConfig.Companion.QUIZ_TYPE.ONGOING
        ),"Ongoing")
        adapter.addFragment(
            OngoingFragment.newInstance(OngoingData(batch.batch.upcoming),
            DefaultConfig.Companion.QUIZ_TYPE.UPCOMING),"Upcoming")
        adapter.addFragment(
            OngoingFragment.newInstance(OngoingData(batch.batch.completed),
            DefaultConfig.Companion.QUIZ_TYPE.COMPLETED),"Completed")
        activityQuizBinding.viewPager.adapter = adapter
        activityQuizBinding.tabs.setupWithViewPager(activityQuizBinding.viewPager)
    }

    override fun getApp(): App {
        return (application as App)
    }


}