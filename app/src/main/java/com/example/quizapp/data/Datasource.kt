package com.example.quizapp.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val selectedOption: MutableState<String?> = mutableStateOf(null)
)

object QuizDataSource {
    val easyQuestions = listOf(
        QuizQuestion(
            question = "What is the output of the following Java code?\n\npublic class MyClass {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(++x);\n    }\n}",
            options = listOf("4", "5", "6", "7"),
            correctAnswer = "6"
        ),
        QuizQuestion(
            question = "Which keyword is used to explicitly state that a method does not return any value?",
            options = listOf("void", "null", "empty", "none"),
            correctAnswer = "void"
        ),
        QuizQuestion(
            question = "What is the correct syntax to initialize an array in Java?",
            options = listOf("[1, 2, 3]", "new Array(1, 2, 3)", "{1, 2, 3}", "Array(1, 2, 3)"),
            correctAnswer = "{1, 2, 3}"
        ),
        QuizQuestion(
            question = "What is the output of the following Java code?\n\npublic class MyClass {\n    public static void main(String[] args) {\n        String str = \"Hello, World!\";\n        System.out.println(str.length());\n    }\n}",
            options = listOf("12", "13", "14", "15"),
            correctAnswer = "13"
        ),
        QuizQuestion(
            question = "Which loop is guaranteed to execute at least once?",
            options = listOf("while loop", "for loop", "do-while loop", "if-else loop"),
            correctAnswer = "do-while loop"
        )
    )

    val mediumQuestions = listOf(
        QuizQuestion(
            question = "What is the output of the following Java code?\n\npublic class MyClass {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++);\n    }\n}",
            options = listOf("4", "5", "6", "7"),
            correctAnswer = "5"
        ),
        QuizQuestion(
            question = "Which keyword is used to prevent a class from being inherited by other classes?",
            options = listOf("private", "final", "static", "protected"),
            correctAnswer = "final"
        ),
        QuizQuestion(
            question = "What is the correct way to declare a constant variable in Java?",
            options = listOf("final int x = 10;", "const int x = 10;", "static final int x = 10;", "constant int x = 10;"),
            correctAnswer = "static final int x = 10;"
        ),
        QuizQuestion(
            question = "What is the output of the following Java code?\n\npublic class MyClass {\n    public static void main(String[] args) {\n        String str = \"Hello, World!\";\n        System.out.println(str.substring(7));\n    }\n}",
            options = listOf("Hello, World!", "World!", "Hello,", "ld!"),
            correctAnswer = "World!"
        ),
        QuizQuestion(
            question = "Which statement is used to exit from a loop in Java?",
            options = listOf("stop", "end", "break", "exit"),
            correctAnswer = "break"
        )
    )

    val hardQuestions = listOf(
        QuizQuestion(
            question = "What is the output of the following Java code?\n\npublic class MyClass {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++ + ++x);\n    }\n}",
            options = listOf("10", "11", "12", "13"),
            correctAnswer = "12"
        ),
        QuizQuestion(
            question = "Which keyword is used to define a block of code that can be synchronized by multiple threads?",
            options = listOf("sync", "synchronized", "threadsafe", "lock"),
            correctAnswer = "synchronized"
        ),
        QuizQuestion(
            question = "What is the correct way to declare a generic class in Java?",
            options = listOf("class MyClass<T>", "class MyClass<type T>", "class MyClass{}", "class MyClass<T extends Object>"),
            correctAnswer = "class MyClass<T>"
        ),
        QuizQuestion(
            question = "What is the output of the following Java code?\n\npublic class MyClass {\n    public static void main(String[] args) {\n        String str = \"Hello, World!\";\n        System.out.println(str.replace(\"o\", \"a\"));\n    }\n}",
            options = listOf("Hella, Warld!", "Hella, Warrld!", "Hello, World!", "Hella, Wold!"),
            correctAnswer = "Hella, Warld!"
        ),
        QuizQuestion(
            question = "Which exception is thrown when a method is called on an object that is null?",
            options = listOf("NullPointerException", "ClassCastException", "ArrayIndexOutOfBoundsException", "NumberFormatException"),
            correctAnswer = "NullPointerException"
        )
    )
}
