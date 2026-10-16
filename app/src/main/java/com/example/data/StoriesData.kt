package com.example.data

data class StoryPage(
    val pageNumber: Int,
    val textBn: String,
    val textEn: String,
    val illustrationEmoji: String
)

data class StoryQuestion(
    val questionBn: String,
    val questionEn: String,
    val options: List<String>,
    val correctIndex: Int
)

data class Story(
    val id: String,
    val titleBn: String,
    val titleEn: String,
    val moralBn: String,
    val moralEn: String,
    val coverEmoji: String,
    val pages: List<StoryPage>,
    val question: StoryQuestion
)

object StoriesData {

    val storiesList = listOf(
        Story(
            id = "story_1",
            titleBn = "ছোট্ট পায়রা ও পিঁপড়ের বন্ধুত্ব",
            titleEn = "The Pigeon and the Kind Ant",
            moralBn = "উপকার করলে উপকার পাওয়া যায় ও বন্ধুত্ব সবসময় জয়ী হয়।",
            moralEn = "Kindness is never wasted; helping friends makes everyone happy.",
            coverEmoji = "🕊️🐜",
            pages = listOf(
                StoryPage(
                    1,
                    "এক সবুজ বনে একটি শান্ত নদীর ধারে এক ছোট্ট পিঁপড়ে জল খেতে গিয়ে জলে পড়ে গেল।",
                    "In a green forest by a peaceful river, a little ant slipped into the water.",
                    "🌊🐜"
                ),
                StoryPage(
                    2,
                    "গাছের ডালে বসে এক দয়ালু পায়রা তা দেখল। সে তাড়াতাড়ি একটি গাছের পাতা ঠোঁটে করে জলে ফেলল।",
                    "A kind pigeon sitting on a tree saw the ant. She dropped a fresh leaf into the river.",
                    "🕊️🍃"
                ),
                StoryPage(
                    3,
                    "পিঁপড়েটি পাতার ওপর চেপে নিরাপদে তীরে উঠে এল এবং পায়রাকে ধন্যবাদ জানাল।",
                    "The ant climbed onto the leaf safely to the shore and thanked the pigeon.",
                    "🐜💚🕊️"
                ),
                StoryPage(
                    4,
                    "একদিন এক শিকারী পায়রাকে তীর মারতে এল। পিঁপড়েটি দৌড়ে এসে শিকারীর পায়ে কামড় দিল!",
                    "One day, a hunter aimed at the pigeon. The little ant ran and bit the hunter's foot!",
                    "🏹🐜🦶"
                ),
                StoryPage(
                    5,
                    "তীর লক্ষ্যভ্রষ্ট হলো আর পায়রাটি উড়ে পালাল। এভাবেই তাদের বন্ধুত্ব চিরদিনের জন্য অমর হলো।",
                    "The arrow missed, and the pigeon flew away safely. Their friendship lasted forever.",
                    "🕊️✨🐜"
                )
            ),
            question = StoryQuestion(
                "পায়রা পিঁপড়েকে বাঁচাতে জলে কী ফেলেছিল?",
                "What did the pigeon drop to save the ant?",
                listOf("পাথর", "গাছের পাতা", "ফল", "কাঠি"),
                1
            )
        ),
        Story(
            id = "story_2",
            titleBn = "বুদ্ধিমান ছোট্ট খরগোশ",
            titleEn = "The Clever Little Bunny",
            moralBn = "রাগ না করে বুদ্ধি দিয়ে বড় সমস্যাও সহজ করা যায়।",
            moralEn = "Wisdom and patience can solve any big problem.",
            coverEmoji = "🐰🥕",
            pages = listOf(
                StoryPage(
                    1,
                    "একটি রঙিন বাগানে মিঠু নামের এক মিষ্টি খরগোশ বাস করত। সে রোজ গাজর ও তাজা ঘাস খেত।",
                    "In a colorful garden lived a sweet little bunny named Mithu who loved carrots.",
                    "🏡🐰🥕"
                ),
                StoryPage(
                    2,
                    "এক গ্রীষ্মের দুপুরে বাগানের জল শেষ হয়ে গেল। সব পশুরা খুব চিন্তিত হয়ে পড়ল।",
                    "On a hot summer noon, the garden water dried up. All the animals grew worried.",
                    "☀️🏜️🐿️"
                ),
                StoryPage(
                    3,
                    "মিঠু বলল, 'চলো আমরা সবাই মিলে বড় গাছের তলায় নরম মাটি খুঁড়ি!'",
                    "Mithu said, 'Let us all dig the soft earth under the big banyan tree together!'",
                    "🌳🐾🐰"
                ),
                StoryPage(
                    4,
                    "সবাই মিলে একসঙ্গে মাটি খুঁড়তেই ভেতর থেকে মিষ্টি ঠান্ডা জল বেরিয়ে এল!",
                    "Working together, cool sweet fresh water bubbled up from beneath the soil!",
                    "💦🎉✨"
                ),
                StoryPage(
                    5,
                    "সবাই আনন্দ করল এবং মিঠুর বুদ্ধির প্রশংসা করল। একতাই আসল শক্তি!",
                    "Everyone cheered and praised Mithu's clever idea. Unity brings joy!",
                    "🐰🤝🐻💖"
                )
            ),
            question = StoryQuestion(
                "মিঠু খরগোশ খেতে সবচেয়ে কী ভালোবাসত?",
                "What did Mithu the bunny love to eat?",
                listOf("আইসক্রিম", "গাজর ও তাজা ঘাস", "মাছ", "ভাত"),
                1
            )
        ),
        Story(
            id = "story_3",
            titleBn = "টুনটুনি ও তার রঙিন গান",
            titleEn = "Tuntuni and the Rainbow Song",
            moralBn = "আনন্দ ভাগ করে নিলে সবার জীবন রঙিন হয়ে ওঠে।",
            moralEn = "Sharing happiness fills the world with colors.",
            coverEmoji = "🐥🌈",
            pages = listOf(
                StoryPage(
                    1,
                    "একটি ছোট্ট টুনটুনি পাখি সকালবেলা মিষ্টি সুরে গান গাইত।",
                    "A cheerful little tailorbird sang melodious songs every morning.",
                    "🌅🐥🎶"
                ),
                StoryPage(
                    2,
                    "তার গান শুনে মন খারাপ করা মেঘগুলো হেসে ফেলত এবং আকাশে রংধনু উঠত।",
                    "Hearing her happy tune, sad grey clouds smiled and a bright rainbow appeared.",
                    "☁️➡️🌈"
                ),
                StoryPage(
                    3,
                    "টুনটুনি সবাইকে শেখাল: হাসিখুশি থাকা এবং বন্ধুদের সাহায্য করাই সবচেয়ে বড় জাদু।",
                    "Tuntuni taught everyone that smiling and spreading joy is the greatest magic.",
                    "💖🌸🐥"
                )
            ),
            question = StoryQuestion(
                "টুনটুনির গান শুনে আকাশে কী উঠেছিল?",
                "What appeared in the sky after Tuntuni's song?",
                listOf("বৃষ্টির ঝড়", "রংধনু", "কালো মেঘ", "ধোঁয়া"),
                1
            )
        )
    )
}
