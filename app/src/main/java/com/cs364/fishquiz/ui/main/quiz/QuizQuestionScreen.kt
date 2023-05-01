package com.cs364.fishquiz.ui.main.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs364.fishquiz.ui.data.QuestionType
import kotlin.random.Random
import com.cs364.fishquiz.ui.data.QuizQuestions

@Composable
fun QuizQuestionScreen(
    onAnswerClicked: (Boolean) -> Unit = {},
    quizViewModel: QuizViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    //var selectedAnswer by rememberSaveable { mutableStateOf(Boolean) }
    val quizUiState by quizViewModel.uiState.collectAsState()
    var isQuestionTrue: Boolean = true

    Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth().padding((16.dp)), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Question ${quizUiState.currentQuestionCount}", fontSize = 16.sp)
            Text("Current score: ${quizUiState.score}", fontSize = 16.sp)
        }

        Text("True or False", fontSize = 48.sp, fontWeight = FontWeight.Bold)
        // Text(QuizQuestions.questions[questionIndex].second, fontSize = 24.sp, modifier = Modifier.padding(10.dp))
        Text(quizUiState.currentQuestion, fontSize = 24.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
        Button(onClick = {
            quizViewModel.pickNewRandomQuestion()
            quizViewModel.incrementQuestionCount()
            onAnswerClicked(true)
        }){ Text("True") }
        Button(onClick = {
            quizViewModel.pickNewRandomQuestion()
            quizViewModel.incrementQuestionCount()
            onAnswerClicked(false)
        }){ Text("False") }
    }
}
