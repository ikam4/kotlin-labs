package com.example.kotlinlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ComposeExercisesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ComposeExercises()
                }
            }
        }
    }
}

@Composable
private fun ComposeExercises() {
    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CenteredDescription()
        SingleLineDescription()
        UnderlinedDescription()
        ExerciseButton()
        InitialsBanner()
    }
}

@Preview(name = "Зелёный курсив", widthDp = 360, showBackground = true)
@Composable
private fun CenteredDescription() {
    Text(
        text = stringResource(R.string.compose_description),
        modifier = Modifier.fillMaxWidth(),
        color = Color.Green,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
    )
}

@Preview(name = "Одна строка", widthDp = 360, showBackground = true)
@Composable
private fun SingleLineDescription() {
    Text(
        text = stringResource(R.string.compose_description),
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview(name = "Первая строка с отступом", widthDp = 360, showBackground = true)
@Composable
private fun UnderlinedDescription() {
    val description = stringResource(R.string.compose_description)
    val indentedDescription = buildAnnotatedString {
        withStyle(ParagraphStyle(textIndent = TextIndent(firstLine = 48.sp))) {
            append(description)
        }
    }
    Text(
        text = indentedDescription,
        modifier = Modifier.fillMaxWidth().background(Color.Green),
        color = Color.Black,
        fontSize = 24.sp,
        textDecoration = TextDecoration.Underline,
    )
}

@Preview(name = "Кнопка", widthDp = 360, showBackground = true)
@Composable
private fun ExerciseButton() {
    val shape = RoundedCornerShape(16.dp)
    Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth().shadow(4.dp, shape),
        shape = shape,
        border = BorderStroke(2.dp, Color.Gray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
        ),
    ) {
        Text(
            text = stringResource(R.string.exercise_button),
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
        )
    }
}
