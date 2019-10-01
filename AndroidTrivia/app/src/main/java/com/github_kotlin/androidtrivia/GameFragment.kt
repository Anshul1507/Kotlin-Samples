package com.github_kotlin.androidtrivia


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github_kotlin.androidtrivia.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    data class Question(val text:String,val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "What is Android Jetpack?",
            answers = listOf("all of these", "tools", "documentation", "libraries")),
        Question(text = "Base class for Layout?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
        Question(text = "Layout for complex Screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
        Question(text = "Pushing structured data into a Layout?",
            answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
        Question(text = "Inflate layout in fragments?",
            answers = listOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")),
        Question(text = "Build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
        Question(text = "Android vector format?",
            answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
        Question(text = "Android Navigation Component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
        Question(text = "Registers app with launcher?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
        Question(text = "Mark a layout for Data Binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1)/2,3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater,R.layout.fragment_game,container,false
        )


        randomizeQuestions()
        binding.game = this

        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER"){
            view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            if(checkedId != -1){
                var answerIndex = 0
                when (checkedId){
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3

                }

                if(answers[answerIndex] == currentQuestion.answers[0]){
                    questionIndex++

                    if(questionIndex < numQuestions){
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()

                    }else{
                        //We've Won! navigate to the gamewon fragment
                    }
                }else{
                    //Game over! a wrong answer sends us to the gameoverfragment
                }
            }

        }

        return binding.root
    }

private fun randomizeQuestions() {
    questions.shuffle()
    questionIndex=0
    setQuestion()

}
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = "Android Trivia ({questionIndex + 1}/{numQuestions})"
    }
}
