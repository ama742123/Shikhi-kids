package com.example.data

enum class GameType(val id: String, val titleBn: String, val titleEn: String, val iconEmoji: String) {
    BENGALI_LETTER_HUNT("bn_letter_hunt", "বাংলা বর্ণ খোঁজো", "Bengali Letter Hunt", "🔍"),
    ENGLISH_ALPHABET_MATCH("en_alpha_match", "ইংরেজি বর্ণ মেলাও", "Alphabet Match", "🅰️"),
    PICTURE_WORD_MATCH("pic_word_match", "ছবি ও শব্দ মেলানো", "Picture & Word Match", "🖼️"),
    LISTEN_AND_CHOOSE("listen_choose", "শুনে সঠিকটি বাছো", "Listen & Choose", "🎧"),
    COUNT_OBJECTS("count_objects", "ফল ও তারা গণনা", "Count the Objects", "🔢"),
    NUMBER_SEQUENCE("number_seq", "পরবর্তী সংখ্যা", "Number Sequence", "➡️"),
    SIMPLE_ADDITION("simple_add", "যোগের খেলা", "Simple Addition", "➕"),
    SIMPLE_SUBTRACTION("simple_sub", "বিয়োগের খেলা", "Simple Subtraction", "➖"),
    MISSING_LETTER("missing_letter", "হারানো বর্ণ বের করো", "Missing Letter", "🧩"),
    WORD_BUILDER("word_builder", "শব্দ গঠন", "Word Builder", "🧱"),
    SHAPE_MATCHING("shape_match", "আকৃতি মেলানো", "Shape Matching", "🔺"),
    MEMORY_MATCH("memory_match", "স্মৃতি কার্ড উল্টানো", "Memory Match", "🃏"),
    ALPHABET_ORDERING("alpha_ordering", "বর্ণের সঠিক ক্রম", "Alphabet Ordering", "📋"),
    LETTER_NUMBER_TRACING("tracing_hub", "অঙ্কন ও ট্রেসিং", "Tracing Game", "✍️")
}

data class GenericGameRound(
    val promptBn: String,
    val promptEn: String,
    val audioPrompt: String,
    val targetItem: String,
    val options: List<String>,
    val correctIndex: Int,
    val hint: String
)

object GamesData {

    val letterHuntRounds = listOf(
        GenericGameRound("খুঁজে বের করো: 'অ'", "Find the letter: 'অ'", "অ বর্ণটি খুঁজে বের করো", "অ", listOf("আ", "অ", "ই", "উ"), 1, "প্রথম স্বরবর্ণ 'অ'"),
        GenericGameRound("খুঁজে বের করো: 'ক'", "Find the letter: 'ক'", "ক বর্ণটি খুঁজে বের করো", "ক", listOf("খ", "গ", "ক", "ঘ"), 2, "কলা ও কাকের 'ক'"),
        GenericGameRound("খুঁজে বের করো: 'ম'", "Find the letter: 'ম'", "ম বর্ণটি খুঁজে বের করো", "ম", listOf("ম", "ন", "প", "ব"), 0, "মাছের 'ম'"),
        GenericGameRound("খুঁজে বের করো: 'ল'", "Find the letter: 'ল'", "ল বর্ণটি খুঁজে বের করো", "ল", listOf("র", "য", "ব", "ল"), 3, "লিচুর 'ল'")
    )

    val englishMatchRounds = listOf(
        GenericGameRound("বড় হাতের 'A'-এর সাথে ছোট হাতের রূপ মেলাও", "Match uppercase 'A' to lowercase", "Match uppercase A to lowercase", "A", listOf("b", "a", "d", "c"), 1, "A -> a"),
        GenericGameRound("বড় হাতের 'B'-এর সাথে ছোট হাতের রূপ মেলাও", "Match uppercase 'B' to lowercase", "Match uppercase B to lowercase", "B", listOf("p", "d", "b", "q"), 2, "B -> b"),
        GenericGameRound("বড় হাতের 'R'-এর সাথে ছোট হাতের রূপ মেলাও", "Match uppercase 'R' to lowercase", "Match uppercase R to lowercase", "R", listOf("r", "n", "m", "h"), 0, "R -> r"),
        GenericGameRound("বড় হাতের 'T'-এর সাথে ছোট হাতের রূপ মেলাও", "Match uppercase 'T' to lowercase", "Match uppercase T to lowercase", "T", listOf("f", "l", "i", "t"), 3, "T -> t")
    )

    val pictureWordRounds = listOf(
        GenericGameRound("এই ছবির সঠিক নাম কোনটি? 🥭", "What fruit is this? 🥭", "এই ফলের নাম কী?", "🥭", listOf("আম", "আপেল", "কলা", "লিচু"), 0, "মিষ্টি পাকা আম"),
        GenericGameRound("এই ছবির সঠিক নাম কোনটি? 🐱", "What animal is this? 🐱", "এই প্রাণীর নাম কী?", "🐱", listOf("কুকুর", "বিড়াল", "বাঘ", "সিংহ"), 1, "মিউ মিউ করে ডাকে বিড়াল"),
        GenericGameRound("এই ছবির সঠিক নাম কোনটি? 🚗", "What is this? 🚗", "এই গাড়িটির নাম কী?", "🚗", listOf("জাহাজ", "নৌকা", "গাড়ি", "বিমান"), 2, "চাকাওয়ালা লাল গাড়ি"),
        GenericGameRound("এই ছবির সঠিক নাম কোনটি? 🌸", "What flower is this? 🌸", "এই ফুলের নাম কী?", "🌸", listOf("গোলাপ", "শাপলা", "ফুল", "পাতা"), 2, "সুন্দর সুবাসিত ফুল")
    )

    val countObjectsRounds = listOf(
        GenericGameRound("কয়টি তারা আছে গণনা করো: ⭐⭐⭐", "Count the stars: ⭐⭐⭐", "কয়টি তারা আছে?", "⭐⭐⭐", listOf("২", "৩", "৪", "৫"), 1, "এক, দুই, তিন"),
        GenericGameRound("কয়টি আপেল আছে গণনা করো: 🍎🍎🍎🍎", "Count the apples: 🍎🍎🍎🍎", "কয়টি আপেল আছে?", "🍎🍎🍎🍎", listOf("৩", "৫", "৪", "৬"), 2, "এক, দুই, তিন, চার"),
        GenericGameRound("কয়টি মাছ আছে গণনা করো: 🐟🐟", "Count the fish: 🐟🐟", "কয়টি মাছ আছে?", "🐟🐟", listOf("১", "২", "৩", "৪"), 1, "এক, দুই"),
        GenericGameRound("কয়টি বেলুন আছে গণনা করো: 🎈🎈🎈🎈🎈", "Count balloons: 🎈🎈🎈🎈🎈", "কয়টি বেলুন আছে?", "🎈🎈🎈🎈🎈", listOf("৪", "৬", "৭", "৫"), 3, "এক, দুই, তিন, চার, পাঁচ")
    )

    val numberSeqRounds = listOf(
        GenericGameRound("ফাঁকা জায়গায় সঠিক সংখ্যা বসাও: ১, ২, ?, ৪", "Fill in missing number: 1, 2, ?, 4", "ফাঁকা স্থানে কী বসবে?", "১, ২, ?, ৪", listOf("৩", "৫", "০", "৬"), 0, "দুইয়ের পর তিন"),
        GenericGameRound("ফাঁকা জায়গায় সঠিক সংখ্যা বসাও: ৫, ৬, ?, ৮", "Fill in missing number: 5, 6, ?, 8", "ফাঁকা স্থানে কী বসবে?", "৫, ৬, ?, ৮", listOf("৪", "৭", "৯", "১০"), 1, "ছয়ের পর সাত"),
        GenericGameRound("ফাঁকা জায়গায় সঠিক সংখ্যা বসাও: ৭, ৮, ?, ১০", "Fill in missing number: 7, 8, ?, 10", "ফাঁকা স্থানে কী বসবে?", "৭, ৮, ?, ১০", listOf("৬", "৫", "৯", "১"), 2, "আটের পর নয়")
    )

    val additionRounds = listOf(
        GenericGameRound("২ + ২ = কত?", "What is 2 + 2?", "দুই যোগ দুই সমান কত?", "২ + ২", listOf("৩", "৪", "৫", "৬"), 1, "🍎🍎 + 🍎🍎 = ৪"),
        GenericGameRound("৩ + ১ = কত?", "What is 3 + 1?", "তিন যোগ এক সমান কত?", "৩ + ১", listOf("২", "৩", "৪", "৫"), 2, "⭐⭐⭐ + ⭐ = ৪"),
        GenericGameRound("৪ + ১ = কত?", "What is 4 + 1?", "চার যোগ এক সমান কত?", "৪ + ১", listOf("৫", "৬", "৩", "৪"), 0, "চার আর এক মিলে পাঁচ")
    )

    val subtractionRounds = listOf(
        GenericGameRound("৩ - ১ = কত?", "What is 3 - 1?", "তিন থেকে এক বিয়োগ করলে কত থাকে?", "৩ - ১", listOf("১", "২", "৩", "০"), 1, "তিনটি থেকে একটি বাদ দিলে দুই"),
        GenericGameRound("৪ - ২ = কত?", "What is 4 - 2?", "চার থেকে দুই বাদ দিলে কত থাকে?", "৪ - ২", listOf("১", "৩", "২", "৪"), 2, "চার থেকে দুই গেলে থাকে দুই"),
        GenericGameRound("৫ - ১ = কত?", "What is 5 - 1?", "পাঁচ থেকে এক গেলে কত থাকে?", "৫ - ১", listOf("৪", "৩", "২", "৫"), 0, "পাঁচ থেকে এক বাদ দিলে চার")
    )

    val missingLetterRounds = listOf(
        GenericGameRound("আ _ ল (ফলের নাম)", "A _ P L E", "সঠিক বর্ণটি বেছে নাও", "আ _ ল", listOf("প", "ক", "ম", "ব"), 0, "আ + প + ল = আপেল"),
        GenericGameRound("ক _ া (ফলের নাম)", "B A N A _ A", "সঠিক বর্ণটি বেছে নাও", "ক _ া", listOf("ল", "ম", "র", "ন"), 0, "ক + ল + া = কলা"),
        GenericGameRound("C _ T (A furry animal)", "C _ T", "Fill the missing letter", "C _ T", listOf("O", "A", "E", "U"), 1, "C + A + T = CAT"),
        GenericGameRound("D _ G (Man's best friend)", "D _ G", "Fill the missing letter", "D _ G", listOf("O", "I", "U", "A"), 0, "D + O + G = DOG")
    )

    val wordBuilderRounds = listOf(
        GenericGameRound("বর্ণগুলো সাজিয়ে শব্দ বানাও: [ম] [আ]", "Unscramble: [ম] [আ]", "বর্ণ সাজিয়ে শব্দ বানাও", "আম", listOf("আম", "মা", "মল", "মম"), 0, "মিষ্টি ফল আম"),
        GenericGameRound("বর্ণগুলো সাজিয়ে শব্দ বানাও: [ল] [ফ]", "Unscramble: [ল] [ফ]", "বর্ণ সাজিয়ে শব্দ বানাও", "ফল", listOf("লফ", "ফল", "ফাল", "ফুল"), 1, "গাছের ফল"),
        GenericGameRound("Make a word from: [A] [C] [T]", "Make a word: [A] [C] [T]", "Make word CAT", "CAT", listOf("ACT", "CAT", "TAC", "ATC"), 1, "Meow animal: CAT")
    )

    val shapeMatchRounds = listOf(
        GenericGameRound("কোনটি গোল বৃত্ত (Circle)?", "Which shape is a Circle?", "কোনটি গোল বৃত্ত?", "Circle", listOf("⏹️ বর্গ", "⭕ বৃত্ত", "🔺 ত্রিভুজ", "⭐ তারা"), 1, "গোলাকার চাঁদ"),
        GenericGameRound("কোনটি তিন কোণার ত্রিভুজ (Triangle)?", "Which shape is a Triangle?", "কোনটি তিন কোণার ত্রিভুজ?", "Triangle", listOf("⭕ বৃত্ত", "⏹️ বর্গ", "🔺 ত্রিভুজ", "❤️ হৃদয়"), 2, "তিনটি কোণা"),
        GenericGameRound("কোনটি চার কোণার সমান বর্গ (Square)?", "Which shape is a Square?", "কোনটি চার কোণার বর্গ?", "Square", listOf("⏹️ বর্গ", "⭕ বৃত্ত", "⭐ তারা", "🔺 ত্রিভুজ"), 0, "চারটি সমান বাহু")
    )

    val alphabetOrderingRounds = listOf(
        GenericGameRound("অ-এর পর কোন বর্ণটি আসে?", "What comes after অ?", "অ এর পর কোন বর্ণ আসে?", "অ", listOf("ই", "আ", "উ", "ঋ"), 1, "অ, আ"),
        GenericGameRound("ক-এর পর কোন বর্ণটি আসে?", "What comes after ক?", "ক এর পর কোন বর্ণ আসে?", "ক", listOf("গ", "খ", "ঘ", "ঙ"), 1, "ক, খ"),
        GenericGameRound("What letter comes after 'A'?", "What letter comes after A?", "What comes after A?", "A", listOf("C", "B", "D", "E"), 1, "A, B, C")
    )
}
