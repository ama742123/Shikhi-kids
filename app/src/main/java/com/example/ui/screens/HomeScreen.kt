package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.audio.AudioController
import com.example.data.UserProgress
import com.example.ui.theme.ShikhiBlueDark
import com.example.ui.theme.ShikhiBlueLight
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiCoral
import com.example.ui.theme.ShikhiCoralDark
import com.example.ui.theme.ShikhiCoralLight
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiGreenDark
import com.example.ui.theme.ShikhiGreenLight
import com.example.ui.theme.ShikhiPink
import com.example.ui.theme.ShikhiPinkLight
import com.example.ui.theme.ShikhiPurple
import com.example.ui.theme.ShikhiPurpleDark
import com.example.ui.theme.ShikhiPurpleLight
import com.example.ui.theme.ShikhiSkyBlue
import com.example.ui.theme.ShikhiYellow
import com.example.ui.theme.ShikhiYellowDark
import com.example.ui.theme.ShikhiYellowLight
import com.example.ui.theme.StarGold

enum class AppScreen {
    HOME,
    BENGALI_LEARNING,
    ENGLISH_LEARNING,
    MATH_LEARNING,
    STORIES,
    TRACING,
    GAMES_HUB,
    PROGRESS,
    PARENT_SECTION
}

data class HomeSectionItem(
    val screen: AppScreen,
    val titleBn: String,
    val titleEn: String,
    val tagText: String,
    val icon: ImageVector,
    val containerColor: Color,
    val contentColor: Color,
    val emoji: String
)

@Composable
fun HomeScreen(
    progress: UserProgress,
    audioController: AudioController,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val sections = listOf(
        HomeSectionItem(
            AppScreen.BENGALI_LEARNING,
            "১. বাংলা শিখি",
            "Bengali Learning",
            "অ আ ক খ",
            Icons.Default.MenuBook,
            ShikhiYellowLight,
            ShikhiYellowDark,
            "🐝"
        ),
        HomeSectionItem(
            AppScreen.ENGLISH_LEARNING,
            "২. English Learning",
            "Alphabet & Words",
            "A B C D",
            Icons.Default.Translate,
            ShikhiBlueLight,
            ShikhiBlueDark,
            "🍎"
        ),
        HomeSectionItem(
            AppScreen.MATH_LEARNING,
            "৩. গণিত শিখি",
            "Math & Counting",
            "১ ২ ৩ / 1 2 3",
            Icons.Default.Calculate,
            ShikhiGreenLight,
            ShikhiGreenDark,
            "🔢"
        ),
        HomeSectionItem(
            AppScreen.STORIES,
            "৪. মজার গল্প",
            "Bedtime & Moral Stories",
            "নীতিকথা ও গল্প",
            Icons.Default.CollectionsBookmark,
            ShikhiCoralLight,
            ShikhiCoralDark,
            "📖"
        ),
        HomeSectionItem(
            AppScreen.TRACING,
            "৫. লেখার অনুশীলন",
            "Writing & Tracing",
            "আঁকাআঁকি ও হাত ঘোরানো",
            Icons.Default.Brush,
            ShikhiPurpleLight,
            ShikhiPurpleDark,
            "✍️"
        ),
        HomeSectionItem(
            AppScreen.GAMES_HUB,
            "৬. শিক্ষামূলক গেম",
            "14 Fun Educational Games",
            "১৪টি মজার খেলা",
            Icons.Default.Extension,
            ShikhiPinkLight,
            ShikhiPink,
            "🎮"
        ),
        HomeSectionItem(
            AppScreen.PROGRESS,
            "৭. আমার অগ্রগতি",
            "Stars & Badges",
            "স্টার ও ট্রফি",
            Icons.Default.EmojiEvents,
            ShikhiYellowLight,
            StarGold,
            "🏆"
        ),
        HomeSectionItem(
            AppScreen.PARENT_SECTION,
            "৮. অভিভাবক বিভাগ",
            "Parental Settings & Support",
            "সেটিংস ও সাহায্য",
            Icons.Default.FamilyRestroom,
            Color(0xFFEDE7F6),
            Color(0xFF5E35B1),
            "👨‍👩‍👧"
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 3.dp,
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_shikhi_logo),
                        contentDescription = "Shikhi Logo",
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .border(2.dp, ShikhiYellow, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "শিখি কিডস (Shikhi Kids)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = progress.ageLevel.labelBn,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }

                // Stars count button
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = ShikhiYellowLight,
                    border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder),
                    modifier = Modifier
                        .clickable {
                            audioController.playStarSound()
                            onNavigate(AppScreen.PROGRESS)
                        }
                        .testTag("home_stars_button")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Stars",
                            tint = StarGold,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${progress.stars}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Welcome Hero Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShikhiYellowLight),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, ShikhiCardBorder)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "স্বাগতম ছোট্ট বন্ধু! 👋",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp
                            ),
                            color = ShikhiYellowDark
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "চলো আনন্দের সাথে বাংলা, ইংরেজি ও গণিত শিখি!",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 13.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(
                    onClick = {
                        audioController.playClickSound()
                        audioController.speak("স্বাগতম শিখি কিডস-এ! চলো আনন্দের সাথে শিখি।")
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .testTag("home_welcome_speak_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "স্বাগতম বার্তা শুনুন",
                        tint = ShikhiYellowDark
                    )
                }
            }
        }

        // 8 Interactive Section Cards Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 14.dp, end = 14.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("home_grid")
        ) {
            items(sections) { item ->
                SectionCard(
                    item = item,
                    onClick = {
                        audioController.playClickSound()
                        audioController.speak(item.titleBn)
                        onNavigate(item.screen)
                    }
                )
            }
        }
    }
}

@Composable
fun SectionCard(
    item: HomeSectionItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp)
            .clickable(onClick = onClick)
            .testTag("section_card_${item.screen.name}"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = item.containerColor),
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
                    color = Color.White,
                    shadowElevation = 1.dp,
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = item.emoji,
                            fontSize = 22.sp
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Text(
                        text = item.tagText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = item.contentColor,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Column {
                Text(
                    text = item.titleBn,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = item.titleEn,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
