package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioController
import com.example.data.GameType
import com.example.data.GamesData
import com.example.data.GenericGameRound
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiPink
import com.example.ui.theme.ShikhiPinkLight
import com.example.ui.theme.ShikhiYellowDark
import com.example.ui.theme.ShikhiYellowLight
import com.example.ui.theme.StarGold

@Composable
fun GamesHubScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onNavigateToTracing: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedGame by remember { mutableStateOf<GameType?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = if (selectedGame == null) "শিক্ষামূলক গেম" else selectedGame!!.titleBn,
            subtitle = if (selectedGame == null) "১৪টি মজার শিক্ষামূলক খেলা" else selectedGame!!.titleEn,
            starsCount = progress.stars,
            onBack = {
                if (selectedGame != null) {
                    selectedGame = null
                } else {
                    onBack()
                }
            },
            onSpeak = {
                if (selectedGame == null) {
                    audioController.speak("মজার গেমের ক্লাবে স্বাগতম! ১৪টি খেলা থেকে তোমার প্রিয়টি বেছে নাও।")
                } else {
                    audioController.speak(selectedGame!!.titleBn)
                }
            }
        )

        if (selectedGame == null) {
            GamesGrid(
                onSelectGame = { game ->
                    if (game == GameType.LETTER_NUMBER_TRACING) {
                        onNavigateToTracing()
                    } else {
                        selectedGame = game
                        audioController.playClickSound()
                        audioController.speak(game.titleBn)
                    }
                }
            )
        } else if (selectedGame == GameType.MEMORY_MATCH) {
            MemoryMatchGame(
                audioController = audioController,
                userPreferences = userPreferences,
                onBackToHub = { selectedGame = null }
            )
        } else {
            ActiveRoundsGame(
                gameType = selectedGame!!,
                audioController = audioController,
                userPreferences = userPreferences,
                onBackToHub = { selectedGame = null }
            )
        }
    }
}

@Composable
fun GamesGrid(
    onSelectGame: (GameType) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(14.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(GameType.entries) { game ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clickable { onSelectGame(game) }
                    .testTag("game_item_${game.id}"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = ShikhiPinkLight,
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = game.iconEmoji, fontSize = 22.sp)
                            }
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Play",
                            tint = ShikhiPink,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column {
                        Text(
                            text = game.titleBn,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = game.titleEn,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActiveRoundsGame(
    gameType: GameType,
    audioController: AudioController,
    userPreferences: UserPreferences,
    onBackToHub: () -> Unit
) {
    val rounds: List<GenericGameRound> = when (gameType) {
        GameType.BENGALI_LETTER_HUNT -> GamesData.letterHuntRounds
        GameType.ENGLISH_ALPHABET_MATCH -> GamesData.englishMatchRounds
        GameType.PICTURE_WORD_MATCH -> GamesData.pictureWordRounds
        GameType.COUNT_OBJECTS -> GamesData.countObjectsRounds
        GameType.NUMBER_SEQUENCE -> GamesData.numberSeqRounds
        GameType.SIMPLE_ADDITION -> GamesData.additionRounds
        GameType.SIMPLE_SUBTRACTION -> GamesData.subtractionRounds
        GameType.MISSING_LETTER -> GamesData.missingLetterRounds
        GameType.WORD_BUILDER -> GamesData.wordBuilderRounds
        GameType.SHAPE_MATCHING -> GamesData.shapeMatchRounds
        GameType.ALPHABET_ORDERING -> GamesData.alphabetOrderingRounds
        GameType.LISTEN_AND_CHOOSE -> GamesData.pictureWordRounds
        else -> GamesData.letterHuntRounds
    }

    var roundIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val round = rounds.getOrElse(roundIndex) { rounds.first() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "রাউন্ড ${roundIndex + 1} / ${rounds.size}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = ShikhiPink
            )

            IconButton(
                onClick = {
                    audioController.speak(round.audioPrompt)
                },
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(ShikhiPinkLight)
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Play instruction",
                    tint = ShikhiPink
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Target Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = ShikhiPinkLight),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiPink)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = round.targetItem,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ShikhiPink
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = round.promptBn,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = round.promptEn,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Options Grid (2x2)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(round.options.indices.toList()) { index ->
                val option = round.options[index]
                val isSelected = selectedOption == index
                val isCorrect = index == round.correctIndex
                val containerBg = when {
                    selectedOption == null -> MaterialTheme.colorScheme.surface
                    isSelected && isCorrect -> Color(0xFFE8F5E9)
                    isSelected && !isCorrect -> Color(0xFFFFEBEE)
                    isCorrect -> Color(0xFFE8F5E9)
                    else -> MaterialTheme.colorScheme.surface
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(84.dp)
                        .clickable(enabled = selectedOption == null) {
                            selectedOption = index
                            if (index == round.correctIndex) {
                                audioController.playSuccessSound()
                                audioController.speak("সাবাশ! দারুণ উত্তর।")
                                userPreferences.markLessonCompleted("game_${gameType.id}_$roundIndex", 5)
                            } else {
                                audioController.playHintSound()
                                audioController.speak("আবার চেষ্টা করো!")
                            }
                        }
                        .testTag("game_option_$index"),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = containerBg),
                    border = androidx.compose.foundation.BorderStroke(
                        1.5.dp,
                        if (isSelected) ShikhiPink else ShikhiCardBorder
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        if (selectedOption != null) {
            Button(
                onClick = {
                    selectedOption = null
                    if (roundIndex + 1 < rounds.size) {
                        roundIndex++
                    } else {
                        onBackToHub()
                    }
                    audioController.playClickSound()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("game_next_btn"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiPink)
            ) {
                Text(
                    text = if (roundIndex + 1 == rounds.size) "গেম সম্পন্ন (Finish Game)" else "পরবর্তী রাউন্ড (Next Round)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

data class MemoryCard(
    val id: Int,
    val content: String,
    var isFlipped: Boolean = false,
    var isMatched: Boolean = false
)

@Composable
fun MemoryMatchGame(
    audioController: AudioController,
    userPreferences: UserPreferences,
    onBackToHub: () -> Unit
) {
    val symbols = listOf("🐶", "🐱", "🍎", "⭐")
    val initialCards = remember {
        (symbols + symbols).shuffled().mapIndexed { index, symbol ->
            MemoryCard(id = index, content = symbol)
        }
    }

    val cards = remember { mutableStateListOf<MemoryCard>().apply { addAll(initialCards) } }
    var selectedFirst by remember { mutableStateOf<Int?>(null) }
    var matchesFound by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "স্মৃতি খেলা: একই কার্ডের জোড়া মেলাও!",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = ShikhiPink
        )
        Text(
            text = "মিলে যাওয়া জোড়া: $matchesFound / 4",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4x2 Grid of Cards
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(cards.indices.toList()) { index ->
                val card = cards[index]
                Card(
                    modifier = Modifier
                        .height(86.dp)
                        .clickable(enabled = !card.isFlipped && !card.isMatched) {
                            audioController.playClickSound()
                            if (selectedFirst == null) {
                                card.isFlipped = true
                                selectedFirst = index
                            } else {
                                val firstIdx = selectedFirst!!
                                card.isFlipped = true
                                if (cards[firstIdx].content == card.content) {
                                    cards[firstIdx].isMatched = true
                                    card.isMatched = true
                                    matchesFound++
                                    audioController.playSuccessSound()
                                    if (matchesFound == 4) {
                                        userPreferences.markLessonCompleted("game_memory_match", 15)
                                        audioController.speak("দারুণ স্মৃতিশক্তি! সব জোড়া মিলে গেছে।")
                                    }
                                } else {
                                    audioController.playHintSound()
                                    // Flip back after quick delay
                                    cards[firstIdx].isFlipped = false
                                    card.isFlipped = false
                                }
                                selectedFirst = null
                            }
                        }
                        .testTag("memory_card_$index"),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (card.isFlipped || card.isMatched) ShikhiYellowLight else ShikhiPinkLight
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.5.dp,
                        if (card.isMatched) ShikhiGreen else ShikhiPink
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (card.isFlipped || card.isMatched) {
                            Text(text = card.content, fontSize = 32.sp)
                        } else {
                            Text(text = "❓", fontSize = 24.sp)
                        }
                    }
                }
            }
        }

        if (matchesFound == 4) {
            Button(
                onClick = onBackToHub,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiPink)
            ) {
                Text("খেলা শেষ (Game Complete!)", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
