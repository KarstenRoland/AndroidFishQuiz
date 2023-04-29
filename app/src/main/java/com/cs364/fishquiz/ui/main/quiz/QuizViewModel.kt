package com.cs364.fishquiz.ui.main.quiz

import androidx.lifecycle.ViewModel
import com.cs364.fishquiz.ui.data.QuizUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState(currentFishId = 0, currentFishHabitatId = 0))
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()
    // private var askedQuestionAttributes: Pair<Int, String>

    /**
     * Sets quiz values back to their defaults.
     */
    fun resetQuiz() {
        _uiState.value = QuizUiState(currentFishId = 0, currentFishHabitatId = 0)
    }

    /**
     * Updates both IDs for the UI state.
     * Should be used when a fish changes.
     *
     * @param currentFishId
     * @param currentFishHabitatId
     */
    fun updateIds(currentFishId: Int, currentFishHabitatId: Int) {
        _uiState.update {currentState ->
            currentState.copy(
                currentFishId = currentFishId,
                currentFishHabitatId = currentFishHabitatId
            )
        }
    }

    /**
     * Increases the score by 1.
     */
    private fun incrementScore() {
        _uiState.update {currentState ->
            currentState.copy(
                score = currentState.score + 1
            )
        }
    }

    /**
     * Increments the question count by 1.
     */
    private fun incrementQuestionCount() {
        _uiState.update {currentState ->
            currentState.copy(
                currentQuestionCount = currentState.currentQuestionCount + 1
            )
        }
    }

    /**
     * Changes the current question text.
     */
    private fun setQuestion(question: String) {
        _uiState.update {currentState ->
            currentState.copy(
                currentQuestion = question
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
        if(_uiState.value.currentQuestionCount >= totalQuestions) {
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
