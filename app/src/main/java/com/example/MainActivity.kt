package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.audio.AudioController
import com.example.data.UserPreferences
import com.example.ui.screens.AppScreen
import com.example.ui.screens.BengaliLearningScreen
import com.example.ui.screens.EnglishLearningScreen
import com.example.ui.screens.GamesHubScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MathLearningScreen
import com.example.ui.screens.ParentScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.StoriesScreen
import com.example.ui.screens.TracingScreen
import com.example.ui.theme.ShikhiKidsTheme

class MainActivity : ComponentActivity() {

    private lateinit var audioController: AudioController
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        audioController = AudioController(this)
        userPreferences = UserPreferences(this)

        setContent {
            ShikhiKidsTheme {
                val progress by userPreferences.progressFlow.collectAsStateWithLifecycle()
                var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

                BackHandler(enabled = currentScreen != AppScreen.HOME) {
                    audioController.stopSpeaking()
                    currentScreen = AppScreen.HOME
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Crossfade(
                        targetState = currentScreen,
                        label = "screen_transition",
                        modifier = Modifier.padding(innerPadding)
                    ) { screen ->
                        when (screen) {
                            AppScreen.HOME -> HomeScreen(
                                progress = progress,
                                audioController = audioController,
                                onNavigate = { nextScreen ->
                                    currentScreen = nextScreen
                                }
                            )

                            AppScreen.BENGALI_LEARNING -> BengaliLearningScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.ENGLISH_LEARNING -> EnglishLearningScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.MATH_LEARNING -> MathLearningScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.STORIES -> StoriesScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.TRACING -> TracingScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.GAMES_HUB -> GamesHubScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onNavigateToTracing = {
                                    currentScreen = AppScreen.TRACING
                                },
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.PROGRESS -> ProgressScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )

                            AppScreen.PARENT_SECTION -> ParentScreen(
                                progress = progress,
                                userPreferences = userPreferences,
                                audioController = audioController,
                                onBack = {
                                    audioController.stopSpeaking()
                                    currentScreen = AppScreen.HOME
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioController.shutdown()
    }
}
