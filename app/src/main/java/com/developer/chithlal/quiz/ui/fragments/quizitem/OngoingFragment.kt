package com.developer.chithlal.quiz.ui.fragments.quizitem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.chithlal.quiz.databinding.FragmentOngoingBinding
import com.developer.chithlal.quiz.dependencyinjection.ComponentFactory
import com.developer.chithlal.quiz.model.OngoingData
import com.developer.chithlal.quiz.model.QuizItem
import com.developer.chithlal.quiz.ui.adapter.QuizListAdapter
import com.developer.chithlal.quiz.util.DefaultConfig
import javax.inject.Inject

private const val ARG_ONGOING_LIST = "LIST"
private const val ARG_QUIZ_TYPE = "QUIZ_TYPE"


@Suppress("DEPRECATION")
class OngoingFragment : Fragment(),QuizListContract.View {
    // TODO: Rename and change types of parameters
    private lateinit var ongoingQuiz: OngoingData
    private lateinit var mBinding: FragmentOngoingBinding
    private lateinit var mQuizType: DefaultConfig.Companion.QUIZ_TYPE
    @Inject
    lateinit var presenter: QuizListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ongoingQuiz = it.getSerializable(ARG_ONGOING_LIST) as OngoingData
            mQuizType = it.getSerializable(ARG_QUIZ_TYPE) as DefaultConfig.Companion.QUIZ_TYPE

        }
        ComponentFactory.getFragmentComponent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentOngoingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

       // mBinding.textView.setText(ongoingQuiz.ongoingList[0].quizDetails[0].name)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setupView(this)
    }

    companion object {

        @JvmStatic
        fun newInstance(onGoingData: OngoingData,
                        type: DefaultConfig.Companion.QUIZ_TYPE) =
            OngoingFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ONGOING_LIST, onGoingData)
                    putSerializable(ARG_QUIZ_TYPE,type)

                }
            }
    }

    override fun showList(quizList: List<QuizItem>) {
        mBinding.rvOngoing.setHasFixedSize(true)
        val llayout = LinearLayoutManager(context)
        llayout.isAutoMeasureEnabled = false
       mBinding.rvOngoing.layoutManager =llayout
        mBinding.rvOngoing.adapter = QuizListAdapter(quizList,mQuizType,context!!)

    }

    override fun showMessage(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun getQuizData(): OngoingData {
        return ongoingQuiz
    }

    override fun showEmptyQuiz() {
        mBinding.layoutNoQuiz.visibility = VISIBLE
    }
}