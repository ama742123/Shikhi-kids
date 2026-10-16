package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.EnglishData
import com.example.data.EnglishLetter
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiBlueDark
import com.example.ui.theme.ShikhiBlueLight
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiSkyBlue

@Composable
fun EnglishLearningScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Alphabet A–Z", "Vocabulary", "Sight Words", "Quiz")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = "English Learning",
            subtitle = "Alphabet, Phonics & Words",
            starsCount = progress.stars,
            onBack = onBack,
            onSpeak = {
                audioController.speak("Welcome to English Learning! Tap any letter to learn phonics and words.", isBengali = false)
            }
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 12.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = ShikhiBlueDark,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        audioController.playClickSound()
                    },
                    text = {
                        Text(
                            text = tabTitle,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 15.sp
                        )
                    },
                    modifier = Modifier.testTag("en_tab_$index")
                )
            }
        }

        when (selectedTabIndex) {
            0 -> EnglishAlphabetGrid(audioController, userPreferences)
            1 -> EnglishVocabularyView(audioController, userPreferences)
            2 -> EnglishSightWordsView(audioController, userPreferences)
            3 -> EnglishQuizView(audioController, userPreferences)
        }
    }
}

@Composable
fun EnglishAlphabetGrid(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    var selectedLetter by remember { mutableStateOf(EnglishData.alphabet.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Detailed Card Preview
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .testTag("en_letter_detail_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShikhiBlueLight),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiSkyBlue)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(2.dp, ShikhiBlueDark, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${selectedLetter.upper} ${selectedLetter.lower}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ShikhiBlueDark
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${selectedLetter.upper} for ${selectedLetter.word}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = selectedLetter.emoji, fontSize = 22.sp)
                    }
                    Text(
                        text = selectedLetter.phonics,
                        style = MaterialTheme.typography.bodySmall,
                        color = ShikhiBlueDark
                    )
                    Text(
                        text = "বাংলা: ${selectedLetter.bnMeaning}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(
                    onClick = {
                        audioController.playClickSound()
                        audioController.speak("${selectedLetter.upper}, ${selectedLetter.lower}. ${selectedLetter.upper} is for ${selectedLetter.word}.", isBengali = false)
                        userPreferences.markLessonCompleted("en_alpha_${selectedLetter.upper}", 1)
                    },
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(ShikhiBlueDark)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Speak Letter",
                        tint = Color.White
                    )
                }
            }
        }

        // Alphabet Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(EnglishData.alphabet) { item ->
                val isSelected = selectedLetter == item
                Card(
                    modifier = Modifier
                        .size(72.dp)
                        .clickable {
                            selectedLetter = item
                            audioController.playClickSound()
                            audioController.speak("${item.upper}. ${item.word}.", isBengali = false)
                            userPreferences.markLessonCompleted("en_alpha_${item.upper}", 1)
                        }
                        .testTag("en_card_${item.upper}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) ShikhiBlueDark else MaterialTheme.colorScheme.surface
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        if (isSelected) 2.dp else 1.dp,
                        if (isSelected) ShikhiBlueDark else ShikhiCardBorder
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${item.upper} ${item.lower}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = item.emoji,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EnglishVocabularyView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(EnglishData.vocabularyList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak("${item.word}. ${item.sentence}", isBengali = false)
                        userPreferences.markLessonCompleted("en_vocab_${item.word}", 2)
                    }
                    .testTag("en_vocab_card_${item.word}"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = ShikhiBlueLight,
                        modifier = Modifier.size(54.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = item.emoji, fontSize = 28.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.word,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "(${item.category})",
                                fontSize = 12.sp,
                                color = ShikhiBlueDark
                            )
                        }
                        Text(
                            text = item.sentence,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = item.bnTranslation,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak("${item.word}. ${item.sentence}", isBengali = false)
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ShikhiBlueLight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Listen",
                            tint = ShikhiBlueDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnglishSightWordsView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(EnglishData.sightWords) { (word, sentenceWithBn) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak("$word. $sentenceWithBn", isBengali = false)
                        userPreferences.markLessonCompleted("en_sight_$word", 2)
                    },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = word,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = ShikhiBlueDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = sentenceWithBn,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak(word, isBengali = false)
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ShikhiBlueLight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Listen",
                            tint = ShikhiBlueDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnglishQuizView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var showHint by remember { mutableStateOf(false) }

    val question = EnglishData.quizQuestions.getOrNull(currentQuestionIndex) ?: EnglishData.quizQuestions.first()

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
                text = "Question ${currentQuestionIndex + 1} of ${EnglishData.quizQuestions.size}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = ShikhiBlueDark
            )

            IconButton(
                onClick = {
                    showHint = !showHint
                    audioController.playHintSound()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = "Hint",
                    tint = ShikhiBlueDark
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShikhiBlueLight),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiSkyBlue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = question.illustration,
                    fontSize = 54.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(
                    onClick = {
                        audioController.speak(question.promptAudioText, isBengali = false)
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Speak Question",
                        tint = ShikhiBlueDark
                    )
                }
            }
        }

        AnimatedVisibility(visible = showHint) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE))
            ) {
                Text(
                    text = "💡 Hint: ${question.hint}",
                    fontSize = 13.sp,
                    color = ShikhiBlueDark,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        question.options.forEachIndexed { index, option ->
            val isSelected = selectedOptionIndex == index
            val isCorrect = index == question.correctIndex
            val optionColor = when {
                selectedOptionIndex == null -> MaterialTheme.colorScheme.surface
                isSelected && isCorrect -> Color(0xFFE8F5E9)
                isSelected && !isCorrect -> Color(0xFFFFEBEE)
                isCorrect -> Color(0xFFE8F5E9)
                else -> MaterialTheme.colorScheme.surface
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clickable(enabled = selectedOptionIndex == null) {
                        selectedOptionIndex = index
                        if (index == question.correctIndex) {
                            audioController.playSuccessSound()
                            audioController.speak("Great job! That is correct.", isBengali = false)
                            userPreferences.addStars(5)
                        } else {
                            audioController.playHintSound()
                            audioController.speak("Try again little friend!", isBengali = false)
                        }
                    }
                    .testTag("en_quiz_opt_$index"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = optionColor),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isSelected) ShikhiBlueDark else ShikhiCardBorder
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = option,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (selectedOptionIndex != null && isCorrect) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Correct",
                            tint = ShikhiGreen
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (selectedOptionIndex != null) {
            Button(
                onClick = {
                    selectedOptionIndex = null
                    showHint = false
                    currentQuestionIndex = (currentQuestionIndex + 1) % EnglishData.quizQuestions.size
                    audioController.playClickSound()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("en_quiz_next_btn"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiBlueDark)
            ) {
                Text(
                    text = if (currentQuestionIndex + 1 == EnglishData.quizQuestions.size) "Restart Quiz" else "Next Question",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
