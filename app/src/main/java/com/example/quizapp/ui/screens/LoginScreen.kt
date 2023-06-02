import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.ui.Screen
import com.example.quizapp.ui.theme.md_theme_dark_primary
import com.example.quizapp.ui.theme.md_theme_light_primary
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme as MaterialTheme3



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: QuizViewModel) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier.padding(vertical = 32.dp),
        ) {
            Text(
                text = "Java Quiz App",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.logo),
                contentDescription = "Quiz Logo",
                modifier = Modifier
                    .width(150.dp)
                    .height(190.dp)
            )
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it.replace("\n", "") },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it.replace("\n", "") },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    if (username.value.isBlank() || password.value.isBlank()) {
                        viewModel.viewModelScope.launch {
                            snackbarHostState.showSnackbar("Username or password cannot be empty")
                        }
                    } else {
                        viewModel.viewModelScope.launch {
                            val loginSuccessful = viewModel.login(username.value, password.value)
                            if (loginSuccessful) {
                                navController.navigate(Screen.MainMenu.route)
                            } else {
                                snackbarHostState.showSnackbar("Invalid username or password")
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Screen.Register.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }
        }



    }
}
