import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.data.QuizDataSource
import com.example.quizapp.data.QuizQuestion
import com.example.quizapp.ui.Screen
import com.example.quizapp.ui.theme.md_theme_dark_scrim
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
    val isDarkMode = isSystemInDarkTheme()

    val topAppBarColor = if (isDarkMode) {
        Color(0xFF082841) // Dark mode color: #082841
    } else {
        Color(0xFF86C9FE) // Light mode color: #86c9fe
    }

    val topAppBarContentColor = if (isDarkMode) {
        Color(0xFFEFEFEF)
    } else {
        colorScheme.scrim
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(if (isDarkMode) R.drawable.d else R.drawable.l),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

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
            Surface(
                color = topAppBarColor,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    IconButton(
                        onClick = { showDialog.value = true },
                        content = {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Back to Main Menu",
                                tint = topAppBarContentColor
                            )
                        }
                    )
                    Text(
                        text = "${difficulty?.capitalize()} Quiz: ${currentQuestionIndex.value + 1} / ${questions.size}",
                        color = topAppBarContentColor,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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

                        if (currentQuestionIndex.value == questions.size) {
                            viewModel.score = score.value

                            viewModel.viewModelScope.launch {
                                when (difficulty) {
                                    "easy" -> viewModel.updateEasyScore(
                                        viewModel.username!!,
                                        score.value
                                    )

                                    "medium" -> viewModel.updateMediumScore(
                                        viewModel.username!!,
                                        score.value
                                    )

                                    "hard" -> viewModel.updateHardScore(
                                        viewModel.username!!,
                                        score.value
                                    )
                                }
                            }

                            navController.navigate(
                                Screen.Results(
                                    score.value,
                                    questions.size
                                ).route
                            )
                        }
                    }
                )
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = colorScheme.scrim,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
