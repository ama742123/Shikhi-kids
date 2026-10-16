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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
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
import com.example.data.StoriesData
import com.example.data.Story
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiCoral
import com.example.ui.theme.ShikhiCoralDark
import com.example.ui.theme.ShikhiCoralLight
import com.example.ui.theme.ShikhiGreen

@Composable
fun StoriesScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var activeStory by remember { mutableStateOf<Story?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = if (activeStory == null) "মজার গল্প" else activeStory!!.titleBn,
            subtitle = if (activeStory == null) "নীতিকথা ও শিক্ষামূলক গল্প" else "গল্পের আসর",
            starsCount = progress.stars,
            onBack = {
                if (activeStory != null) {
                    audioController.stopSpeaking()
                    activeStory = null
                } else {
                    onBack()
                }
            },
            onSpeak = {
                if (activeStory == null) {
                    audioController.speak("মজার গল্পের আসরে স্বাগতম! যেকোনো একটি গল্প বেছে নাও।")
                } else {
                    audioController.speak(activeStory!!.titleBn)
                }
            }
        )

        if (activeStory == null) {
            StoryListView(
                stories = StoriesData.storiesList,
                onSelectStory = { story ->
                    audioController.playClickSound()
                    audioController.speak(story.titleBn)
                    activeStory = story
                }
            )
        } else {
            StoryReaderView(
                story = activeStory!!,
                audioController = audioController,
                userPreferences = userPreferences,
                onFinish = {
                    activeStory = null
                }
            )
        }
    }
}

@Composable
fun StoryListView(
    stories: List<Story>,
    onSelectStory: (Story) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(stories) { story ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectStory(story) }
                    .testTag("story_card_${story.id}"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = ShikhiCoralLight,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = story.coverEmoji, fontSize = 28.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = story.titleBn,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = story.titleEn,
                            fontSize = 13.sp,
                            color = ShikhiCoralDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "💡 ${story.moralBn}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Read Story",
                        tint = ShikhiCoralDark
                    )
                }
            }
        }
    }
}

@Composable
fun StoryReaderView(
    story: Story,
    audioController: AudioController,
    userPreferences: UserPreferences,
    onFinish: () -> Unit
) {
    var currentPageIndex by remember { mutableIntStateOf(0) }
    var selectedQuestionOption by remember { mutableStateOf<Int?>(null) }

    val isLastPage = currentPageIndex >= story.pages.size
    val totalPages = story.pages.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isLastPage) {
            val page = story.pages[currentPageIndex]

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "পৃষ্ঠা ${currentPageIndex + 1} / $totalPages",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = ShikhiCoralDark
                )

                IconButton(
                    onClick = {
                        audioController.speak(page.textBn)
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(ShikhiCoralLight)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "গল্প পাঠ শুনুন",
                        tint = ShikhiCoralDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main illustration & Story Page
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiCoralLight)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = page.illustrationEmoji,
                        fontSize = 68.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = page.textBn,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 19.sp,
                            lineHeight = 28.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = page.textEn,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Page Navigation Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentPageIndex > 0) {
                            currentPageIndex--
                            audioController.playClickSound()
                        }
                    },
                    enabled = currentPageIndex > 0,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ShikhiCoralLight, contentColor = ShikhiCoralDark)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "আগের পৃষ্ঠা")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("আগের পৃষ্ঠা")
                }

                Button(
                    onClick = {
                        currentPageIndex++
                        audioController.playClickSound()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ShikhiCoralDark)
                ) {
                    Text(if (currentPageIndex == totalPages - 1) "প্রশ্নোত্তর" else "পরের পৃষ্ঠা")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "পরের পৃষ্ঠা")
                }
            }
        } else {
            // Story Comprehension Question Screen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = ShikhiCoralLight)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🎉 গল্পের প্রশ্ন!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ShikhiCoralDark)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = story.question.questionBn,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            story.question.options.forEachIndexed { index, option ->
                val isSelected = selectedQuestionOption == index
                val isCorrect = index == story.question.correctIndex
                val optBg = when {
                    selectedQuestionOption == null -> MaterialTheme.colorScheme.surface
                    isSelected && isCorrect -> Color(0xFFE8F5E9)
                    isSelected && !isCorrect -> Color(0xFFFFEBEE)
                    isCorrect -> Color(0xFFE8F5E9)
                    else -> MaterialTheme.colorScheme.surface
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable(enabled = selectedQuestionOption == null) {
                            selectedQuestionOption = index
                            if (index == story.question.correctIndex) {
                                audioController.playSuccessSound()
                                audioController.speak("দারুণ হয়েছে! তুমি গল্পটি খুব সুন্দর বুঝেছ।")
                                userPreferences.markLessonCompleted("story_${story.id}", 10)
                            } else {
                                audioController.playHintSound()
                            }
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = optBg),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = option, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        if (selectedQuestionOption != null && isCorrect) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "সঠিক", tint = ShikhiGreen)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onFinish,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiCoralDark)
            ) {
                Text("গল্প শেষ (Complete Story)", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
