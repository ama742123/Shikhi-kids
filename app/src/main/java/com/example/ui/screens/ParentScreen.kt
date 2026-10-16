package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioController
import com.example.data.AgeLevel
import com.example.data.UserPreferences
import com.example.data.UserProgress
import com.example.ui.components.TopNavBar
import com.example.ui.theme.ShikhiCardBorder
import com.example.ui.theme.ShikhiPurpleDark
import com.example.ui.theme.ShikhiPurpleLight

@Composable
fun ParentScreen(
    progress: UserProgress,
    userPreferences: UserPreferences,
    audioController: AudioController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isGateUnlocked by remember { mutableStateOf(false) }

    // Math challenge for parental gate: e.g. 7 + 8 = 15
    val num1 = remember { 7 }
    val num2 = remember { 8 }
    val expectedAnswer = num1 + num2
    var inputAnswer by remember { mutableStateOf("") }
    var gateError by remember { mutableStateOf(false) }

    var showResetDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopNavBar(
            title = "অভিভাবক বিভাগ",
            subtitle = "Parental Settings & Support",
            starsCount = progress.stars,
            onBack = onBack,
            onSpeak = {
                audioController.speak("অভিভাবক বিভাগে স্বাগতম। শিশুদের নিরাপত্তার জন্য এটি সংরক্ষিত।")
            }
        )

        if (!isGateUnlocked) {
            // Parental Gate Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(ShikhiPurpleLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Parent Gate",
                        tint = ShikhiPurpleDark,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "অভিভাবক যাচাই (Parental Gate)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "ছোট্ট শিশুদের সুরক্ষার জন্য নিচের সাধারণ গণিত সমাধান করুন:\n$num1 + $num2 = কত?",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = inputAnswer,
                    onValueChange = {
                        inputAnswer = it
                        gateError = false
                    },
                    label = { Text("উত্তর লিখুন") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .testTag("parent_gate_input")
                )

                if (gateError) {
                    Text(
                        text = "ভুল উত্তর! আবার চেষ্টা করুন।",
                        color = Color.Red,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (inputAnswer.trim() == expectedAnswer.toString()) {
                            isGateUnlocked = true
                            audioController.playSuccessSound()
                        } else {
                            gateError = true
                            audioController.playHintSound()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp)
                        .testTag("parent_gate_submit_btn"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ShikhiPurpleDark)
                ) {
                    Icon(imageVector = Icons.Default.LockOpen, contentDescription = "Unlock")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("প্রবেশ করুন", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            // Parent Dashboard Settings
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Learning Level Setting
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "🎯 শিক্ষার্থীর বয়স ও স্তর (Age Level)",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            AgeLevel.entries.forEach { level ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            userPreferences.setAgeLevel(level)
                                            audioController.playClickSound()
                                        }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = progress.ageLevel == level,
                                        onClick = {
                                            userPreferences.setAgeLevel(level)
                                            audioController.playClickSound()
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column {
                                        Text(text = level.labelBn, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                                        Text(text = level.labelEn, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }
                    }
                }

                // Sound & Audio Settings
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "🔊 শব্দ ও উচ্চারণ (Sound & Speech)",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = "সাউন্ড এফেক্টস (Sound Effects)", fontSize = 15.sp)
                                    Text(text = "বোতাম ও উত্তরের শব্দ", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Switch(
                                    checked = progress.soundEnabled,
                                    onCheckedChange = {
                                        userPreferences.setSoundEnabled(it)
                                        audioController.isSoundEnabled = it
                                    }
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = "কণ্ঠ উচ্চারণ (Voice & Phonics)", fontSize = 15.sp)
                                    Text(text = "বর্ণ ও শব্দের অডিও পাঠ", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Switch(
                                    checked = progress.voiceEnabled,
                                    onCheckedChange = {
                                        userPreferences.setVoiceEnabled(it)
                                        audioController.isVoiceEnabled = it
                                    }
                                )
                            }
                        }
                    }
                }

                // Support & Contact Section
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "📬 যোগাযোগ ও সহায়তা (Support & Help)",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "যেকোনো মতামত, পরামর্শ বা সমস্যার জন্য আমাদের সাথে যোগাযোগ করুন:",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Email display box
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = ShikhiPurpleLight,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "support.shikhi@gmail.com",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = ShikhiPurpleDark
                                    )

                                    IconButton(
                                        onClick = {
                                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                            val clip = ClipData.newPlainText("Support Email", "support.shikhi@gmail.com")
                                            clipboard.setPrimaryClip(clip)
                                            Toast.makeText(context, "ইমেইল ঠিকানা কপি করা হয়েছে!", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy Email",
                                            tint = ShikhiPurpleDark
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                                            data = Uri.parse("mailto:support.shikhi@gmail.com")
                                            putExtra(Intent.EXTRA_SUBJECT, "Shikhi Kids Support")
                                        }
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        Toast.makeText(context, "ইমেইল অ্যাপ পাওয়া যায়নি। ঠিকানা কপি করুন।", Toast.LENGTH_LONG).show()
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("parent_send_email_btn"),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = ShikhiPurpleDark)
                            ) {
                                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("সাপোর্ট ইমেইল পাঠান (Send Email)", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Child Safety & Privacy Information
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Security, contentDescription = "Security", tint = Color(0xFF2E7D32))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "🔒 শিশু সুরক্ষা ও গোপনীয়তা (Child Safety)",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "• সম্পূর্ণ বিজ্ঞাপনমুক্ত (Zero Ads)\n• কোনো ব্যক্তিগত তথ্য বা অ্যাকাউন্ট প্রয়োজন নেই\n• ১০০% অফলাইনে নিরাপদ ব্যবহার\n• গুগল প্লে ফ্যামিলি ও COPPA নীতিমালার অনুসারী",
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // App Info & Reset Progress
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ShikhiCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "অ্যাপ সংস্করণ: ১.০.০ (Build 1.0)",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            TextButton(
                                onClick = { showResetDialog = true },
                                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFD32F2F)),
                                modifier = Modifier.testTag("parent_reset_progress_btn")
                            ) {
                                Icon(imageVector = Icons.Default.RestartAlt, contentDescription = "Reset")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("সমস্ত অগ্রগতি রিসেট করুন (Reset All Progress)")
                            }
                        }
                    }
                }
            }
        }

        if (showResetDialog) {
            AlertDialog(
                onDismissRequest = { showResetDialog = false },
                title = { Text("অগ্রগতি রিসেট করবেন?") },
                text = { Text("সমস্ত স্টার, অর্জিত ব্যাজ ও সমাপ্ত পাঠের রেকর্ড মুছে ফেলা হবে। আপনি কি নিশ্চিত?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            userPreferences.resetProgress()
                            showResetDialog = false
                            Toast.makeText(context, "অগ্রগতি রিসেট করা হয়েছে", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("হ্যাঁ, রিসেট করুন", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showResetDialog = false }) {
                        Text("বাতিল")
                    }
                }
            )
        }
    }
}
