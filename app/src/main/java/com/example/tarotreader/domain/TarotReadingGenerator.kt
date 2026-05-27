package com.example.tarotreader.domain

import com.example.tarotreader.data.TarotDeck
import com.example.tarotreader.ui.screens.DrawnCard
import kotlin.random.Random

object TarotReadingGenerator {

    fun generateReading(): List<DrawnCard> {

        val availableCards = TarotDeck.cards.toMutableList()

        val selectedCards = mutableListOf<DrawnCard>()

        repeat(3) {

            val randomIndex = Random.nextInt(availableCards.size)

            val selectedCard = availableCards[randomIndex]

            selectedCards.add(
                DrawnCard(selectedCard)
            )

            // Remove drawn card from deck
            availableCards.removeAt(randomIndex)
        }

        return selectedCards
    }
}