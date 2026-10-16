# 🌟 শিখি কিডস (Shikhi Kids) — সম্পূর্ণ শিক্ষামূলক অ্যান্ড্রয়েড অ্যাপ

> **একটি সুন্দর, নিরাপদ এবং ১০০% অফলাইন শিশু শিক্ষামূলক অ্যান্ড্রয়েড অ্যাপ্লিকেশন (বয়স: ৩–৮ বছর)**  
> **A beautiful, child-safe, 100% offline educational Android learning app for kids aged 3–8.**

---

## 📱 অ্যাপ পরিচিতি (About Shikhi Kids)

**শিখি কিডস (Shikhi Kids)** হলো ৩ থেকে ৮ বছর বয়সী শিশুদের আনন্দের সাথে বাংলা, ইংরেজি এবং প্রাথমিক গণিত শেখার একটি আকর্ষণীয় অ্যান্ড্রয়েড অ্যাপ। অ্যাপটি সম্পূর্ণ বিজ্ঞাপনমুক্ত, কোনো ইন্টারনেট সংযোগ বা লগইন ছাড়াই অফলাইনে চলে এবং শিশুদের গোপনীয়তা সুরক্ষিত রাখে।

### প্রধান বৈশিষ্ট্যসমূহ (Key Highlights):
1. **বাংলা শিখি (Bengali Learning):** স্বরবর্ণ (অ–ঔ), ব্যঞ্জনবর্ণ (ক–ঁ), বর্ণ উচ্চারণ, ফলের নাম, পশুর নাম, ফুলের নাম, রঙের নাম ও সংখ্যা।
2. **English Learning:** Alphabet A–Z (Uppercase & Lowercase), Phonics sounds, Vocabulary words, and Sight words.
3. **গণিত শিখি (Mathematics):** সংখ্যা গণনা ১–২০, ১–৫০, ১–১০০ (বাংলা ও ইংরেজি), জ্যামিতিক আকৃতি, তুলনা (বড়/ছোট, বেশি/কম) এবং ছবি দিয়ে সহজ যোগ–বিয়োগ।
4. **মজার গল্প (Bedtime & Moral Stories):** মূল সচিত্র শিশুতোষ গল্প (পায়রা ও পিঁপড়ের বন্ধুত্ব, বুদ্ধিমান খরগোশ, টুনটুনির গান) এবং সহজ কুইজ।
5. **লেখার অনুশীলন (Writing & Tracing):** টাচ স্ক্রিনে আঙুল দিয়ে বর্ণ ও সংখ্যা হাত ঘোরানোর ইন্টারঅ্যাক্টিভ ক্যানভাস এবং বিভিন্ন রঙিন পেনসিল।
6. **১৪টি শিক্ষামূলক গেম (14 Educational Games):** Letter Hunt, Alphabet Match, Picture & Word Match, Listen & Choose, Count Objects, Missing Letter, Word Builder, Memory Match এবং আরও অনেক।
7. **আমার অগ্রগতি (Rewards & Progress):** স্টার সংগ্রহ, ৭টি সুন্দর ট্রফি ও ব্যাজ, যা ডিভাইসে অফলাইনে সংরক্ষিত থাকে।
8. **সুরক্ষিত অভিভাবক বিভাগ (Protected Parent Section):** শিশুদের নিরাপত্তায় গণিত গেট দ্বারা সুরক্ষিত, যেখানে বয়স স্তর (৩–৪, ৫–৬, ৭–৮ বছর), সাউন্ড কন্ট্রোল, প্রগতি রিসেট এবং সাপোর্ট ইমেইল রয়েছে।
9. **সাপোর্ট ইমেইল:** `support.shikhi@gmail.com`

---

## 🛠️ ব্যবহৃত প্রযুক্তি (Technology Stack)

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Architecture:** Clean MVVM Architecture with StateFlow
- **Audio System:** Native Android `TextToSpeech` (Bengali & English) + Pure `AudioTrack` synthesized tones (100% offline)
- **Local Persistence:** Android SharedPreferences (zero network dependency)
- **Build System:** Gradle 9.3.1 (Kotlin DSL), Android Gradle Plugin 9.1.1
- **CI/CD:** Automated GitHub Actions Workflow (`.github/workflows/build-apk.yml`)

---

## 🚀 মোবাইল ফোন দিয়ে APK ডাউনলোড ও ইনস্টল করার সহজ উপায়

আপনি যদি কম্পিউটার ছাড়া শুধুমাত্র **অ্যান্ড্রয়েড মোবাইল ফোন** ব্যবহার করেন, তবে এই সহজ ধাপগুলো অনুসরণ করে তৈরি হওয়া APK ইনস্টল করুন:

### ধাপসমূহ (Step-by-Step Instructions):

1. **A. Open GitHub on an Android phone:** আপনার ফোনের ক্রোম (Chrome) বা যেকোনো ব্রাউজারে GitHub খুলুন এবং লগইন করুন।
2. **B. Open the Shikhi Kids repository:** আপনার Shikhi Kids রিপোজিটরিতে প্রবেশ করুন।
3. **C. Select the Actions tab:** রিপোজিটরির উপরের মেনু থেকে **Actions** ট্যাবে ট্যাপ করুন।
4. **D. Open the latest successful workflow run:** সবচেয়ে সাম্প্রতিক সফল **“Build Shikhi Kids Android APK”** রানটি খুলুন (সবুজ টিকচিহ্নযুক্ত)।
5. **E. Find the Artifacts section:** পেজের নিচের দিকে স্ক্রোল করে **Artifacts** সেকশনে যান।
6. **F. Tap the artifact to download:** **`Shikhi-Kids-Android-APK`** আর্টিক্যাফেটে ট্যাপ করুন। একটি ZIP ফাইল ডাউনলোড হবে।
7. **G. Extract the downloaded ZIP file:** আপনার ফোনের ফাইল ম্যানেজার (Files / My Files) অ্যাপ খুলে ডাউনলোড ফোল্ডারে যান এবং জিপ ফাইলটি এক্সট্র্যাক্ট (Extract / আনজিপ) করুন।
8. **H. Find app-debug.apk:** আনজিপ করা ফোল্ডারের ভেতর **`app-debug.apk`** ফাইলটি দেখতে পাবেন।
9. **I. Tap the APK and permit installation:** `app-debug.apk` ফাইলে ট্যাপ করুন। ফোন যদি "Install unknown apps" অনুমতি চায়, তবে "Allow from this source" চালু করুন।
10. **J. Install and open Shikhi Kids:** **Install** বাটনে চাপুন। ইনস্টলেশন শেষ হলে **Open** ট্যাপ করে শিখি কিডস চালু করুন!

> 💡 **নোট:** ভবিষ্যতে কোডে কোনো পরিবর্তন করে পুশ করলে গিটহাব অ্যাকশনস নিজে থেকেই নতুন APK তৈরি করবে। আপনি একই নিয়মে নতুন APK নামিয়ে আপডেট করে নিতে পারবেন।

---

## 🤖 স্বয়ংক্রিয় গিটহাব অ্যাকশনস ওয়ার্কফ্লো (GitHub Actions CI/CD)

ওয়ার্কফ্লো ফাইলটি রয়েছে:
`.github/workflows/build-apk.yml`

### এই ওয়ার্কফ্লো যা করে:
1. `main` বা `master` ব্রাঞ্চে কোনো কোড পুশ হলে নিজে থেকেই ট্রিগার হয় (অথবা Actions ট্যাব থেকে ম্যানুয়ালি `Run workflow` করা যায়)।
2. উবুন্টু ভার্চুয়াল মেশিনে JDK 17 ও Android SDK সেটআপ করে।
3. `./gradlew assembleDebug` কমান্ডের মাধ্যমে অ্যান্ড্রয়েড APK কম্পাইল করে।
4. যাচাই করে যে `app/build/outputs/apk/debug/app-debug.apk` সঠিকভাবে তৈরি হয়েছে।
5. স্বয়ংক্রিয়ভাবে `Shikhi-Kids-Android-APK` নামে আর্টিক্যাফেক্ট আপলোড করে যাতে ফোন থেকে সরাসরি ডাউনলোড করা যায়।

---

## 📦 গুগল প্লে স্টোর রিলিজ প্রস্তুতি (Google Play Release Guidelines)

আপনি যখন অ্যাপটি গুগল প্লে স্টোরে সবার জন্য প্রকাশ করতে চাইবেন:

1. **AAB (Android App Bundle) তৈরি করুন:**
   ```bash
   ./gradlew bundleRelease
   ```
   এর মাধ্যমে `app/build/outputs/bundle/release/app-release.aab` ফাইল তৈরি হবে।

2. **রিলিজ কীস্টোর (Release Keystore):**
   একটি নিরাপদ ডিজিটাল সার্টিফিকেট তৈরি করুন (`keytool -genkey -v -keystore my-upload-key.jks ...`)। কীস্টোরের পাসওয়ার্ড কখনো গিটহাবে পুশ করবেন না; গিটহাবের **Settings > Secrets and variables > Actions**-এ সিক্রেট হিসেবে রাখুন।

3. **গুগল প্লে পলিসি ও ফ্যামিলি প্রোগ্রাম (Families Policy):**
   - Shikhi Kids সম্পূর্ণ বিজ্ঞাপনমুক্ত এবং কোনো ব্যক্তিগত তথ্য সংগ্রহ করে না।
   - টার্গেট অডিয়েন্স হিসেবে ৩–৮ বছর (Ages 3–8) নির্বাচন করুন।
   - ডেটা সেফটি ফর্মে "No data collected" নির্বাচন করুন।

---

## 🛡️ শিশু সুরক্ষা ও সহায়তা (Child Safety & Support)

- **কোনো বিজ্ঞাপন নেই (Zero Ads)**
- **কোনো ট্র্যাকিং বা বিশ্লেষণ নেই (No Analytics / No Tracking)**
- **কোনো অ্যাকাউন্টের ঝামেলা নেই (No Login / 100% Offline)**
- **সরাসরি সহায়তার ঠিকানা:** [support.shikhi@gmail.com](mailto:support.shikhi@gmail.com)
- অ্যাপের অভিভাবক বিভাগ থেকে সরাসরি ইমেইল পাঠানোর ব্যবস্থা ও কপি বাটন যুক্ত রয়েছে।
