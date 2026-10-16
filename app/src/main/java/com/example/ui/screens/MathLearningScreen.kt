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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import com.example.data.MathData
import com.example.data.MathEquation
import com.example.data.MathShape
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiGreenDark
import com.example.ui.theme.ShikhiGreenLight

@Composable
fun MathLearningScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("গণনা ১–২০", "আকৃতি (Shapes)", "তুলনা (Compare)", "যোগ ও বিয়োগ")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = "গণিত শিখি",
            subtitle = "সংখ্যা, আকৃতি ও যোগ–বিয়োগ",
            starsCount = progress.stars,
            onBack = onBack,
            onSpeak = {
                audioController.speak("মজার গণিত ক্লাসে স্বাগতম! এসো সহজে গণনা ও হিসাব শিখি।")
            }
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 12.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = ShikhiGreenDark,
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
                    modifier = Modifier.testTag("math_tab_$index")
                )
            }
        }

        when (selectedTabIndex) {
            0 -> MathCountingView(audioController, userPreferences)
            1 -> MathShapesView(audioController, userPreferences)
            2 -> MathComparisonView(audioController, userPreferences)
            3 -> MathArithmeticView(audioController, userPreferences)
        }
    }
}

@Composable
fun MathCountingView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(MathData.numbersList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak("${item.numeralBn}, ${item.nameBn}। English: ${item.numeralEn}, ${item.nameEn}")
                        userPreferences.markLessonCompleted("math_count_${item.numeralBn}", 1)
                    }
                    .testTag("math_count_row_${item.numeralBn}"),
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
                            .border(1.5.dp, ShikhiGreenDark, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${item.numeralBn} / ${item.numeralEn}",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = ShikhiGreenDark
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.nameBn,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "(${item.nameEn})",
                                fontSize = 14.sp,
                                color = ShikhiGreenDark
                            )
                        }
                        Text(
                            text = item.countItems,
                            fontSize = 16.sp
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak("${item.numeralBn}, ${item.nameBn}")
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ShikhiGreenLight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Speak Number",
                            tint = ShikhiGreenDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MathShapesView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(MathData.shapes) { shape ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak("${shape.nameBn} বা ${shape.nameEn}। ${shape.descriptionBn}")
                        userPreferences.markLessonCompleted("math_shape_${shape.nameEn}", 2)
                    }
                    .testTag("math_shape_card_${shape.nameEn}"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = ShikhiGreenLight,
                        modifier = Modifier.size(54.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = shape.symbol, fontSize = 28.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = shape.nameBn,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "(${shape.nameEn})",
                                fontSize = 14.sp,
                                color = ShikhiGreenDark
                            )
                        }
                        Text(
                            text = shape.descriptionBn,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            audioController.playClickSound()
                            audioController.speak("${shape.nameBn} বা ${shape.nameEn}")
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(ShikhiGreenLight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Speak Shape",
                            tint = ShikhiGreenDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MathComparisonView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    val comparisons = listOf(
        Triple("বড় বনাম ছোট (Big vs Small)", "🐘 হাতি বড় ➡️ 🐭 ইঁদুর ছোট", "হাতি বড় এবং ইঁদুর ছোট।"),
        Triple("বেশি বনাম কম (More vs Less)", "🍎🍎🍎🍎🍎 (৫টি আপেল) ➡️ 🍎 (১টি)", "পাঁচটি আপেল বেশি এবং একটি আপেল কম।"),
        Triple("লম্বা বনাম খাটো (Tall vs Short)", "🦒 জিরাফ লম্বা ➡️ 🐢 কচ্ছপ খাটো", "জিরাফ অনেক লম্বা এবং কচ্ছপ খাটো।")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(comparisons) { (title, visual, explanation) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        audioController.playClickSound()
                        audioController.speak(explanation)
                        userPreferences.markLessonCompleted("math_compare_$title", 2)
                    },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = ShikhiGreenDark
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = visual,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = explanation,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun MathArithmeticView(
    audioController: AudioController,
    userPreferences: UserPreferences
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val eq = MathData.equations[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "প্রশ্ন ${currentIndex + 1} / ${MathData.equations.size}",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = ShikhiGreenDark
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Equation Visual Box
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShikhiGreenLight),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiGreenDark)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = eq.visualItemsA, fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = eq.operator,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ShikhiGreenDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = eq.visualItemsB, fontSize = 28.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "=",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ShikhiGreenDark
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "?",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = ShikhiGreenDark
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = eq.promptBn,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                IconButton(
                    onClick = {
                        audioController.speak(eq.promptBn)
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Speak",
                        tint = ShikhiGreenDark
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Options
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            eq.options.forEach { option ->
                val isSelected = selectedOption == option
                val isCorrect = option == eq.answer
                val btnColor = when {
                    selectedOption == null -> MaterialTheme.colorScheme.surface
                    isSelected && isCorrect -> Color(0xFFE8F5E9)
                    isSelected && !isCorrect -> Color(0xFFFFEBEE)
                    isCorrect -> Color(0xFFE8F5E9)
                    else -> MaterialTheme.colorScheme.surface
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(72.dp)
                        .clickable(enabled = selectedOption == null) {
                            selectedOption = option
                            if (option == eq.answer) {
                                audioController.playSuccessSound()
                                audioController.speak("ঠিক বলেছো! চমৎকার উত্তর।")
                                userPreferences.addStars(5)
                            } else {
                                audioController.playHintSound()
                                audioController.speak("আবার চেষ্টা করো!")
                            }
                        }
                        .testTag("math_arithmetic_opt_$option"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = btnColor),
                    border = androidx.compose.foundation.BorderStroke(
                        1.5.dp,
                        if (isSelected) ShikhiGreenDark else ShikhiCardBorder
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$option",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (selectedOption != null) {
            Button(
                onClick = {
                    selectedOption = null
                    currentIndex = (currentIndex + 1) % MathData.equations.size
                    audioController.playClickSound()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("math_next_btn"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiGreenDark)
            ) {
                Text(
                    text = if (currentIndex + 1 == MathData.equations.size) "পুনরায় শুরু করুন (Restart)" else "পরবর্তী প্রশ্ন (Next)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
