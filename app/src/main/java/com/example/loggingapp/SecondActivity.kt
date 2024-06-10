package com.example.loggingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loggingapp.ui.theme.LoggingAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoggingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    secondpage(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun secondpage(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFC0CB), Color(0xFF800080))
                )
            )

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "स्वागत! ",
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic ,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoggingAppTheme {
        secondpage()
    }
}