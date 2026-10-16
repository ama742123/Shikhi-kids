package com.example.data

data class MathNumber(
    val numeralBn: String,
    val numeralEn: String,
    val nameBn: String,
    val nameEn: String,
    val countItems: String
)

data class MathShape(
    val nameBn: String,
    val nameEn: String,
    val symbol: String,
    val descriptionBn: String,
    val sidesCount: Int
)

data class MathEquation(
    val id: String,
    val promptBn: String,
    val promptEn: String,
    val visualItemsA: String,
    val visualItemsB: String,
    val operator: String, // "+" or "-"
    val numA: Int,
    val numB: Int,
    val answer: Int,
    val options: List<Int>
)

object MathData {

    val numbersList = listOf(
        MathNumber("১", "1", "এক", "One", "🍎"),
        MathNumber("২", "2", "দুই", "Two", "🍎🍎"),
        MathNumber("৩", "3", "তিন", "Three", "⭐⭐⭐"),
        MathNumber("৪", "4", "চার", "Four", "🚗🚗🚗🚗"),
        MathNumber("৫", "5", "পাঁচ", "Five", "🎈🎈🎈🎈🎈"),
        MathNumber("৬", "6", "ছয়", "Six", "🐱🐱🐱🐱🐱🐱"),
        MathNumber("৭", "7", "সাত", "Seven", "🌸🌸🌸🌸🌸🌸🌸"),
        MathNumber("৮", "8", "আট", "Eight", "🍓🍓🍓🍓🍓🍓🍓🍓"),
        MathNumber("৯", "9", "নয়", "Nine", "🦋🦋🦋🦋🦋🦋🦋🦋🦋"),
        MathNumber("১০", "10", "দশ", "Ten", "🐠🐠🐠🐠🐠🐠🐠🐠🐠🐠"),
        MathNumber("১১", "11", "এগারো", "Eleven", "11 items"),
        MathNumber("১২", "12", "বারো", "Twelve", "12 items"),
        MathNumber("১৩", "13", "তেরো", "Thirteen", "13 items"),
        MathNumber("১৪", "14", "চৌদ্দ", "Fourteen", "14 items"),
        MathNumber("১৫", "15", "পনেরো", "Fifteen", "15 items"),
        MathNumber("১৬", "16", "ষোলো", "Sixteen", "16 items"),
        MathNumber("১৭", "17", "সতেরো", "Seventeen", "17 items"),
        MathNumber("১৮", "18", "আঠারো", "Eighteen", "18 items"),
        MathNumber("১৯", "19", "উনিশ", "Nineteen", "19 items"),
        MathNumber("২০", "20", "বিশ", "Twenty", "20 items")
    )

    val shapes = listOf(
        MathShape("বৃত্ত", "Circle", "⭕", "কোনো কোণা নেই, চাঁদের মতো গোল", 0),
        MathShape("বর্গ", "Square", "⏹️", "চারটি সমান বাহু এবং চারটি কোণা", 4),
        MathShape("ত্রিভুজ", "Triangle", "🔺", "তিনটি বাহু এবং তিনটি কোণা", 3),
        MathShape("তারা", "Star", "⭐", "আকাশের উজ্জ্বল তারার মতো পাঁচ মাথা", 5),
        MathShape("হৃদয়", "Heart", "❤️", "ভালোবাসার প্রতীক নরম সুন্দর আকৃতি", 0),
        MathShape("আয়তক্ষেত্র", "Rectangle", "▭", "বিপরীত বাহু সমান, বইয়ের মতো", 4)
    )

    val equations = listOf(
        MathEquation(
            id = "eq_1",
            promptBn = "২টি আপেলের সাথে আরো ১টি আপেল যোগ করলে কত হয়?",
            promptEn = "What is 2 apples plus 1 apple?",
            visualItemsA = "🍎🍎",
            visualItemsB = "🍎",
            operator = "+",
            numA = 2,
            numB = 1,
            answer = 3,
            options = listOf(2, 3, 4)
        ),
        MathEquation(
            id = "eq_2",
            promptBn = "৩টি তারা এবং আরো ২টি তারা মিলে মোট কয়টি হয়?",
            promptEn = "What is 3 stars plus 2 stars?",
            visualItemsA = "⭐⭐⭐",
            visualItemsB = "⭐⭐",
            operator = "+",
            numA = 3,
            numB = 2,
            answer = 5,
            options = listOf(4, 5, 6)
        ),
        MathEquation(
            id = "eq_3",
            promptBn = "৪টি বেলুন থেকে ১টি বেলুন উড়ে গেলে কয়টি থাকে?",
            promptEn = "4 balloons take away 1 balloon?",
            visualItemsA = "🎈🎈🎈🎈",
            visualItemsB = "🎈",
            operator = "-",
            numA = 4,
            numB = 1,
            answer = 3,
            options = listOf(2, 3, 4)
        ),
        MathEquation(
            id = "eq_4",
            promptBn = "৫টি স্ট্রবেরি থেকে ২টি স্ট্রবেরি খেলে কয়টি থাকে?",
            promptEn = "5 strawberries take away 2?",
            visualItemsA = "🍓🍓🍓🍓🍓",
            visualItemsB = "🍓🍓",
            operator = "-",
            numA = 5,
            numB = 2,
            answer = 3,
            options = listOf(3, 4, 2)
        )
    )
}
