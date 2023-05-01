package com.cs364.fishquiz.ui.main.quiz

import androidx.lifecycle.ViewModel
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.data.QuizQuestions
import com.cs364.fishquiz.ui.data.QuizUiState
import com.cs364.fishquiz.ui.main.FishDBViewModel
import kotlinx.coroutines.flow.*
import kotlin.random.Random

class QuizViewModel(fishList: List<Fish>): ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState(currentFishId = 0, currentFishHabitatId = 0))
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        pickNewRandomQuestion()
    }

    /**
     * Resets all UI state values to defaults.
     */
    fun resetQuiz() {
        _uiState.value = QuizUiState(
            currentFishId = 0,
            currentFishHabitatId = 0
        )
    }

    /**
     * Updates the UI state with fish details
     * depending on the new ID given to it.
     *
     * @param currentFishId
     */
    // TODO
    fun updateNewFish(
        fishDbData: FishDBViewModel,
        currentFishId: Int
    ) {
        val currentFishFlow = fishDbData.getFishFromId(currentFishId)
        // val currentFish = currentFishFlow.collect
        _uiState.update {currentState ->
            currentState.copy(
                currentFishId = currentFishId,
                // currentFishName = currentFish.common_name,
                // currentFishGenus = "",
                // currentFishSpecies = "",
                // currentFishWeight = 0,
                // currentFishLength = 0,
                // currentFishDepth = 0
            )
        }
    }

    /**
     * Increases the score by 1.
     */
    fun incrementScore() {
        _uiState.update {currentState ->
            currentState.copy(
                score = currentState.score + 1
            )
        }
    }

    /**
     * Increments the question count by 1.
     */
    fun incrementQuestionCount() {
        _uiState.update {currentState ->
            currentState.copy(
                totalQuestionsAnswered = currentState.totalQuestionsAnswered + 1
            )
        }
    }

    /**
     * Changes the current question text via random number generation.
     */
    fun pickNewRandomQuestion() {
        // val randomIndex = Random.nextInt(0, fish)
        _uiState.update {currentState ->
            currentState.copy(
                //
                // Set question variables
                currentQuestionIndex = Random.nextInt(0, QuizQuestions.questions.size - 1),
                currentQuestionType = QuizQuestions.questions[currentState.currentQuestionIndex].first,
                currentQuestion = QuizQuestions.questions[currentState.currentQuestionIndex].second

                // Set fish variables
                //currentFishId
            )
        }
    }

    /**
     * Checks if the quiz is over and changes the state if it is.
     * The quiz is over if the question count hits [totalQuestions].
     *
     * @param totalQuestions
     */
    private fun checkQuizOverAndSet(totalQuestions: Int) {
        if(_uiState.value.totalQuestionsAnswered >= totalQuestions) {
            setQuizOver(true)
        }
        else setQuizOver(false)
    }

    /**
     * Changes the quiz over state.
     */
    private fun setQuizOver(quizOver: Boolean) {
        _uiState.update {currentState ->
            currentState.copy(
                isQuizOver = quizOver
            )
        }
    }
}
