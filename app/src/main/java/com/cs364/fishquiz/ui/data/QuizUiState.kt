package com.cs364.fishquiz.ui.data

data class QuizUiState(
    val fishList: List<Fish>,
    val currentFishId: Int,
    val currentFishHabitatId: Int,
    val currentQuestion: String = "",
    val isCurrentQuestionTrue: Boolean = true,
    val totalQuestionsAnswered: Int = 1,
    val score: Int = 0,
    val isQuizOver: Boolean = false
)
