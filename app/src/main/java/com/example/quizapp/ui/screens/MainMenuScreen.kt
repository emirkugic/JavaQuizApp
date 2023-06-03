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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.R

@Composable
fun CustomTopAppBar(
    title: String,
    icon: ImageVector,
    contentDescription: String,
    action: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = action) {
                Icon(icon, contentDescription = contentDescription)
            }
        }
    )
}

@Composable
fun MainMenuScreen(navController: NavController, viewModel: QuizViewModel) {
        Box(modifier = Modifier.fillMaxSize()) {
            CustomTopAppBar(
                title = "Java Quiz App",
                icon = Icons.Default.AccountCircle,
                contentDescription = "User Profile",
                action = { navController.navigate("user/${viewModel.username}/${viewModel.score}") }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LET'S PLAY!",
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.error,
                    fontSize = 35.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(
                            top = 15.dp,
                            start = 20.dp
                        )
                )

                Text(
                    text = "Pick a difficulty:",
                    color = colorScheme.scrim,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(
                            top = 8.dp,
                            start = 20.dp
                        )

                )

                Spacer(modifier = Modifier
                    .height(50.dp)
                )
                Button(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(300.dp)
                        .height(100.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inversePrimary),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { navController.navigate("quiz/easy") }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "{",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                        Text(
                            text = "EASY",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                        )
                        Text(
                            text = "}",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(300.dp)
                        .height(100.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { navController.navigate("quiz/medium") }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "{",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                        Text(
                            text = "MEDIUM",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                        )
                        Text(
                            text = "}",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(300.dp)
                        .height(100.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inverseSurface),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { navController.navigate("quiz/hard") }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "{",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                        Text(
                            text = "HARD",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                        )
                        Text(
                            text = "}",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp
                        )
                    }
                }
            }
        }
}
