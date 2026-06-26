# DailyOS--Today-Version

A lightweight Android application that helps you focus on **today**.

Unlike traditional habit trackers that emphasize streaks, history, and long-term analytics, DailyOS is intentionally designed around a single principle:

> Every day is a fresh start.

The application resets at the start of a new day while preserving your personal preferences. Its purpose is to provide a simple daily operating system rather than a productivity platform.

---

## Features

* Daily requirements tracking
* Custom daily goals
* Quick activity logging
* Activity timeline
* Configurable daily targets
* Persistent local storage
* Automatic new-day reset
* Delete confirmations
* Lightweight architecture
* Offline-first operation

---

## Philosophy

DailyOS intentionally avoids unnecessary complexity.

It does **not** include:

* Streaks
* Historical statistics
* Cloud synchronization
* Accounts
* AI features
* Notifications
* Achievement systems
* Gamification

The goal is to help you complete **today**, not optimize your entire life.

---

## Tech Stack

* Kotlin
* Jetpack Compose
* Material 3
* Android ViewModel
* Jetpack DataStore
* Kotlin Serialization

---

## Architecture

```
MainActivity
│
├── Today
│   ├── Requirements
│   ├── Goals
│   ├── Quick Log
│   └── Activity Log
│
└── Preferences
    ├── Daily Targets
    └── Today's Sleep
```

The application follows a single-activity architecture with separated ViewModels for today's state and user preferences.

---

## Project Structure

```
app/
├── data/
├── engine/
├── model/
├── navigation/
├── repository/
├── ui/
├── util/
└── viewmodel/
```

---

## Privacy

DailyOS stores all information locally on your device using Jetpack DataStore.

No user accounts, cloud synchronization, analytics, or external servers are used.

---

## License

This project is licensed under the MIT License.
