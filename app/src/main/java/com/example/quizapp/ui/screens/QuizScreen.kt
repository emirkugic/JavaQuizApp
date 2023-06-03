import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quizapp.data.QuizDataSource
import com.example.quizapp.data.QuizQuestion
import com.example.quizapp.ui.Screen
import kotlinx.coroutines.launch

@Composable
fun QuizCard(question: QuizQuestion, selectedOption: MutableState<String>) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = question.question)

        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEach { option ->
            Button(
                onClick = { selectedOption.value = option },
                colors = if (selectedOption.value == option) {
                    ButtonDefaults.buttonColors(colorScheme.onPrimaryContainer)
                } else {
                    ButtonDefaults.buttonColors(colorScheme.primary)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(300.dp),

            ) {
                Text(text = option)
            }
        }
    }
}


@Composable
fun QuizScreen(navController: NavController, difficulty: String?, viewModel: QuizViewModel) {
    val questions = when (difficulty) {
        "easy" -> QuizDataSource.easyQuestions
        "medium" -> QuizDataSource.mediumQuestions
        "hard" -> QuizDataSource.hardQuestions
        else -> listOf<QuizQuestion>()
    }

    val currentQuestionIndex = remember { mutableStateOf(0) }
    val selectedOption = remember { mutableStateOf("") }
    val score = remember { mutableStateOf(0) }
    val showDialog = remember { mutableStateOf(false) }

    val currentQuestion = questions.getOrNull(currentQuestionIndex.value)

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Confirmation") },
            text = { Text("Are you sure you want to quit the quiz?") },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false
                    navController.navigate(Screen.MainMenu.route)
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("No")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("${difficulty?.capitalize()} Quiz: ${currentQuestionIndex.value + 1} / ${questions.size}") },
            navigationIcon = {
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(Icons.Default.Close, contentDescription = "Back to Main Menu")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Use the QuizCard function here
        currentQuestion?.let {
            QuizCard(it, selectedOption)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 50.dp),
            horizontalArrangement = Arrangement.End
        )
        {
            Text(
                text = "Next",
                color = colorScheme.scrim,
                modifier = Modifier.clickable {
                    if (selectedOption.value == currentQuestion?.correctAnswer) {
                        score.value++
                    }
                    selectedOption.value = ""
                    currentQuestionIndex.value++

                    // Check if the quiz is finished
                    if (currentQuestionIndex.value == questions.size) {
                        // Update the score in the ViewModel
                        viewModel.score = score.value

                        // Update the score in the database
                        viewModel.viewModelScope.launch {
                            when (difficulty) {
                                "easy" -> viewModel.updateEasyScore(viewModel.username!!, score.value)
                                "medium" -> viewModel.updateMediumScore(viewModel.username!!, score.value)
                                "hard" -> viewModel.updateHardScore(viewModel.username!!, score.value)
                            }
                        }

                        // Navigate to the ResultsScreen
                        navController.navigate(Screen.Results(score.value, questions.size).route)
                    }
                }
            )
            //add arrow icon here
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Next",
                modifier = Modifier.padding(start = 4.dp)
            )

        }

    }
}

