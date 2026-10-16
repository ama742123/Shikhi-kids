package com.example.data

data class BengaliLetter(
    val letter: String,
    val word: String,
    val meaningEn: String,
    val emoji: String,
    val phonetic: String,
    val isVowel: Boolean = true
)

data class BengaliVocabulary(
    val wordBn: String,
    val wordEn: String,
    val category: String,
    val emoji: String,
    val sentenceBn: String
)

data class BengaliQuizQuestion(
    val id: String,
    val question: String,
    val promptAudioText: String,
    val options: List<String>,
    val correctIndex: Int,
    val hint: String,
    val illustration: String
)

object BengaliData {

    val vowels = listOf(
        BengaliLetter("অ", "অলি", "Honeybee", "🐝", "Oli - Honeybee"),
        BengaliLetter("আ", "আম", "Mango", "🥭", "Aam - Mango"),
        BengaliLetter("ই", "ইলিশ", "Hilsa Fish", "🐟", "Ilish - Fish"),
        BengaliLetter("ঈ", "ঈগল", "Eagle", "🦅", "Eegol - Eagle"),
        BengaliLetter("উ", "উট", "Camel", "🐫", "Ut - Camel"),
        BengaliLetter("ঊ", "ঊষা", "Dawn / Sunrise", "🌅", "Usha - Sunrise"),
        BengaliLetter("ঋ", "ঋষি", "Sage", "🧘", "Rishi - Sage"),
        BengaliLetter("এ", "একতারা", "Ektara Instrument", "🪕", "Ektara"),
        BengaliLetter("ঐ", "ঐরাবত", "Elephant", "🐘", "Oirabot - Elephant"),
        BengaliLetter("ও", "ওলকপি", "Kohlrabi", "🥬", "Olkopi"),
        BengaliLetter("ঔ", "ঔষধ", "Medicine", "💊", "Oushodh - Medicine")
    )

    val consonants = listOf(
        BengaliLetter("ক", "কলা", "Banana", "🍌", "Kola - Banana", false),
        BengaliLetter("খ", "খরগোশ", "Rabbit", "🐰", "Khorgosh - Rabbit", false),
        BengaliLetter("গ", "গরু", "Cow", "🐄", "Goru - Cow", false),
        BengaliLetter("ঘ", "ঘোড়া", "Horse", "🐎", "Ghora - Horse", false),
        BengaliLetter("ঙ", "ব্যাঙ", "Frog", "🐸", "Byang - Frog", false),
        BengaliLetter("চ", "চাঁদ", "Moon", "🌙", "Chand - Moon", false),
        BengaliLetter("ছ", "ছাতা", "Umbrella", "☂️", "Chhata - Umbrella", false),
        BengaliLetter("জ", "জাহাজ", "Ship", "🚢", "Jahaj - Ship", false),
        BengaliLetter("ঝ", "ঝুড়ি", "Basket", "🧺", "Jhuri - Basket", false),
        BengaliLetter("ঞ", "মিঞা", "Miah Cat", "🐱", "Miah", false),
        BengaliLetter("ট", "টমেটো", "Tomato", "🍅", "Tomato", false),
        BengaliLetter("ঠ", "ঠাকুমা", "Grandma", "👵", "Thakuma - Grandma", false),
        BengaliLetter("ড", "ডালিম", "Pomegranate", "🍎", "Dalim", false),
        BengaliLetter("ঢ", "ঢোল", "Drum", "🥁", "Dhol - Drum", false),
        BengaliLetter("ণ", "হরিণ", "Deer", "🦌", "Horin - Deer", false),
        BengaliLetter("ত", "তরমুজ", "Watermelon", "🍉", "Tormuj - Watermelon", false),
        BengaliLetter("থ", "থালা", "Plate", "🍽️", "Thala - Plate", false),
        BengaliLetter("দ", "দোয়েল", "Magpie Robin", "🐦", "Doyel - National Bird", false),
        BengaliLetter("ধ", "ধান", "Paddy", "🌾", "Dhan - Paddy", false),
        BengaliLetter("ন", "নৌকা", "Boat", "⛵", "Nouka - Boat", false),
        BengaliLetter("প", "পাখি", "Bird", "🦜", "Pakhi - Bird", false),
        BengaliLetter("ফ", "ফুল", "Flower", "🌸", "Phul - Flower", false),
        BengaliLetter("ব", "বই", "Book", "📚", "Boi - Book", false),
        BengaliLetter("ভ", "ভালুক", "Bear", "🐻", "Bhaluk - Bear", false),
        BengaliLetter("ম", "মাছ", "Fish", "🐠", "Machh - Fish", false),
        BengaliLetter("য", "যানবাহন", "Vehicles", "🚗", "Janbahon", false),
        BengaliLetter("র", "রংধনু", "Rainbow", "🌈", "Rongdhonu - Rainbow", false),
        BengaliLetter("ল", "লিচু", "Lychee", "🍒", "Lichu - Lychee", false),
        BengaliLetter("শ", "শাপলা", "Water Lily", "🪷", "Shapla - Water Lily", false),
        BengaliLetter("ষ", "ষাঁড়", "Bull", "🐂", "Shanr - Bull", false),
        BengaliLetter("স", "সিংহ", "Lion", "🦁", "Singho - Lion", false),
        BengaliLetter("হ", "হাতি", "Elephant", "🐘", "Hati - Elephant", false),
        BengaliLetter("ড়", "ঘড়ি", "Clock", "⏰", "Ghori - Clock", false),
        BengaliLetter("ঢ়", "আষাঢ়", "Monsoon", "🌧️", "Asharh - Monsoon", false),
        BengaliLetter("য়", "ময়না", "Myna Bird", "🐥", "Moyna", false),
        BengaliLetter("ৎ", "উৎসব", "Festival", "🎉", "Utsob - Festival", false),
        BengaliLetter("ং", "হংস", "Swan", "🦆", "Hongsho - Swan", false),
        BengaliLetter("ঃ", "দুঃখী", "Kind Heart", "💖", "Dukkhi", false),
        BengaliLetter("ঁ", "চাঁদ", "Crescent", "✨", "Chandrabindu", false)
    )

    val vocabularyList = listOf(
        BengaliVocabulary("আম", "Mango", "ফল (Fruits)", "🥭", "মিষ্টি পাকা আম খেতে ভারি মিষ্টি।"),
        BengaliVocabulary("কলা", "Banana", "ফল (Fruits)", "🍌", "কলা খেলে শরীরে অনেক শক্তি বাড়ে।"),
        BengaliVocabulary("আপেল", "Apple", "ফল (Fruits)", "🍎", "রোজ একটি আপেল খেলে রোগ পালায়।"),
        BengaliVocabulary("লিচু", "Lychee", "ফল (Fruits)", "🍒", "রসাল লাল লিচু দেখতে বড় সুন্দর।"),
        BengaliVocabulary("বাঘ", "Tiger", "পশু (Animals)", "🐯", "রয়েল বেঙ্গল টাইগার সুন্দরবনের রাজা।"),
        BengaliVocabulary("হাতি", "Elephant", "পশু (Animals)", "🐘", "হাতি বিশাল বড় কিন্তু খুব শান্ত প্রাণী।"),
        BengaliVocabulary("বিড়াল", "Cat", "পশু (Animals)", "🐱", "ছোট্ট বিড়াল মিউ মিউ করে ডাকে।"),
        BengaliVocabulary("দোয়েল", "Magpie", "পাখি (Birds)", "🐦", "দোয়েল আমাদের মিষ্টি গানের পাখি।"),
        BengaliVocabulary("টিয়া", "Parrot", "পাখি (Birds)", "🦜", "সবুজ টিয়া লাল ঠোঁট দিয়ে কথা বলে।"),
        BengaliVocabulary("শাপলা", "Water Lily", "ফুল (Flowers)", "🪷", "শাপলা আমাদের জাতীয় ফুল, জলে ফোটে।"),
        BengaliVocabulary("গোলাপ", "Rose", "ফুল (Flowers)", "🌹", "গোলাপ ফুলের সুবাস কত চমৎকার!"),
        BengaliVocabulary("সূর্যমুখী", "Sunflower", "ফুল (Flowers)", "🌻", "সূর্যমুখী সূর্যের দিকে মুখ করে হাসে।"),
        BengaliVocabulary("লাল", "Red", "রং (Colors)", "🔴", "টকটকে লাল পাকা টমেটো।"),
        BengaliVocabulary("সবুজ", "Green", "রং (Colors)", "🟢", "গাছের পাতা আর ঘাসের রং সবুজ।"),
        BengaliVocabulary("নীল", "Blue", "রং (Colors)", "🔵", "খোলা আকাশের রং সুন্দর নীল।"),
        BengaliVocabulary("হলুদ", "Yellow", "রং (Colors)", "🟡", "পাকা কলা ও সূর্যের আলো হলুদ।"),
        BengaliVocabulary("চোখ", "Eye", "শরীর (Body)", "👀", "চোখ দিয়ে আমরা সুন্দর পৃথিবী দেখি।"),
        BengaliVocabulary("হাত", "Hand", "শরীর (Body)", "✋", "হাত দিয়ে আমরা ছবি আঁকি ও লিখি।"),
        BengaliVocabulary("কান", "Ear", "শরীর (Body)", "👂", "কান দিয়ে আমরা মিষ্টি গান ও কথা শুনি।"),
        BengaliVocabulary("মা", "Mother", "পরিবার (Family)", "👩‍👧", "মা আমাদের সবচেয়ে বেশি ভালোবাসেন।"),
        BengaliVocabulary("বাবা", "Father", "পরিবার (Family)", "👨‍👦", "বাবা আমাদের সবসময় আগলে রাখেন।")
    )

    val numbersBn = listOf(
        Triple("১", "এক (One)", "⭐"),
        Triple("২", "দুই (Two)", "⭐⭐"),
        Triple("৩", "তিন (Three)", "⭐⭐⭐"),
        Triple("৪", "চার (Four)", "🍎🍎🍎🍎"),
        Triple("৫", "পাঁচ (Five)", "🍎🍎🍎🍎🍎"),
        Triple("৬", "ছয় (Six)", "🎈🎈🎈🎈🎈🎈"),
        Triple("৭", "সাত (Seven)", "🎈🎈🎈🎈🎈🎈🎈"),
        Triple("৮", "আট (Eight)", "🌸🌸🌸🌸🌸🌸🌸🌸"),
        Triple("৯", "নয় (Nine)", "🌸🌸🌸🌸🌸🌸🌸🌸🌸"),
        Triple("১০", "দশ (Ten)", "🐠🐠🐠🐠🐠🐠🐠🐠🐠🐠")
    )

    val quizQuestions = listOf(
        BengaliQuizQuestion(
            id = "bn_q1",
            question = "কোন বর্ণটি দিয়ে 'আম' শব্দটি শুরু হয়?",
            promptAudioText = "কোন বর্ণটি দিয়ে আম শব্দটি শুরু হয়?",
            options = listOf("অ", "আ", "ই", "উ"),
            correctIndex = 1,
            hint = "আ-তে আম, মিষ্টি পাকা আম!",
            illustration = "🥭"
        ),
        BengaliQuizQuestion(
            id = "bn_q2",
            question = "নিচের কোন ছবিতে 'হাতি' রয়েছে?",
            promptAudioText = "নিচের কোন ছবিতে হাতি রয়েছে?",
            options = listOf("🐱 বিড়াল", "🐘 হাতি", "🐶 কুকুর", "🐟 মাছ"),
            correctIndex = 1,
            hint = "শুঁড়ওয়ালা বিশাল প্রাণী!",
            illustration = "🐘"
        ),
        BengaliQuizQuestion(
            id = "bn_q3",
            question = "ক-তে কোন ফলের নাম হয়?",
            promptAudioText = "ক তে কোন ফলের নাম হয়?",
            options = listOf("কলা", "লিচু", "আপেল", "আম"),
            correctIndex = 0,
            hint = "ক-তে কলা, খোসা ছাড়িয়ে খেতে হয়!",
            illustration = "🍌"
        ),
        BengaliQuizQuestion(
            id = "bn_q4",
            question = "আমাদের জাতীয় ফুল কোনটি?",
            promptAudioText = "আমাদের জাতীয় ফুল কোনটি?",
            options = listOf("গোলাপ", "সূর্যমুখী", "শাপলা", "জবা"),
            correctIndex = 2,
            hint = "জলে ফোটে সাদা আর লাল পাপড়ির ফুল!",
            illustration = "🪷"
        ),
        BengaliQuizQuestion(
            id = "bn_q5",
            question = "বাংলা সংখ্যা '৩'-এর পরের সংখ্যা কোনটি?",
            promptAudioText = "বাংলা সংখ্যা তিন এর পরের সংখ্যা কোনটি?",
            options = listOf("১", "২", "৪", "৫"),
            correctIndex = 2,
            hint = "এক, দুই, তিন, এর পর আসে...",
            illustration = "🔢"
        )
    )
}
