package com.example.data

data class EnglishLetter(
    val upper: String,
    val lower: String,
    val word: String,
    val phonics: String,
    val emoji: String,
    val bnMeaning: String
)

data class EnglishVocabulary(
    val word: String,
    val category: String,
    val emoji: String,
    val sentence: String,
    val bnTranslation: String
)

data class EnglishQuizQuestion(
    val id: String,
    val question: String,
    val promptAudioText: String,
    val options: List<String>,
    val correctIndex: Int,
    val hint: String,
    val illustration: String
)

object EnglishData {

    val alphabet = listOf(
        EnglishLetter("A", "a", "Apple", "A says /æ/ as in Apple", "🍎", "আপেল"),
        EnglishLetter("B", "b", "Ball", "B says /b/ as in Ball", "⚽", "বল"),
        EnglishLetter("C", "c", "Cat", "C says /k/ as in Cat", "🐱", "বিড়াল"),
        EnglishLetter("D", "d", "Dog", "D says /d/ as in Dog", "🐶", "কুকুর"),
        EnglishLetter("E", "e", "Elephant", "E says /e/ as in Elephant", "🐘", "হাতি"),
        EnglishLetter("F", "f", "Fish", "F says /f/ as in Fish", "🐟", "মাছ"),
        EnglishLetter("G", "g", "Grapes", "G says /g/ as in Grapes", "🍇", "আঙ্গুর"),
        EnglishLetter("H", "h", "Hat", "H says /h/ as in Hat", "🎩", "টুপি"),
        EnglishLetter("I", "i", "Ice Cream", "I says /aɪ/ as in Ice Cream", "🍦", "আইসক্রিম"),
        EnglishLetter("J", "j", "Juice", "J says /dʒ/ as in Juice", "🧃", "জুস / রস"),
        EnglishLetter("K", "k", "Kite", "K says /k/ as in Kite", "🪁", "ঘুড়ি"),
        EnglishLetter("L", "l", "Lion", "L says /l/ as in Lion", "🦁", "সিংহ"),
        EnglishLetter("M", "m", "Moon", "M says /m/ as in Moon", "🌙", "চাঁদ"),
        EnglishLetter("N", "n", "Nest", "N says /n/ as in Nest", "🪺", "পাখির বাসা"),
        EnglishLetter("O", "o", "Orange", "O says /ɒ/ as in Orange", "🍊", "কমলালেবু"),
        EnglishLetter("P", "p", "Pencil", "P says /p/ as in Pencil", "✏️", "পেন্সিল"),
        EnglishLetter("Q", "q", "Queen", "Q says /kw/ as in Queen", "👑", "রানী"),
        EnglishLetter("R", "r", "Rainbow", "R says /r/ as in Rainbow", "🌈", "রংধনু"),
        EnglishLetter("S", "s", "Sun", "S says /s/ as in Sun", "☀️", "সূর্য"),
        EnglishLetter("T", "t", "Tree", "T says /t/ as in Tree", "🌳", "গাছ"),
        EnglishLetter("U", "u", "Umbrella", "U says /ʌ/ as in Umbrella", "☂️", "ছাতা"),
        EnglishLetter("V", "v", "Van", "V says /v/ as in Van", "🚐", "ভ্যান গাড়ি"),
        EnglishLetter("W", "w", "Watch", "W says /w/ as in Watch", "⌚", "হাতঘড়ি"),
        EnglishLetter("X", "x", "Xylophone", "X says /z/ as in Xylophone", "🎼", "জাইলোফোন"),
        EnglishLetter("Y", "y", "Yellow", "Y says /j/ as in Yellow", "💛", "হলুদ"),
        EnglishLetter("Z", "z", "Zebra", "Z says /z/ as in Zebra", "🦓", "জেব্রা")
    )

    val vocabularyList = listOf(
        EnglishVocabulary("Cat", "Animals", "🐱", "The cat drinks milk.", "বিড়ালটি দুধ খায়।"),
        EnglishVocabulary("Dog", "Animals", "🐶", "The dog wags its tail.", "কুকুরটি লেজ নাড়ে।"),
        EnglishVocabulary("Lion", "Animals", "🦁", "The lion is very strong.", "সিংহ খুব শক্তিশালী।"),
        EnglishVocabulary("Apple", "Fruits", "🍎", "An apple is sweet and red.", "আপেল মিষ্টি ও লাল।"),
        EnglishVocabulary("Banana", "Fruits", "🍌", "Monkeys love bananas.", "বানর কলা খেতে ভালোবাসে।"),
        EnglishVocabulary("Book", "School", "📚", "I read an interesting book.", "আমি একটি সুন্দর বই পড়ি।"),
        EnglishVocabulary("Pencil", "School", "✏️", "Draw a star with your pencil.", "পেন্সিল দিয়ে তারা আঁকো।"),
        EnglishVocabulary("Red", "Colors", "🔴", "The rose is bright red.", "গোলাপ ফুলটি উজ্জ্বল লাল।"),
        EnglishVocabulary("Blue", "Colors", "🔵", "The sky is clear blue.", "আকাশ পরিষ্কার নীল।"),
        EnglishVocabulary("Green", "Colors", "🟢", "Grass in the field is green.", "মাঠের ঘাস সবুজ।"),
        EnglishVocabulary("Good Morning", "Greetings", "🌅", "Good morning to my teacher!", "আমার শিক্ষককে শুভ সকাল!"),
        EnglishVocabulary("Thank You", "Polite Words", "🙏", "Always say thank you.", "সবসময় ধন্যবাদ বলতে হয়।")
    )

    val sightWords = listOf(
        Pair("The", "The sun shines brightly. (সূর্য উজ্জ্বলভাবে কিরণ দেয়)"),
        Pair("See", "I see a cute bird. (আমি একটি সুন্দর পাখি দেখছি)"),
        Pair("Like", "I like sweet fruits. (আমি মিষ্টি ফল পছন্দ করি)"),
        Pair("Can", "I can run and jump. (আমি দৌড়াতে ও লাফাতে পারি)"),
        Pair("Play", "Let us play together. (চলো একসাথে খেলি)")
    )

    val quizQuestions = listOf(
        EnglishQuizQuestion(
            id = "en_q1",
            question = "Which letter does 'Apple' start with?",
            promptAudioText = "Which letter does Apple start with?",
            options = listOf("B", "A", "C", "D"),
            correctIndex = 1,
            hint = "A is for Apple!",
            illustration = "🍎"
        ),
        EnglishQuizQuestion(
            id = "en_q2",
            question = "Which animal says 'Meow'?",
            promptAudioText = "Which animal says Meow?",
            options = listOf("Dog", "Lion", "Cat", "Elephant"),
            correctIndex = 2,
            hint = "A cute furry friend who loves milk!",
            illustration = "🐱"
        ),
        EnglishQuizQuestion(
            id = "en_q3",
            question = "What color is the sun?",
            promptAudioText = "What color is the sun?",
            options = listOf("Blue", "Green", "Yellow", "Purple"),
            correctIndex = 2,
            hint = "Bright yellow sunshine!",
            illustration = "☀️"
        ),
        EnglishQuizQuestion(
            id = "en_q4",
            question = "What is the lowercase letter for 'G'?",
            promptAudioText = "What is the lowercase letter for G?",
            options = listOf("q", "g", "d", "b"),
            correctIndex = 1,
            hint = "G is uppercase, g is lowercase!",
            illustration = "🔤"
        )
    )
}
