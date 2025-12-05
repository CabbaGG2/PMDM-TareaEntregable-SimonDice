# Damian Dice

Damian Dice is a simple and addictive memory game for Android, inspired by the classic game "Simon Says." The goal is to watch a sequence of colors light up and then repeat it in the correct order. Test your memory and see how many rounds you can beat!

## How to Play

1.  **Start the game.**
2.  Carefully watch the sequence of colors as they light up.
3.  Repeat the sequence by tapping the colors in the same order.
4.  If you get it right, the sequence will get one step longer.
5.  If you make a mistake, the game is over.
6.  Try to beat your high score!

## Features

*   Classic memory game gameplay.
*   Randomly generated sequences that increase in difficulty.
*   Clean and modern user interface built with Jetpack Compose.
*   Tracks the current score (rounds completed).
*   Saves and displays the all-time high score.

## Technical Details

*   **Language:** Kotlin
*   **UI:** Jetpack Compose
*   **Architecture:** Model-View-ViewModel (MVVM)
    *   `MyViewModel` contains all the game logic and manages the state.
    *   Uses `LiveData` and `mutableStateOf` to observe state changes and reactively update the UI.
*   **Data Persistence:** `SharedPreferences` is used to store the high score.

## Main Dependencies

*   Jetpack Compose
*   AndroidX Lifecycle (ViewModel, LiveData)
*   JUnit and Mockito for unit testing.

## How to Build and Run

1.  Clone this repository.
2.  Open the project in Android Studio.
3.  Sync the project with Gradle.
4.  Run the app on an Android emulator or a physical device.
