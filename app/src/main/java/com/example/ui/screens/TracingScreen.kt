package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NavigateNext
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioController
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiGreen
import com.example.ui.theme.ShikhiPurpleDark
import com.example.ui.theme.ShikhiPurpleLight
import com.example.ui.theme.ShikhiYellowDark

data class StrokeLine(
    val points: List<Offset>,
    val color: Color,
    val strokeWidth: Float
)

data class TracingItem(
    val id: String,
    val character: String,
    val titleBn: String,
    val pronunciation: String,
    val isBengali: Boolean = true
)

@Composable
fun TracingScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tracingItems = listOf(
        TracingItem("bn_o", "অ", "বাংলা স্বরবর্ণ 'অ'", "অ"),
        TracingItem("bn_aa", "আ", "বাংলা স্বরবর্ণ 'আ'", "আ"),
        TracingItem("bn_i", "ই", "বাংলা স্বরবর্ণ 'ই'", "ই"),
        TracingItem("bn_ka", "ক", "বাংলা ব্যঞ্জনবর্ণ 'ক'", "ক"),
        TracingItem("bn_kha", "খ", "বাংলা ব্যঞ্জনবর্ণ 'খ'", "খ"),
        TracingItem("bn_num_1", "১", "বাংলা সংখ্যা '১'", "এক"),
        TracingItem("bn_num_2", "২", "বাংলা সংখ্যা '২'", "দুই"),
        TracingItem("en_A", "A", "English Letter 'A'", "A", false),
        TracingItem("en_B", "B", "English Letter 'B'", "B", false),
        TracingItem("en_C", "C", "English Letter 'C'", "C", false),
        TracingItem("en_1", "1", "English Number '1'", "One", false)
    )

    var currentItemIndex by remember { mutableIntStateOf(0) }
    val currentItem = tracingItems[currentItemIndex]

    val strokes = remember { mutableStateListOf<StrokeLine>() }
    var currentPoints by remember { mutableStateOf<List<Offset>>(emptyList()) }

    val colors = listOf(
        ShikhiPurpleDark,
        Color(0xFF0288D1),
        Color(0xFF2E7D32),
        Color(0xFFE64A19),
        Color(0xFFC2185B),
        ShikhiYellowDark
    )
    var selectedColor by remember { mutableStateOf(colors.first()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = "লেখার অনুশীলন",
            subtitle = "আঙুল দিয়ে বর্ণ ও সংখ্যা আঁকো",
            starsCount = progress.stars,
            onBack = onBack,
            onSpeak = {
                audioController.speak("হাতের আঙুল ঘুরিয়ে ${currentItem.character} বর্ণটি সুন্দর করে আঁকো।")
            }
        )

        // Item Selector Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tracingItems) { item ->
                val isSelected = item == currentItem
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (isSelected) ShikhiPurpleDark else MaterialTheme.colorScheme.surface,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isSelected) ShikhiPurpleDark else ShikhiCardBorder
                    ),
                    modifier = Modifier
                        .clickable {
                            currentItemIndex = tracingItems.indexOf(item)
                            strokes.clear()
                            currentPoints = emptyList()
                            audioController.playClickSound()
                            audioController.speak(item.pronunciation, isBengali = item.isBengali)
                        }
                        .testTag("tracing_item_${item.id}")
                ) {
                    Text(
                        text = item.character,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }
        }

        // Tracing Board Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .testTag("tracing_canvas_card"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(2.dp, ShikhiPurpleLight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(currentItem) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                currentPoints = listOf(offset)
                            },
                            onDrag = { change, _ ->
                                change.consume()
                                currentPoints = currentPoints + change.position
                            },
                            onDragEnd = {
                                if (currentPoints.isNotEmpty()) {
                                    strokes.add(
                                        StrokeLine(
                                            points = currentPoints,
                                            color = selectedColor,
                                            strokeWidth = 24f
                                        )
                                    )
                                    currentPoints = emptyList()
                                }
                            }
                        )
                    }
            ) {
                // Background Guide Letter
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = currentItem.character,
                        fontSize = 170.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFF0F0F0)
                    )
                }

                // Drawing Canvas
                Canvas(modifier = Modifier.fillMaxSize()) {
                    for (stroke in strokes) {
                        if (stroke.points.size > 1) {
                            val path = Path().apply {
                                moveTo(stroke.points.first().x, stroke.points.first().y)
                                for (i in 1 until stroke.points.size) {
                                    lineTo(stroke.points[i].x, stroke.points[i].y)
                                }
                            }
                            drawPath(
                                path = path,
                                color = stroke.color,
                                style = Stroke(
                                    width = stroke.strokeWidth,
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Round
                                )
                            )
                        }
                    }

                    if (currentPoints.size > 1) {
                        val activePath = Path().apply {
                            moveTo(currentPoints.first().x, currentPoints.first().y)
                            for (i in 1 until currentPoints.size) {
                                lineTo(currentPoints[i].x, currentPoints[i].y)
                            }
                        }
                        drawPath(
                            path = activePath,
                            color = selectedColor,
                            style = Stroke(
                                width = 24f,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }
                }

                // Voice Icon
                IconButton(
                    onClick = {
                        audioController.speak(currentItem.pronunciation, isBengali = currentItem.isBengali)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(ShikhiPurpleLight)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "উচ্চারণ",
                        tint = ShikhiPurpleDark
                    )
                }
            }
        }

        // Tools & Palette Controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Colors
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                colors.forEach { color ->
                    val isPicked = selectedColor == color
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(color)
                            .clickable {
                                selectedColor = color
                                audioController.playClickSound()
                            }
                            .border(
                                width = if (isPicked) 3.dp else 1.dp,
                                color = if (isPicked) Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                    )
                }
            }

            // Clear Button
            IconButton(
                onClick = {
                    strokes.clear()
                    currentPoints = emptyList()
                    audioController.playClickSound()
                },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFEBEE))
                    .testTag("tracing_clear_btn")
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "মুছে ফেলো (Clear)",
                    tint = Color(0xFFD32F2F)
                )
            }
        }

        // Next & Done Action Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    audioController.playSuccessSound()
                    audioController.speak("সাবাশ! চমৎকার আঁকা হয়েছে।")
                    userPreferences.markLessonCompleted("trace_${currentItem.id}", 5)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .testTag("tracing_done_btn"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiGreen)
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Done")
                Spacer(modifier = Modifier.width(6.dp))
                Text("সম্পন্ন (Done!)", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {
                    currentItemIndex = (currentItemIndex + 1) % tracingItems.size
                    strokes.clear()
                    currentPoints = emptyList()
                    audioController.playClickSound()
                    audioController.speak(tracingItems[currentItemIndex].pronunciation, isBengali = tracingItems[currentItemIndex].isBengali)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .testTag("tracing_next_btn"),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ShikhiPurpleDark)
            ) {
                Text("পরবর্তী (Next)", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(6.dp))
                Icon(imageVector = Icons.Default.NavigateNext, contentDescription = "Next")
            }
        }
    }
}
