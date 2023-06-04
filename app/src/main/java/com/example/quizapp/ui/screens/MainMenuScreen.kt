import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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

    Surface(
        color = topAppBarColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = action,
                content = {
                    Icon(
                        icon,
                        contentDescription = contentDescription,
                        tint = topAppBarContentColor
                    )
                }
            )
            Text(
                text = title,
                color = topAppBarContentColor,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right
            )
        }
    }
}


/*Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(if (isDarkMode) R.drawable.mm else R.drawable.main_manu_background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )*/

@Composable
fun MainMenuScreen(navController: NavController, viewModel: QuizViewModel) {

    val isDarkMode = isSystemInDarkTheme()

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(if (isDarkMode) R.drawable.mm else R.drawable.main_manu_background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        CustomTopAppBar(
            title = "Java Quiz App",
            icon = Icons.Default.AccountCircle,
            contentDescription = "User Profile",
            action = { navController.navigate("user/${viewModel.username}/${viewModel.score}") },
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
                color = colorScheme.scrim,
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(
                        top = 15.dp
                    ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Pick a difficulty",
                color = colorScheme.scrim,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(
                        top = 8.dp
                    ),
                textAlign = TextAlign.Center

            )

            Spacer(
                modifier = Modifier
                    .height(80.dp)
            )
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(280.dp)
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inverseSurface),
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
                    .width(280.dp)
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inverseSurface),
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
                    .width(280.dp)
                    .height(80.dp),
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
