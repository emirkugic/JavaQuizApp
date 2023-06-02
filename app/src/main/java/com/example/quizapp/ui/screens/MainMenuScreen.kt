import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.R

@Composable
fun MainMenuScreen(navController: NavController, viewModel: QuizViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Java Quiz App") },
            actions = {
                IconButton(onClick = { navController.navigate("user/${viewModel.username}/${viewModel.score}") }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "User Profile")
                }
            },
            //backgroundColor = Color.Blue // Set the background color here, ne radi :(
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.icon),
                contentDescription = "Quiz icon"
            )
            Text(
                text = "Pick a difficulty:",
                color = colorScheme.scrim,
                fontSize = 25.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)

            )
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(5.dp),
                onClick = { navController.navigate("quiz/easy") }
            ) {
                Text(text = "Easy")
            }

            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(5.dp),
                onClick = { navController.navigate("quiz/medium") }
            ) {
                Text(text = "Medium")
            }

            Button(
                modifier = Modifier
                    .padding(18.dp)
                    .width(300.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(5.dp),
                onClick = { navController.navigate("quiz/hard") }
            ) {
                Text(text = "Hard")
            }
        }
    }
}
