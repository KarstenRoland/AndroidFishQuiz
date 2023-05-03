package com.cs364.fishquiz.ui.main.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs364.fishquiz.ui.main.FishDBViewModel

@Composable
fun QuizQuestionScreen(
    onAnswerClicked: (Boolean) -> Unit = {},
    quizViewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val quizUiState by quizViewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding((16.dp)), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Question ${quizUiState.totalQuestionsAnswered}", fontSize = 16.sp)
            Text("Current score: ${quizUiState.score}", fontSize = 16.sp)
        }

        Text("True or False", fontSize = 48.sp, fontWeight = FontWeight.Bold)
        // Text(QuizQuestions.questions[questionIndex].second, fontSize = 24.sp, modifier = Modifier.padding(10.dp))
        Text(quizUiState.currentQuestion, fontSize = 24.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
        Button(onClick = {
            if(quizUiState.isCurrentQuestionTrue) {
                quizViewModel.incrementScore()
            }
            quizViewModel.incrementQuestionCount()
            onAnswerClicked(true)
            quizViewModel.pickNewRandomQuestion()
        }){ Text("True") }
        Button(onClick = {
            if(!quizUiState.isCurrentQuestionTrue) {
                quizViewModel.incrementScore()
            }
            quizViewModel.incrementQuestionCount()
            onAnswerClicked(false)
            quizViewModel.pickNewRandomQuestion()
        }){ Text("False") }
    }
}
