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
        Pair(QuestionType.SCIENTIFIC_NAME, "The scientific name of the %1\$s is %2\$s %3\$s."),
        Pair(QuestionType.GENUS, "The genus of the %1\$s is %2\$s."),
        Pair(QuestionType.WEIGHT, "The %1\$s weighs %2\$s kg."),
        Pair(QuestionType.LENGTH, "The %1\$s is %2\$s meters in length."),
        Pair(QuestionType.DEPTH, "The average depth of the %1\$s is %2\$s meters.")
        // Habitat type question
        // Habitat location question
        // Habitat
    )
    // val templa = "Good %s"
    // val man = "man"
    // val woman = "woman"
    // val comple = String.format(templa, man, woman)
}