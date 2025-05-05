# Kotlin Quiz & OrgMe To-Do List App

This repository contains two Android applications originally designed and developed by **Sheila Demonteverde**, using **Kotlin** and **Android Studio**.

---

## KotlinQuizApp

A simple Android quiz app showcasing Kotlin fundamentals and UI interactions using Fragments and a Floating Action Button (FAB).

### Features:
- Fragment-based navigation
- Material Design UI elements (Toolbar, FAB, Snackbar)
- Action bar menu
- Organized folder structure: `layout`, `drawable`, `menu`, `navigation`, etc.

### Main Files:
- `MainActivity.kt`
- `FirstFragment.kt`
- `SecondFragment.kt`

---

## OrgMe - Android To-Do List App

**OrgMe** is a full-featured To-Do List Android app designed to help users manage tasks efficiently with a clean interface and smooth navigation.

### Features:
- Add, edit, and delete tasks
- RecyclerView with custom adapters and view holders
- Fragment navigation using `NavController`
- Splash screen
- Modular Kotlin structure for maintainability

### Key Files:
- `MainActivity.kt`
- `AddNewTaskToList.kt`
- `EditTaskActivity.kt`
- `TaskListAdapter.kt`
- `TodoListFragment.kt`
- `Utils.kt`

---

## How to Build

### Prerequisites:
- Android Studio 3.6+
- Gradle 5+
- Kotlin Plugin 1.3.72+

### Instructions:
```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

Project Structure
KotlinQuizApp/
 ┣ app/
 ┣ MainActivity.kt
 ┣ FirstFragment.kt
 ┣ SecondFragment.kt
 ┣ res/...

OrgMe/
 ┣ raw/
 ┣ MainActivity.kt
 ┣ TaskListAdapter.kt
 ┣ TaskDetailFragment.kt
 ┣ TodoListFragment.kt
 ┗ Utils.kt

Author
Sheila Demonteverde @ 2020
Designed, built, and maintained this project independently
