package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Refresh
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
import com.example.data.BengaliData
import com.example.data.BengaliLetter
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiGreenLight
import com.example.ui.theme.ShikhiSkyBlue
import com.example.ui.theme.ShikhiYellowDark
import com.example.ui.theme.ShikhiYellowLight

@Composable
fun BengaliLearningScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("স্বরবর্ণ", "ব্যঞ্জনবর্ণ", "শব্দ ও ছবি", "সংখ্যা", "কুইজ")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = "বাংলা শিখি",
            subtitle = "স্বরবর্ণ, ব্যঞ্জনবর্ণ ও শব্দ",
            starsCount = progress.stars,
            onBack = onBack,
            onSpeak = {
                audioController.speak("বাংলা শেখার ক্লাসে স্বাগতম! এখানে তুমি বর্ণ ও শব্দ শিখবে।")
            }
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 12.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        audioController.playClickSound()
                        audioController.speak(tabTitle)
                    },
                    text = {
                        Text(
                            text = tabTitle,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 15.sp
                        )
                    },
                    modifier = Modifier.testTag("bn_tab_$index")
                )
            }
        }

        when (selectedTabIndex) {
            0 -> LettersGrid(BengaliData.vowels, audioController, "vowels", userPreferences)
            1 -> LettersGrid(BengaliData.consonants, audioController, "consonants", userPreferences)
            2 -> VocabularyView(audioController, userPreferences)
            3 -> NumbersView(audioController, userPreferences)
            4 -> BengaliQuizView(audioController, userPreferences)
        }
    }
}

@Composable
fun LettersGrid(
    letters: List<BengaliLetter>,
    audioController: AudioController,
    categoryKey: String,
    userPreferences: UserPreferences
) {
    var selectedLetter by remember { mutableStateOf<BengaliLetter?>(letters.firstOrNull()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Detailed Preview Card
        selectedLetter?.let { letter ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .testTag("letter_detail_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = ShikhiYellowLight),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiCardBorder)
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
                            .border(2.dp, ShikhiYellowDark, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter.letter,
                            fontSize = 44.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = ShikhiYellowDark
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${letter.letter}-তে ${letter.word}",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = letter.emoji, fontSize = 22.sp)
                        }
                        Text(
                            text = letter.phonetic,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak("${letter.letter}. ${letter.letter}-তে ${letter.word}.")
                            userPreferences.markLessonCompleted("bn_letter_${letter.letter}", 1)
                        },
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(ShikhiYellowDark)
                            .testTag("letter_speak_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "উচ্চারণ শুনুন",
                            tint = Color.White
                        )
                    }
                }
            }
        }

        // Letter Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(letters) { item ->
                val isSelected = selectedLetter == item
                Card(
                    modifier = Modifier
                        .size(72.dp)
                        .clickable {
                            selectedLetter = item
                            audioController.playClickSound()
                            audioController.speak("${item.letter}. ${item.word}.")
                            userPreferences.markLessonCompleted("bn_${categoryKey}_${item.letter}", 1)
                        }
                        .testTag("bn_letter_card_${item.letter}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) ShikhiYellowDark else MaterialTheme.colorScheme.surface
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        if (isSelected) 2.dp else 1.dp,
                        if (isSelected) ShikhiYellowDark else ShikhiCardBorder
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = item.letter,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = item.word,
                                fontSize = 11.sp,
                                color = if (isSelected) Color.White.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VocabularyView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(BengaliData.vocabularyList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak("${item.wordBn}। ${item.sentenceBn}")
                        userPreferences.markLessonCompleted("bn_vocab_${item.wordBn}", 2)
                    }
                    .testTag("vocab_card_${item.wordBn}"),
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
                        color = ShikhiYellowLight,
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
                                text = item.wordBn,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "(${item.wordEn})",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = item.sentenceBn,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak("${item.wordBn}। ${item.sentenceBn}")
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ShikhiYellowLight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "কথা শুনুন",
                            tint = ShikhiYellowDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NumbersView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(BengaliData.numbersBn) { (numBn, nameBn, icons) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak("$numBn, $nameBn")
                        userPreferences.markLessonCompleted("bn_num_$numBn", 2)
                    }
                    .testTag("bn_number_row_$numBn"),
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
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(ShikhiGreenLight)
                            .border(1.5.dp, ShikhiGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = numBn,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = ShikhiGreen
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = nameBn,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = icons,
                            fontSize = 16.sp
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak("$numBn, $nameBn")
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ShikhiGreenLight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "সংখ্যা শুনুন",
                            tint = ShikhiGreen
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BengaliQuizView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var showHint by remember { mutableStateOf(false) }
    var hasAnsweredCorrectly by remember { mutableStateOf(false) }

    val question = BengaliData.quizQuestions.getOrNull(currentQuestionIndex) ?: BengaliData.quizQuestions.first()

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
                text = "প্রশ্ন ${currentQuestionIndex + 1} / ${BengaliData.quizQuestions.size}",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.primary
            )

            IconButton(
                onClick = {
                    showHint = !showHint
                    audioController.playHintSound()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = "ইঙ্গিত (Hint)",
                    tint = ShikhiYellowDark
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Question Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShikhiYellowLight),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiCardBorder)
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
                        audioController.speak(question.promptAudioText)
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "প্রশ্ন শুনুন",
                        tint = ShikhiYellowDark
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7))
            ) {
                Text(
                    text = "💡 ইঙ্গিত: ${question.hint}",
                    fontSize = 13.sp,
                    color = Color(0xFF827717),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Options
        question.options.forEachIndexed { index, option ->
            val isSelected = selectedOptionIndex == index
            val isCorrect = index == question.correctIndex
            val optionColor = when {
                selectedOptionIndex == null -> MaterialTheme.colorScheme.surface
                isSelected && isCorrect -> Color(0xFFE8F5E9)
                isSelected && !isCorrect -> Color(0xFFFFEBEE)
                isCorrect && hasAnsweredCorrectly -> Color(0xFFE8F5E9)
                else -> MaterialTheme.colorScheme.surface
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .clickable(enabled = selectedOptionIndex == null) {
                        selectedOptionIndex = index
                        if (index == question.correctIndex) {
                            hasAnsweredCorrectly = true
                            audioController.playSuccessSound()
                            audioController.speak("সাবাশ! চমৎকার হয়েছে।")
                            userPreferences.addStars(5)
                        } else {
                            audioController.playHintSound()
                            audioController.speak("আরেকবার চেষ্টা করো ছোট্ট বন্ধু!")
                        }
                    }
                    .testTag("bn_quiz_opt_$index"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = optionColor),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isSelected) MaterialTheme.colorScheme.primary else ShikhiCardBorder
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
                            contentDescription = "সঠিক",
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
                    hasAnsweredCorrectly = false
                    currentQuestionIndex = (currentQuestionIndex + 1) % BengaliData.quizQuestions.size
                    audioController.playClickSound()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("bn_quiz_next_btn"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiYellowDark)
            ) {
                Text(
                    text = if (currentQuestionIndex + 1 == BengaliData.quizQuestions.size) "পুনরায় শুরু করুন (Restart)" else "পরবর্তী প্রশ্ন (Next)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
