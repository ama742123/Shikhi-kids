package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiYellowDark
import com.example.ui.theme.ShikhiYellowLight
import com.example.ui.theme.StarGold

data class BadgeInfo(
    val id: String,
    val titleBn: String,
    val titleEn: String,
    val description: String,
    val emoji: String
)

@Composable
fun ProgressScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val allBadges = listOf(
        BadgeInfo("welcome_badge", "শিখির বন্ধু", "Shikhi's Friend", "অ্যাপে প্রথম পদার্পণ", "🦉"),
        BadgeInfo("first_step", "প্রথম ধাপ", "First Step", "যেকোনো ১টি পাঠ সম্পন্ন", "🌱"),
        BadgeInfo("bright_star", "উজ্জ্বল তারকা", "Bright Star", "৫টি পাঠ সম্পূর্ণ", "⭐"),
        BadgeInfo("bengali_master", "বাংলা বিশারদ", "Bengali Explorer", "১০টি বাংলা পাঠ সম্পূর্ণ", "📚"),
        BadgeInfo("math_magician", "গণিত জাদুকর", "Math Wizard", "২০টি সংখ্যা ও গণনা সম্পূর্ণ", "🔢"),
        BadgeInfo("star_collector", "স্টার সংগ্রাহক", "Star Collector", "৫০টি স্টার অর্জন", "🌟"),
        BadgeInfo("super_champion", "সুপার চ্যাম্পিয়ন", "Super Champion", "১০০টি স্টার অর্জন", "🏆")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = "আমার অগ্রগতি",
            subtitle = "স্টার, ট্রফি ও ব্যাজ",
            starsCount = progress.stars,
            onBack = onBack,
            onSpeak = {
                audioController.speak("সাবাশ ছোট্ট বন্ধু! তুমি মোট ${progress.stars}টি স্টার এবং ${progress.unlockedBadges.size}টি ব্যাজ অর্জন করেছ।")
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Hero Star Summary Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = ShikhiYellowLight),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiCardBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            modifier = Modifier.size(76.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Stars",
                                    tint = StarGold,
                                    modifier = Modifier.size(54.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "${progress.stars} টি স্টার!",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = ShikhiYellowDark
                        )

                        Text(
                            text = "মোট সম্পন্ন কার্যক্রম: ${progress.completedLessons.size} টি",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Progress towards next milestone
                        val targetStars = 50
                        val progressRatio = (progress.stars % targetStars).toFloat() / targetStars.toFloat()
                        LinearProgressIndicator(
                            progress = { progressRatio.coerceIn(0f, 1f) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            color = ShikhiYellowDark,
                            trackColor = Color.White
                        )
                    }
                }
            }

            // Badges Section Header
            item {
                Text(
                    text = "🏅 অর্জিত ব্যাজসমূহ (Achievement Badges)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Badges List
            items(allBadges) { badge ->
                val isUnlocked = progress.unlockedBadges.contains(badge.id)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("badge_card_${badge.id}"),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else Color(0xFFF5F5F5)
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isUnlocked) ShikhiCardBorder else Color(0xFFE0E0E0)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (isUnlocked) ShikhiYellowLight else Color(0xFFE0E0E0),
                            modifier = Modifier.size(54.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                if (isUnlocked) {
                                    Text(text = badge.emoji, fontSize = 28.sp)
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Locked",
                                        tint = Color.Gray,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = badge.titleBn,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "(${badge.titleEn})",
                                    fontSize = 12.sp,
                                    color = if (isUnlocked) ShikhiYellowDark else Color.Gray
                                )
                            }
                            Text(
                                text = badge.description,
                                fontSize = 13.sp,
                                color = if (isUnlocked) MaterialTheme.colorScheme.onSurfaceVariant else Color.Gray
                            )
                        }

                        if (isUnlocked) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Unlocked",
                                tint = ShikhiGreen,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
