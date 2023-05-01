package com.cs364.fishquiz.ui.main.quiz

import androidx.lifecycle.ViewModel
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.data.QuestionType
import com.cs364.fishquiz.ui.data.QuizQuestions
import com.cs364.fishquiz.ui.data.QuizUiState
import com.cs364.fishquiz.ui.main.FishDBViewModel
import kotlinx.coroutines.flow.*
import kotlin.random.Random

class QuizViewModel(fishList: List<Fish>): ViewModel() {
    private val _uiState = MutableStateFlow(QuizUiState(currentFishId = 0, currentFishHabitatId = 0, fishList = fishList))
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        pickNewRandomQuestion()
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
        val newFishId = Random.nextInt(0, uiState.value.fishList.size - 1)
        var formattedQuestion: String = ""
        _uiState.update {currentState ->
            currentState.copy(
                // Set question variables
                currentQuestionIndex = Random.nextInt(0, QuizQuestions.questions.size - 1),
                currentQuestionType = QuizQuestions.questions[currentState.currentQuestionIndex].first,
                currentQuestion = QuizQuestions.questions[currentState.currentQuestionIndex].second,
            )
        }
        if(uiState.value.currentQuestionType == QuestionType.SCIENTIFIC_NAME) {
            formattedQuestion = String.format(
                QuizQuestions.questions[uiState.value.currentQuestionIndex].second,
                uiState.value.fishList[uiState.value.currentFishId].common_name,
                uiState.value.fishList[uiState.value.currentFishId].genus,
                uiState.value.fishList[uiState.value.currentFishId].species
                )
        }
        else if(uiState.value.currentQuestionType == QuestionType.GENUS) {
            formattedQuestion = String.format(
                QuizQuestions.questions[uiState.value.currentQuestionIndex].second,
                uiState.value.fishList[uiState.value.currentFishId].common_name,
                uiState.value.fishList[uiState.value.currentFishId].genus
            )
        }
        else if(uiState.value.currentQuestionType == QuestionType.WEIGHT) {
            formattedQuestion = String.format(
                QuizQuestions.questions[uiState.value.currentQuestionIndex].second,
                uiState.value.fishList[uiState.value.currentFishId].common_name,
                uiState.value.fishList[uiState.value.currentFishId].avg_weight_kg
            )
        }
        else if(uiState.value.currentQuestionType == QuestionType.LENGTH) {
            formattedQuestion = String.format(
                QuizQuestions.questions[uiState.value.currentQuestionIndex].second,
                uiState.value.fishList[uiState.value.currentFishId].common_name,
                uiState.value.fishList[uiState.value.currentFishId].avg_len_met
            )
        }
        else if(uiState.value.currentQuestionType == QuestionType.DEPTH) {
            formattedQuestion = String.format(
                QuizQuestions.questions[uiState.value.currentQuestionIndex].second,
                uiState.value.fishList[uiState.value.currentFishId].common_name,
                uiState.value.fishList[uiState.value.currentFishId].water_depth_met
            )
        }
        _uiState.update {currentState ->
            currentState.copy(
                currentQuestion = formattedQuestion
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
