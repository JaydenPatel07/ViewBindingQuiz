package com.example.viewbindingquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.viewbindingquiz.databinding.FragmentMainBinding
const val KEY_QUESTIONS = "num_of_questions"


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding?= null
    private val binding get()=_binding!!
    var question_list = listOf(
        Question(R.string.question_1, true),
        Question(R.string.question_2, false),
        Question(R.string.question_3, true),
        Question(R.string.question_4, true),
        Question(R.string.question_5, false)
    )

    private var index_of_question: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root

        Log.i("MainActivity", "onCreate Called")

        if (savedInstanceState != null)
            index_of_question = savedInstanceState.getInt(KEY_QUESTIONS)

        nextQuestion()

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }
        binding.next.setOnClickListener {
            nextQuestion()
        }
return rootView
    }

    data class Question(val question: Int, val true_or_false: Boolean) {
        var resourceID: Int = question
        var realAnswer: Boolean = true_or_false
    }

    private fun checkAnswer(user_answer: Boolean) {
        if (user_answer == (question_list.get(index_of_question)).realAnswer) {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show()
        }
    }
    private fun nextQuestion() {
        binding.questionText.text = getString((question_list.get(index_of_question)).resourceID)
        index_of_question++
        if (index_of_question == question_list.size) {
            index_of_question = 0
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart Called")
    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume Called")
    }
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause Called")
    }
    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop Called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy Called")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart Called")
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(KEY_QUESTIONS, index_of_question)
    }

}