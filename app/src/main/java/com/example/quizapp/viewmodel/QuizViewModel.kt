import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.QuizDataSource
import com.example.quizapp.data.QuizQuestion
import com.example.quizapp.data.User
import com.example.quizapp.data.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(private val userDao: UserDao) : ViewModel() {
    var username: String? = null
    var score: Int = 0

    private val _easyScore = MutableStateFlow(0)
    val easyScore: StateFlow<Int> = _easyScore

    private val _mediumScore = MutableStateFlow(0)
    val mediumScore: StateFlow<Int> = _mediumScore

    private val _hardScore = MutableStateFlow(0)
    val hardScore: StateFlow<Int> = _hardScore

    fun getUserScores(username: String) {
        viewModelScope.launch {
            val user = userDao.getUser(username)
            _easyScore.value = user?.easyScore ?: 0
            _mediumScore.value = user?.mediumScore ?: 0
            _hardScore.value = user?.hardScore ?: 0
        }
    }

    fun getQuizQuestions(difficulty: String): List<QuizQuestion> {
        return when (difficulty) {
            "easy" -> QuizDataSource.easyQuestions
            "medium" -> QuizDataSource.mediumQuestions
            "hard" -> QuizDataSource.hardQuestions
            else -> throw IllegalArgumentException("Invalid difficulty level: $difficulty")
        }
    }

    suspend fun register(username: String, password: String) {
        val user = User(username, password)
        userDao.register(user)
    }

    suspend fun login(username: String, password: String): Boolean {
        val user = userDao.getUser(username)
        return if (user != null && user.password == password) {
            this.username = user.username
            this.score = 0 // You might want to retrieve the actual score from a database
            true
        } else {
            false
        }
    }

    fun logout() {
        username = null
        score = 0
    }


    suspend fun updateEasyScore(username: String, score: Int) {
        val user = userDao.getUser(username)
        if (user != null) {
            userDao.updateUser(user.copy(easyScore = score))
            _easyScore.value = score
            println("Updated medium score for user $username to $score")
        } else {
            println("Failed to update medium score for user $username")
        }
    }



    suspend fun updateMediumScore(username: String, score: Int) {
        val user = userDao.getUser(username)
        if (user != null) {
            userDao.updateUser(user.copy(mediumScore = score))
            _mediumScore.value = score
            println("Updated medium score for user $username to $score")
        } else {
            println("Failed to update medium score for user $username")
        }
    }

    suspend fun updateHardScore(username: String, score: Int) {
        val user = userDao.getUser(username)
        if (user != null) {
            userDao.updateUser(user.copy(hardScore = score))
            _hardScore.value = score
            println("Updated hard score for user $username to $score")
        } else {
            println("Failed to update hard score for user $username")
        }
    }



}
