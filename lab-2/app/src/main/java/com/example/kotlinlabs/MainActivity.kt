package com.example.kotlinlabs

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val name = if (BuildConfig.SHOW_NAME) "Никита" else null
        setContent {
            GreetingScreen(
                name = name,
                openBasicExercises = {
                    startActivity(Intent(this, ComposeExercisesActivity::class.java))
                },
                openNextExercises = {
                    startActivity(Intent(this, NextExercisesActivity::class.java))
                },
                openShoppingCart = {
                    startActivity(Intent(this, ShoppingCartActivity::class.java))
                },
            )
        }
    }
}

@Composable
private fun GreetingScreen(
    name: String?,
    openBasicExercises: () -> Unit,
    openNextExercises: () -> Unit,
    openShoppingCart: () -> Unit,
) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                Box(modifier = Modifier.align(Alignment.Center)) {
                    Hello(name)
                }
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Button(onClick = openBasicExercises) {
                        Text(stringResource(R.string.open_exercises))
                    }
                    Button(onClick = openNextExercises) {
                        Text(stringResource(R.string.open_next_exercises))
                    }
                    Button(onClick = openShoppingCart) {
                        Text(stringResource(R.string.open_shopping_cart))
                    }
                }
            }
        }
    }
}

@Composable
fun Hello(name: String?) {
    val message = if (name == null) {
        stringResource(R.string.name_missing)
    } else {
        stringResource(R.string.greeting, name)
    }
    Text(text = message)
}

@Preview(name = "portrait", showSystemUi = true, device = "spec:width=360dp,height=640dp")
@Preview(
    name = "landscape",
    showSystemUi = true,
    device = "spec:width=360dp,height=640dp,orientation=landscape",
)
@Preview(
    name = "round",
    showSystemUi = true,
    device = "spec:width=200dp,height=200dp,isRound=true",
)
@Composable
private fun HelloPreview() {
    MaterialTheme {
        val isRound = LocalConfiguration.current.isScreenRound
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isRound) Color.Yellow else MaterialTheme.colorScheme.surface,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Hello("Никита")
            }
        }
    }
}

@Preview(name = "Без имени")
@Composable
private fun HelloWithoutNamePreview() {
    MaterialTheme {
        Hello(null)
    }
}
