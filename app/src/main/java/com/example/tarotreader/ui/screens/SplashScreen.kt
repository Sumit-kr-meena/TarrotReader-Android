package com.example.tarotreader.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.tarotreader.ui.theme.BottomPurple
import com.example.tarotreader.ui.theme.SystemBarStyle
import com.example.tarotreader.ui.theme.TopPurple

private val Gold = Color(0xFFFFD700)
private val Black = Color(0xFF000000)

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {

    SystemBarStyle(
        statusBarColor = TopPurple,
        navigationBarColor = BottomPurple,
        darkIcons = false
    )

    LaunchedEffect(Unit) {

        delay(2000)

        onNavigateToHome()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        MysticSymbol()
    }
}

@Composable
fun MysticSymbol() {
    Canvas(
        modifier = Modifier.size(220.dp)
    ) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        val center = Offset(canvasWidth / 2f, canvasHeight / 2f)

        // Outer Circle
        drawCircle(
            color = Gold,
            radius = size.minDimension * 0.42f,
            center = center,
            style = Stroke(width = 8f)
        )

        // Inner Circle
        drawCircle(
            color = Gold,
            radius = size.minDimension * 0.38f,
            center = center,
            style = Stroke(width = 6f)
        )

        // Vertical Spike Line
        drawLine(
            color = Black,
            start = Offset(center.x, size.height * 0.00f),
            end = Offset(center.x, size.height * 1.00f),
            strokeWidth = 40f,
            cap = StrokeCap.Square
        )

        // Vertical Spike Line
        drawLine(
            color = Gold,
            start = Offset(center.x, size.height * 0.08f),
            end = Offset(center.x, size.height * 0.92f),
            strokeWidth = 10f,
            cap = StrokeCap.Square
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

    SplashScreen(
        onNavigateToHome = {}
    )
}