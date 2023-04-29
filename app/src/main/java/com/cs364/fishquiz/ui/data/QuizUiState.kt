package com.cs364.fishquiz.ui.data

data class QuizUiState(
    val currentFishId: Int,
    val currentFishHabitatId: Int,
    val score: Int = 0,
    val currentQuestionCount: Int = 1,
    val currentQuestion: String = "",
    val isQuizOver: Boolean = false
)
