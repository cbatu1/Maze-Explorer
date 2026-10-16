
# Maze Explorer 🧩

## About the Project

Maze Explorer is a simple Java-based maze game where the player navigates through a randomly generated maze using the arrow keys on their keyboard.

The objective of the game is to guide a **blue player icon through the maze to reach the green exit square**, while avoiding walls.

The program features an interactive graphical user interface (GUI), tracks the number of moves made by the player, and displays status messages throughout the game.

## How It Works

The program uses Java Swing to create an interactive game window and Java 2D Graphics to display the maze, player, and exit.

When the game starts, a random 21 × 21 maze is generated using a recursive backtracking algorithm. The maze is represented by a two-dimensional integer array, with different values representing walls, open paths, and the exit.

For each movement, the program:

1. Detects which arrow key the player presses.
2. Determines the direction the player wants to move.
3. Checks whether the movement is valid and does not pass through a wall.
4. Updates the player's position if the movement is valid.
5. Increases the move counter after each successful movement.
6. Repaints the game panel to display the player's new position.
7. Checks whether the player has reached the green exit square.

When the player reaches the exit, a message displays the total number of moves taken to complete the maze.

## Features

The game includes an interactive interface that allows the user to:

- Navigate the maze using the keyboard arrow keys
- Generate a new randomly created maze
- Reset the player to the starting position
- View instructions on how to play
- Track the number of successful moves
- Receive status messages throughout the game
- See a winning message after reaching the exit

## Game Interface

The maze is displayed using Java 2D Graphics, with different shapes and colours representing the game elements:

- **Black rectangles** - Walls that the player cannot move through
- **White rectangles** - Open paths that the player can navigate
- **Blue oval** - The player icon
- **Green rectangle** - The exit square the player must reach
- **Light grey lines** - Grid lines separating the maze cells

The interface also contains three interactive buttons:

- **New Maze** - Generates a new maze and resets the move counter
- **Reset Player** - Returns the player to the starting position and resets the move counter
- **Instructions** - Displays a short help message explaining how to play

The game window also includes labels displaying the player's move count and relevant status messages.

## Data Structures Used

The program uses several data structures and variables to organize the maze and manage the game's functionality.

- **Two-Dimensional Integer Array (`int[][]`)** - Stores the maze as a 21 × 21 grid. Each cell contains a value representing a wall (`1`), an open path (`0`), or the exit (`2`).
- **One-Dimensional Integer Array (`int[]`)** - Stores the four possible directions used during maze generation. The directions are shuffled to create different maze layouts.
- **Primitive Integer Variables (`int`)** - Track the player's row and column, the starting and goal positions, the maze dimensions, and the number of moves made.
- **Boolean Variables (`boolean`)** - Track whether the player has won and whether a movement was successful.
- **Object References** - Store and connect Swing components, the random number generator, and the main game window.

The two-dimensional array is the primary data structure, allowing the program to efficiently identify walls, open paths, and the exit when generating and navigating the maze.

## Maze Generation Algorithm

The program uses a **recursive backtracking algorithm** to generate random mazes.

The algorithm works by:

1. Creating a two-dimensional array and filling every cell with walls.
2. Starting at position `(1, 1)` in the maze.
3. Creating an array containing the four possible movement directions.
4. Randomly shuffling the directions to produce different maze layouts.
5. Checking whether moving two cells in a direction leads to an unvisited location.
6. Removing the wall between the current cell and the new location.
7. Recursively repeating the process from the new location.
8. Continuing until all reachable unvisited cells have been explored.

Once the maze has been generated, the program establishes the player's starting position and places the green exit near the opposite corner.

This approach creates a connected maze with paths that the player can navigate.

## Project Files

- `MazeExplorer.java` - Contains the main game class, Swing interface, maze generation algorithm, 2D graphics, player movement, and event listeners.
- `Project2Runner.java` - Contains the `main()` method used to launch the Maze Explorer game, along with the original project description and requirements.
- `README.md` - Project documentation.

## Running the Program

1. Make sure the Java Development Kit (JDK) is installed on your computer.
2. Place `MazeExplorer.java` and `Project2Runner.java` in the same project directory.
3. Open the files in a Java-compatible IDE or development environment.
4. Run the `main()` method in `Project2Runner.java`.
5. Use the keyboard arrow keys to navigate through the maze.
6. Avoid the black walls and work your way toward the green exit square.
7. Use the New Maze, Reset Player, or Instructions buttons whenever needed.

The game displays a winning message when the player successfully reaches the green exit square.

## Purpose

The purpose of this project is to demonstrate how Java can be used to create an interactive graphical application through the combination of **Swing components, 2D graphics, data structures, algorithms, and event listeners**.

By incorporating a two-dimensional array, recursive backtracking, randomly generated mazes, keyboard-controlled movement, and interactive buttons, the project demonstrates how different programming concepts can work together to create a functional game.

Maze Explorer provides practical experience with object-oriented programming, GUI development, recursion, array manipulation, and event-driven programming in Java.
