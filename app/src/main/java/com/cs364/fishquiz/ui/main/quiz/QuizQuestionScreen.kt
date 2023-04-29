package com.cs364.fishquiz.ui.main.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuizQuestionScreen(
    currentQuestion: String = "",
    isQuestionTrue: Boolean = false,
    totalQuestions: Int = 10,
    currentQuestionNum: Int = 1,
    score: Int = 0,
    onAnswerClicked: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedAnswer by rememberSaveable { mutableStateOf(Boolean) }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Question $currentQuestionNum out of $totalQuestions")
        Text("Current score: $score")
        Text(currentQuestion)
        Button(onClick = {onAnswerClicked(true)}){
            Text("True")
        }
        Button(onClick = {onAnswerClicked(false)}){
            Text("False")
        }
    }
}

@Preview
@Composable
fun QuestionScreenPreview(){
    QuizQuestionScreen(currentQuestion = "This question is false.")
}