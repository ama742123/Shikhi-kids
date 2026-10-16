package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AgeLevel(val labelBn: String, val labelEn: String, val ageRange: String) {
    AGE_3_4("ছোট্ট শিক্ষার্থী (৩–৪ বছর)", "Little Learner (3–4 yrs)", "3–4"),
    AGE_5_6("প্রাথমিক শিক্ষার্থী (৫–৬ বছর)", "Junior Explorer (5–6 yrs)", "5–6"),
    AGE_7_8("উন্নত শিক্ষার্থী (৭–৮ বছর)", "Young Champion (7–8 yrs)", "7–8")
}

data class UserProgress(
    val stars: Int = 0,
    val completedLessons: Set<String> = emptySet(),
    val unlockedBadges: Set<String> = emptySet(),
    val ageLevel: AgeLevel = AgeLevel.AGE_3_4,
    val soundEnabled: Boolean = true,
    val voiceEnabled: Boolean = true
)

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("shikhi_kids_prefs", Context.MODE_PRIVATE)

    private val _progressFlow = MutableStateFlow(loadProgress())
    val progressFlow: StateFlow<UserProgress> = _progressFlow.asStateFlow()

    private fun loadProgress(): UserProgress {
        val stars = prefs.getInt("stars", 15) // starter 15 stars to encourage kid
        val completed = prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()
        val badges = prefs.getStringSet("unlocked_badges", setOf("welcome_badge")) ?: setOf("welcome_badge")
        val ageIndex = prefs.getInt("age_level", AgeLevel.AGE_3_4.ordinal)
        val ageLevel = AgeLevel.entries.getOrElse(ageIndex) { AgeLevel.AGE_3_4 }
        val sound = prefs.getBoolean("sound_enabled", true)
        val voice = prefs.getBoolean("voice_enabled", true)
        return UserProgress(stars, completed, badges, ageLevel, sound, voice)
    }

    fun addStars(amount: Int) {
        val current = _progressFlow.value
        val newStars = current.stars + amount
        prefs.edit().putInt("stars", newStars).apply()
        _progressFlow.value = current.copy(stars = newStars)
        checkBadges(newStars, current.completedLessons.size)
    }

    fun markLessonCompleted(lessonId: String, rewardStars: Int = 5) {
        val current = _progressFlow.value
        val newCompleted = current.completedLessons + lessonId
        val newStars = current.stars + rewardStars
        prefs.edit()
            .putStringSet("completed_lessons", newCompleted)
            .putInt("stars", newStars)
            .apply()
        _progressFlow.value = current.copy(
            completedLessons = newCompleted,
            stars = newStars
        )
        checkBadges(newStars, newCompleted.size)
    }

    private fun checkBadges(stars: Int, completedCount: Int) {
        val badges = _progressFlow.value.unlockedBadges.toMutableSet()
        if (completedCount >= 1) badges.add("first_step")
        if (completedCount >= 5) badges.add("bright_star")
        if (completedCount >= 10) badges.add("bengali_master")
        if (completedCount >= 20) badges.add("math_magician")
        if (stars >= 50) badges.add("star_collector")
        if (stars >= 100) badges.add("super_champion")

        if (badges.size > _progressFlow.value.unlockedBadges.size) {
            prefs.edit().putStringSet("unlocked_badges", badges).apply()
            _progressFlow.value = _progressFlow.value.copy(unlockedBadges = badges)
        }
    }

    fun setAgeLevel(level: AgeLevel) {
        prefs.edit().putInt("age_level", level.ordinal).apply()
        _progressFlow.value = _progressFlow.value.copy(ageLevel = level)
    }

    fun setSoundEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("sound_enabled", enabled).apply()
        _progressFlow.value = _progressFlow.value.copy(soundEnabled = enabled)
    }

    fun setVoiceEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("voice_enabled", enabled).apply()
        _progressFlow.value = _progressFlow.value.copy(voiceEnabled = enabled)
    }

    fun resetProgress() {
        prefs.edit().clear().apply()
        val resetState = UserProgress(
            stars = 0,
            completedLessons = emptySet(),
            unlockedBadges = setOf("welcome_badge"),
            ageLevel = AgeLevel.AGE_3_4,
            soundEnabled = true,
            voiceEnabled = true
        )
        _progressFlow.value = resetState
    }
}
