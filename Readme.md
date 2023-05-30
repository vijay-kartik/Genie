# Genie - Offline Password Manager

![Genie App Icon](/Users/kartik/Genie/app/src/main/ic_app_icon-playstore.png)

Genie is a secure and user-friendly Android application that allows users to store and manage their passwords locally on their device. The app is built with Jetpack Compose and uses the Android Keystore System for secure encryption of user data.

## Features

- **Secure Storage**: All passwords are stored securely on the device using AES encryption. The master key is securely stored using the Android Keystore system, ensuring that your data is safe even if your device is compromised.

- **Biometric Authentication**: Users have the option to enable biometric authentication (fingerprint scanning) for an added layer of security.

- **Offline Access**: Since all data is stored locally, users can access their passwords anytime, anywhere, without needing an internet connection.

- **User-friendly Interface**: The app features a clean and intuitive interface built with Jetpack Compose, making password management a breeze.

## Technology Stack

- **Jetpack Compose**: The modern toolkit for building native Android UI. Jetpack Compose simplifies and accelerates UI development on Android.

- **Android Keystore System**: A secure container for storing cryptographic keys in a context that makes them more difficult to extract.

- **Encrypted Shared Preferences**: Part of Android's Jetpack Security library, Encrypted Shared Preferences provide a secure way to read and write encrypted key-value pairs.

- **Biometric Authentication**: The Biometric library is a part of the AndroidX Biometric library which provides a standardized dialog to prompt users for their biometric credentials (fingerprint).

## Security

Security is a top priority in Genie. The app uses the Android Keystore System to securely store the master key, which is used to encrypt and decrypt the passwords. The Android Keystore System makes it more difficult to extract the keys, ensuring that the keys are not accessible when the device is locked.

In addition, the app provides an option for users to enable biometric authentication, adding an extra layer of security. The biometric data is managed by the device's operating system and is not accessible by the app or any other apps, ensuring the privacy and security of the user's biometric data.

## Getting Started

To get started with Genie, users are guided through an onboarding process where they set up their master key and optional biometric authentication. Once the setup is complete, users can start adding, viewing, and managing their passwords.
