package com.cs364.fishquiz.ui.data

enum class QuestionType {
    SCIENTIFIC_NAME,
    GENUS,
    WEIGHT,
    LENGTH,
    DEPTH,
    HABITAT_TYPE,
    HABITAT_LOCATION,
    HABITAT_NAME
}

object QuizQuestions {
    val questions = listOf(
        Pair(QuestionType.SCIENTIFIC_NAME, "The scientific name of the %s is %s %s."),
        Pair(QuestionType.GENUS, "The genus of the %s is %s."),
        Pair(QuestionType.WEIGHT, "The %s weighs %f kg."),
        Pair(QuestionType.LENGTH, "The %s is %f meters in length."),
        Pair(QuestionType.DEPTH, "The average depth of the %s is %f meters.")
        // Habitat type question
        // Habitat location question
        // Habitat
    )
    // val templa = "Good %s"
    // val man = "man"
    // val woman = "woman"
    // val comple = String.format(templa, man, woman)
}