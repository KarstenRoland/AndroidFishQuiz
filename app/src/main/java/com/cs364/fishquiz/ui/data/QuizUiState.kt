package com.cs364.fishquiz.ui.data

data class QuizUiState(
    val currentFishId: Int,
    val currentFishName: String = "",
    val currentFishGenus: String = "",
    val currentFishSpecies: String = "",
    val currentFishWeight: Int = 0,
    val currentFishLength: Int = 0,
    val currentFishDepth: Int = 0,
    val currentFishHabitatId: Int,
    val currentQuestionIndex: Int = 0,
    val currentQuestionType: QuestionType? = null,
    val currentQuestion: String = "",
    val isCurrentQuestionTrue: Boolean = true,
    val currentQuestionCount: Int = 1,
    val score: Int = 0,
    val isQuizOver: Boolean = false
)
