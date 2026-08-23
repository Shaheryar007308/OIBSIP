# Ticklt — To-Do App

Ticklt is a simple and modern Android To-Do application built with **Kotlin** and **Jetpack Compose**. It lets users create, view, edit, and delete tasks, with all tasks stored locally using the Room Database also add Firebase Authentication where user can use their email to register themselve and then login using their registered email.

## Features

- Create new tasks
- View saved tasks
- Edit existing tasks
- Delete tasks
- Persistent local storage with Room Database
- Firebase Authentication
- Add and edit tasks using a Bottom Sheet
- Clean Jetpack Compose user interface
- MVVM architecture for organized and maintainable code

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Database:** Room Database
- **Asynchronous operations:** Kotlin Coroutines and Flow
- **State management:** ViewModel and StateFlow
- **Build system:** Gradle Kotlin DSL

## Architecture

The app follows the MVVM architecture pattern:

```text
Jetpack Compose UI
        ↓
     ViewModel
        ↓
    Repository
        ↓
       DAO
        ↓
 Room Database
```

- **UI:** Displays tasks and sends user actions to the ViewModel.
- **ViewModel:** Holds UI state and handles task operations.
- **Repository:** Provides a clean data layer between ViewModel and Room.
- **DAO:** Contains Room database queries.
- **Room Database:** Stores tasks locally on the device.


## Requirements

- Android Studio Ladybug or newer
- JDK 17
- Android SDK
- Minimum SDK: Update this with your `minSdk`
- Kotlin
- Gradle

## Installation

1. Clone this repository:

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/Ticklt-Todo-App.git
```

2. Open the project in Android Studio.

3. Allow Gradle sync to complete.

4. Run the app on an Android emulator or a physical Android device.

## How It Works

1. Tap the add-task button.
2. Enter task details in the Bottom Sheet.
3. Save the task.
4. The task is stored locally with Room Database.
5. Use the edit icon to update a task.
6. Use the delete icon to remove a task.

## Future Improvements

- Mark tasks as completed
- Task categories and priorities
- Due date and reminder notifications
- Dark mode
- Search and filter tasks
- Swipe to delete
- Backup and restore tasks
- Unit tests and UI tests

## Author

**Shaheryar Mukhtar**

## License

This project is created for learning and portfolio purposes.
