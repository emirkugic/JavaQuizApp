import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quizapp.data.QuizDataSource
import com.example.quizapp.data.QuizQuestion
import com.example.quizapp.ui.Screen
import kotlinx.coroutines.launch

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

    val currentQuestion = questions.getOrNull(currentQuestionIndex.value)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {navController.navigate(Screen.MainMenu.route)}) {
                Icon(Icons.Default.Close, contentDescription = "Back to Main Menu")
            }
        }

        Text(text = "Difficulty: $difficulty")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = currentQuestion?.question ?: "No more questions")

        Spacer(modifier = Modifier.height(16.dp))

        currentQuestion?.options?.forEach { option ->
            Button(
                onClick = { selectedOption.value = option },
                colors = ButtonDefaults.buttonColors(
                    /*backgroundColor = if (selectedOption.value == option) MaterialTheme.colors.primary else MaterialTheme.colors.surface*/
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = option)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
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
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
        }

    }
}
