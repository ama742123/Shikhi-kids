package com.example.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.sin

/**
 * AudioController provides:
 * 1. Native synthesized sound effects via AudioTrack (100% offline, zero missing asset errors).
 * 2. On-device Text-To-Speech for Bengali and English with child-friendly pitch and speed.
 * 3. Mute/unmute settings.
 */
class AudioController(private val context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isTtsReady = false
    private var isBengaliAvailable = false
    private var isEnglishAvailable = false

    var isSoundEnabled: Boolean = true
    var isVoiceEnabled: Boolean = true

    private val audioScope = CoroutineScope(Dispatchers.Default)

    init {
        try {
            tts = TextToSpeech(context.applicationContext, this)
        } catch (e: Exception) {
            Log.e("AudioController", "Error initializing TextToSpeech", e)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.let { engine ->
                // Check Bengali voice
                val bnLocale = Locale("bn", "IN")
                val bnResult = engine.isLanguageAvailable(bnLocale)
                isBengaliAvailable = bnResult >= TextToSpeech.LANG_AVAILABLE
                if (!isBengaliAvailable) {
                    val genericBn = Locale("bn")
                    isBengaliAvailable = engine.isLanguageAvailable(genericBn) >= TextToSpeech.LANG_AVAILABLE
                }

                // Check English voice
                val enResult = engine.isLanguageAvailable(Locale.US)
                isEnglishAvailable = enResult >= TextToSpeech.LANG_AVAILABLE

                // Set kid-friendly speech rate and pitch
                engine.setPitch(1.15f) // slightly higher, friendlier tone
                engine.setSpeechRate(0.85f) // slower, clearer for children
                isTtsReady = true
            }
        } else {
            isTtsReady = false
        }
    }

    /**
     * Speaks text in Bengali or English based on isBengali flag.
     */
    fun speak(text: String, isBengali: Boolean = true) {
        if (!isVoiceEnabled || !isTtsReady || text.isBlank()) return

        try {
            val targetLocale = if (isBengali) {
                if (isBengaliAvailable) Locale("bn", "IN") else Locale.getDefault()
            } else {
                Locale.US
            }
            tts?.language = targetLocale
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "Shikhi_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.e("AudioController", "TTS speak failed", e)
        }
    }

    fun stopSpeaking() {
        try {
            tts?.stop()
        } catch (e: Exception) {
            Log.e("AudioController", "TTS stop failed", e)
        }
    }

    /**
     * Synthesizes and plays a friendly success chime (C5 - E5 - G5 - C6)
     */
    fun playSuccessSound() {
        if (!isSoundEnabled) return
        audioScope.launch {
            playToneSequence(listOf(523.25, 659.25, 783.99, 1046.50), 120)
        }
    }

    /**
     * Synthesizes a gentle click/tap pop
     */
    fun playClickSound() {
        if (!isSoundEnabled) return
        audioScope.launch {
            playTone(800.0, 45, 0.4)
        }
    }

    /**
     * Synthesizes a twinkling star reward sound
     */
    fun playStarSound() {
        if (!isSoundEnabled) return
        audioScope.launch {
            playToneSequence(listOf(880.0, 1174.66, 1760.0), 90)
        }
    }

    /**
     * Synthesizes a warm hint / retry tone
     */
    fun playHintSound() {
        if (!isSoundEnabled) return
        audioScope.launch {
            playToneSequence(listOf(440.0, 392.0), 140)
        }
    }

    /**
     * Pure Kotlin tone generation for 100% offline audio without external files
     */
    private fun playToneSequence(frequencies: List<Double>, durationMs: Int) {
        val sampleRate = 22050
        val totalSamples = frequencies.size * (sampleRate * durationMs / 1000)
        val buffer = ShortArray(totalSamples)
        var offset = 0

        for (freq in frequencies) {
            val noteSamples = sampleRate * durationMs / 1000
            for (i in 0 until noteSamples) {
                val envelope = when {
                    i < noteSamples * 0.1 -> i / (noteSamples * 0.1)
                    i > noteSamples * 0.7 -> (noteSamples - i) / (noteSamples * 0.3)
                    else -> 1.0
                }
                val angle = 2.0 * Math.PI * i / (sampleRate / freq)
                val sample = (sin(angle) * envelope * Short.MAX_VALUE * 0.55).toInt().toShort()
                buffer[offset + i] = sample
            }
            offset += noteSamples
        }

        playRawBuffer(buffer, sampleRate)
    }

    private fun playTone(frequency: Double, durationMs: Int, volume: Double = 0.5) {
        val sampleRate = 22050
        val numSamples = (sampleRate * durationMs / 1000)
        val buffer = ShortArray(numSamples)

        for (i in 0 until numSamples) {
            val envelope = when {
                i < numSamples * 0.15 -> i / (numSamples * 0.15)
                i > numSamples * 0.6 -> (numSamples - i) / (numSamples * 0.4)
                else -> 1.0
            }
            val angle = 2.0 * Math.PI * i / (sampleRate / frequency)
            buffer[i] = (sin(angle) * envelope * Short.MAX_VALUE * volume).toInt().toShort()
        }

        playRawBuffer(buffer, sampleRate)
    }

    private fun playRawBuffer(buffer: ShortArray, sampleRate: Int) {
        try {
            val minBufSize = AudioTrack.getMinBufferSize(
                sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            )
            val trackSize = maxOf(buffer.size * 2, minBufSize)

            val track = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(trackSize)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()
            } else {
                @Suppress("DEPRECATION")
                AudioTrack(
                    android.media.AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    trackSize,
                    AudioTrack.MODE_STATIC
                )
            }

            track.write(buffer, 0, buffer.size)
            track.play()
            Thread.sleep((buffer.size.toDouble() / sampleRate * 1000).toLong() + 50)
            track.stop()
            track.release()
        } catch (e: Exception) {
            Log.e("AudioController", "Audio synthesis playback failed", e)
        }
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Exception) {
            Log.e("AudioController", "Shutdown error", e)
        }
    }
}
