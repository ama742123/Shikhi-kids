package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.BengaliData
import com.example.data.EnglishData
import com.example.data.MathData
import com.example.data.StoriesData
import com.example.data.UserPreferences
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Shikhi Kids", appName)
  }

  @Test
  fun `verify educational dataset integrity`() {
    assertTrue(BengaliData.vowels.isNotEmpty())
    assertTrue(BengaliData.consonants.isNotEmpty())
    assertTrue(EnglishData.alphabet.size == 26)
    assertTrue(MathData.numbersList.isNotEmpty())
    assertTrue(StoriesData.storiesList.isNotEmpty())
  }

  @Test
  fun `verify user preferences progress tracking`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val prefs = UserPreferences(context)
    val initialStars = prefs.progressFlow.value.stars
    prefs.addStars(10)
    assertEquals(initialStars + 10, prefs.progressFlow.value.stars)
  }
}
