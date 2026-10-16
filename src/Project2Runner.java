public class Project2Runner {

    /*
     ******** Project Description ********
     *
     * For this second project I decided on a Maze Explorer. It is a simple maze game
     * in which the user controls a little blue player icon, using the arrow keys on
     * the keyboard, and tries to navigate a randomly generated maze. The objective is
     * to reach the green exit square. The program also keeps track of the number of
     * moves and shows status messages at the bottom of the window.
     *
     * The program satisfies the project requirements in several ways. It uses Swing
     * components to build the interface and a JPanel to draw the maze, player, and goal
     * using 2D shapes. It also includes event listeners so the user can interact with
     * the program. Action listeners are used to display instructions, reset the player,
     * and generate a new maze. A keyboard listener is also used so the arrow keys can
     * move the player through the maze using the arrow keys. Each time the player moves
     * or the buttons are pressed, the JPanel updates to show the changes.
     *
     * To use the program, run the main method below. Instructions for the game are as follows:
     * 1. Press the arrow keys to move.
     * 2. Click the New Maze button to generate another maze.
     * 3. Click Reset Player to go back to the starting point.
     * 4. Click Instructions to display a short help message.
     *
     * The goal is to reach the green square.
     *
     ******** Swing Requirement ********
     *
     * This program uses a few meaningful Swing components. In
     * MazeExplorer.java, a JFrame is created by making MazeExplorer extend JFrame
     * near the top of the file. It also creates three JButtons called New Maze,
     * Reset Player, and Instructions starting around lines 11-14 and is initialized
     * in the constructor around lines 29-33. I have also used two JLabels,
     * statusLabel and movesLabel, which are declared near the top of the class and
     * initialized in the constructor around lines 34-42. These components let the
     * user control the game or display game information which are critical to the
     * functionality of the program.
     *
     ******** 2D Graphics Requirement ********
     *
     * Only one JPanel is used for drawing, which is the MazePanel inner class
     * in MazeExplorer.java starting around line 136, this panel is added to the
     * JFrame in the MazeExplorer constructor around line 43. The MazePanel class
     * overrides paintComponent() near the end of the file and uses Graphics2D to
     * draw the maze. The maze is drawn using black rectangles for walls, a green
     * rectangle for the exit, white rectangles for open paths, and a blue oval for
     * the player. This panel acts as the main visual part of the game.
     *
     ******** Event Listener Requirement ********
     *
     * The game uses both an ActionListener and a KeyListener. ActionListener and
     * KeyListener are implemented by the MazeExplorer class near the top of
     * MazeExplorer.java. The actionPerformed() method is defined around lines
     * 58-80 which responds to the New Maze, Reset Player, and Instructions buttons.
     * When these buttons are clicked, it causes meaningful changes to the JPanel
     * because they can generate a new maze, move the player back to the start, or
     * update the game message. The keyPressed() method is defined around lines 103-124
     * and uses the arrow keys to move the player through the maze. Each valid key press
     * changes the player's position and repaints the JPanel, so it also causes a
     * meaningful change to the dynamic graphics of the game.
     */

    public static void main(String[] args) {
        new MazeExplorer();
    }
}

