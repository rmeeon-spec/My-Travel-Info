# My Travel Info — Android starter project

এটি My Travel Info অ্যাপের প্রথম UI prototype এবং Android Studio project scaffold।

## বর্তমানে যা আছে
- Premium forest-green + cream UI
- Home screen, search, destination cards
- Destination details screen
- নতুন জায়গা যোগ করার form
- Bottom navigation: Home / Explore / Favorites / Profile
- নমুনা হিসেবে কয়েকটি বাংলাদেশের গন্তব্য

## গুরুত্বপূর্ণ সীমাবদ্ধতা
এই প্রথম prototype-এ নতুন জায়গা যোগ করলে তা কেবল অ্যাপের চলতি runtime state-এ থাকে। এটি এখনো স্থায়ীভাবে সংরক্ষিত হয় না। Firebase Authentication, Firestore, Storage, ownership-based editing এবং বাস্তব Google Maps integration পরবর্তী ধাপে সংযুক্ত করতে হবে। তাই এখনই এটিকে production বা multi-user release হিসেবে প্রকাশ করা যাবে না।

## Android Studio-তে চালানো
1. Android Studio-তে এই project folder খুলুন।
2. Gradle sync সম্পন্ন হতে দিন (ইন্টারনেট লাগতে পারে)।
3. Firebase ব্যবহার শুরু করার আগে Firebase Console-এ Android app তৈরি করুন:
   - package name: `com.mytravelinfo.app`
   - Authentication provider (Email/Password) চালু করুন।
   - Cloud Firestore এবং Storage তৈরি করুন।
   - `google-services.json` ফাইলটি `app/` ফোল্ডারে রাখুন।
4. Run চাপ দিয়ে emulator বা Android ফোনে পরীক্ষা করুন.

## পরবর্তী development checklist
- Firebase email/password registration and login
- Firestore destination collection
- `createdBy` user ID দিয়ে edit/delete permission
- Firebase Storage photo uploads
- Favorites per user
- Google Maps deep links / Maps SDK
- Report content and admin moderation
- Offline cache and validation
- Build, test and signed APK

## Suggested Firestore document
`destinations/{destinationId}`:
- `name`: string
- `region`: string
- `description`: string
- `route`: string
- `latitude`: number (optional)
- `longitude`: number (optional)
- `photoUrls`: array of strings
- `createdBy`: Firebase UID
- `createdAt`: timestamp
- `updatedAt`: timestamp

Security rules must ensure that signed-in users may create destinations, and only the owner or an authorized moderator may update/delete them. Do not deploy open write rules.

## ছবি যোগ করার UI
- Android Photo Picker দিয়ে গ্যালারি থেকে সর্বোচ্চ ১০টি ছবি নির্বাচন করা যায়।
- FileProvider ও Android camera capture দিয়ে ছবি তোলা যায়।
- নির্বাচিত ছবির preview দেখানো হয়।
- Firebase Storage upload এখনো যুক্ত হয়নি; ছবি URI কেবল prototype runtime-এ থাকে।
