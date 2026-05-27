package com.example.tarotreader.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.tarotreader.data.TarotDeck
import com.example.tarotreader.domain.TarotReadingGenerator
import com.example.tarotreader.model.TarotCard
import com.example.tarotreader.ui.theme.BottomPurple
import com.example.tarotreader.ui.theme.SystemBarStyle
import com.example.tarotreader.ui.theme.TopPurple

private val Gold = Color(0xFFFFD700)
private val CardBorder = Color(0xFFB67CFF)
private val QuestionPurple = Color(0xFFA65CFF)

class DrawnCard(
    val card: TarotCard
) {
    var flipped by mutableStateOf(false)
}

@Composable
fun HomeScreen() {

    SystemBarStyle(
        statusBarColor = TopPurple,
        navigationBarColor = BottomPurple,
        darkIcons = false
    )

    var selectedCards by remember {

        mutableStateOf(
            TarotReadingGenerator.generateReading()
        )
    }

    val allFlipped = selectedCards.all { it.flipped }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        TopPurple,
                        BottomPurple
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = 28.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "TarotReader",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                selectedCards.forEach { drawnCard ->

                    FlipCard(
                        drawnCard = drawnCard
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (allFlipped)
                    "Your reading"
                else
                    "tap to flip card",

                color = Color.White,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.alpha(0.7f)
            )

            // Interpretation Section
            AnimatedVisibility(
                visible = allFlipped,

                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(28.dp))

                    TarotInterpretation(
                        cards = selectedCards.map { it.card }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Gold,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember {
                                    MutableInteractionSource()
                                }
                            ) {

                                selectedCards = TarotDeck.cards
                                    .shuffled()
                                    .take(3)
                                    .map {
                                        DrawnCard(it)
                                    }
                            }
                            .padding(
                                horizontal = 28.dp,
                                vertical = 12.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Draw Again",
                            color = Gold,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FlipCard(
    drawnCard: DrawnCard
) {

    val rotation by animateFloatAsState(
        targetValue = if (drawnCard.flipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 700,
            easing = FastOutSlowInEasing
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(
                width = 96.dp,
                height = 145.dp
            )
            .graphicsLayer {

                rotationY = rotation

                cameraDistance = 12f * density
            }
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {

                drawnCard.flipped = true
            },
        contentAlignment = Alignment.Center
    ) {

        if (rotation <= 90f) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = CardBorder
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "?",
                    color = QuestionPurple,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        else {

            Image(
                painter = painterResource(drawnCard.card.imageRes),
                contentDescription = drawnCard.card.name,

                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {

                        rotationY = 180f
                    },

                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun TarotInterpretation(
    cards: List<TarotCard>
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        cards.forEach { card ->

            Text(
                text = card.name,
                color = Gold,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = card.interpretation,
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen ()
}