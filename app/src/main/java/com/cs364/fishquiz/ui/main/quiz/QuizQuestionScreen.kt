package com.cs364.fishquiz.ui.main.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    //var selectedAnswer by rememberSaveable { mutableStateOf(Boolean) }

    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth().padding((16.dp)), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Question $currentQuestionNum out of $totalQuestions", fontSize = 16.sp)
            Text("Current score: $score", fontSize = 16.sp)
        }

        Text("True or False", fontSize = 48.sp, fontWeight = FontWeight.Bold)
        Text(currentQuestion, fontSize = 24.sp, modifier = Modifier.padding(10.dp))
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