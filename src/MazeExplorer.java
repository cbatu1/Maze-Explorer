import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


 // Class that creates the main game window for the Maze Explorer program.
 // It builds the Swing interface, connects the buttons to actions,
 // and handles the basic game state such as the move counter and win message.

public class MazeExplorer extends JFrame implements ActionListener, KeyListener {
    private final MazePanel mazePanel;
    private final JButton newMazeButton;
    private final JButton resetButton;
    private final JButton instructionsButton;
    private final JLabel statusLabel;
    private final JLabel movesLabel;
    private int moves;
    private boolean gameWon;


    // Constructor sets up the frame, creates the Swing components,
    // and makes the window visible to the user.

    public MazeExplorer() {
        super("Maze Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mazePanel = new MazePanel(this);

        JPanel topPanel = new JPanel();
        newMazeButton = new JButton("New Maze");
        resetButton = new JButton("Reset Player");
        instructionsButton = new JButton("Instructions");
        statusLabel = new JLabel("Use the arrow keys to reach the green exit.");
        movesLabel = new JLabel("Moves: 0");

        newMazeButton.addActionListener(this);
        resetButton.addActionListener(this);
        instructionsButton.addActionListener(this);

        topPanel.add(newMazeButton);
        topPanel.add(resetButton);
        topPanel.add(instructionsButton);
        topPanel.add(movesLabel);

        add(topPanel, BorderLayout.NORTH);
        add(mazePanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocusInWindow();
    }


    // This method handles button clicks for making a new maze,
    // resetting the player, and showing instructions.

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newMazeButton) {
            mazePanel.generateNewMaze();
            moves = 0;
            gameWon = false;
            movesLabel.setText("Moves: 0");
            statusLabel.setText("A new random maze was created.");
        }
        else if (e.getSource() == resetButton) {
            mazePanel.resetPlayer();
            moves = 0;
            gameWon = false;
            movesLabel.setText("Moves: 0");
            statusLabel.setText("Player reset to the start.");
        }
        else if (e.getSource() == instructionsButton) {
            statusLabel.setText("Arrow keys move the player. Reach the green square to win.");
        }

        requestFocusInWindow();
    }


    // Method updates the move counter after a successful move.
    // It exists so the panel can tell the frame when the player moves.

    public void recordMove() {
        moves++;
        movesLabel.setText("Moves: " + moves);
    }


    // Method updates the status label when the player wins.
    // It prevents the program from repeatedly announcing the win.

    public void playerWon() {
        if (!gameWon) {
            gameWon = true;
            statusLabel.setText("You escaped the maze in " + moves + " moves!");
        }
    }


    // This method handles when a key is typed.
    // It's required by KeyListener, but this program does not use it.

    public void keyTyped(KeyEvent e) {
    }


    // The following method handles arrow key input and moves the player in the maze.
    // After moving, the panel is repainted to show the new player position.

    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moved = mazePanel.movePlayer(-1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moved = mazePanel.movePlayer(1, 0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moved = mazePanel.movePlayer(0, -1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moved = mazePanel.movePlayer(0, 1);
        }

        if (moved) {
            recordMove();
        }
    }


    // This method handles when a key is released.
    // Again, it is required by KeyListener, but this program does not use it.

    public void keyReleased(KeyEvent e) {
    }


    // This inner class is the drawing area for the maze.
    // It stores the maze data, creates random mazes, and draws the player and goal.

    static class MazePanel extends JPanel {
        private int[][] maze;
        private final int rows;
        private final int cols;
        private final int cellSize;
        private int playerRow;
        private int playerCol;
        private int startRow;
        private int startCol;
        private int goalRow;
        private int goalCol;
        private final Random random;
        private final MazeExplorer parentFrame;


        // Constructor creates the panel and makes the first random maze.

        public MazePanel(MazeExplorer frame) {
            rows = 21;
            cols = 21;
            cellSize = 25;
            random = new Random();
            parentFrame = frame;
            setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
            setBackground(Color.WHITE);
            generateNewMaze();
        }


        // This method creates a brand-new maze using recursive backtracking.
        // It fills the grid with walls first and then carves out paths.

        public void generateNewMaze() {
            maze = new int[rows][cols];

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    maze[r][c] = 1;
                }
            }

            carvePath(1, 1);

            startRow = 1;
            startCol = 1;
            goalRow = rows - 2;
            goalCol = cols - 2;

            maze[startRow][startCol] = 0;
            maze[goalRow][goalCol] = 2;

            resetPlayer();
        }


        // This method uses recursion to carve paths through the maze.
        // It randomly chooses directions so each maze is different.

        private void carvePath(int row, int col) {
            maze[row][col] = 0;

            int[] directions = {0, 1, 2, 3};
            shuffleDirections(directions);

            for (int i = 0; i < directions.length; i++) {
                int newRow = row;
                int newCol = col;

                if (directions[i] == 0) {
                    newRow = row - 2;
                }
                else if (directions[i] == 1) {
                    newRow = row + 2;
                }
                else if (directions[i] == 2) {
                    newCol = col - 2;
                }
                else if (directions[i] == 3) {
                    newCol = col + 2;
                }

                if (isInsideMaze(newRow, newCol) && maze[newRow][newCol] == 1) {
                    maze[(row + newRow) / 2][(col + newCol) / 2] = 0;
                    carvePath(newRow, newCol);
                }
            }
        }


        // This method randomly shuffles the direction array.
        // It exists to help create different maze layouts each time.

        private void shuffleDirections(int[] directions) {
            for (int i = 0; i < directions.length; i++) {
                int randomIndex = random.nextInt(directions.length);
                int temp = directions[i];
                directions[i] = directions[randomIndex];
                directions[randomIndex] = temp;
            }
        }


        // This method checks if a maze cell is inside the valid drawing area.
        // It helps stop the path carving from going outside the maze.

        private boolean isInsideMaze(int row, int col) {
            return row > 0 && row < rows - 1 && col > 0 && col < cols - 1;
        }


        // Method resets the player back to the start location.
        // It is used when a new maze is created or when reset is pressed.

        public void resetPlayer() {
            playerRow = startRow;
            playerCol = startCol;
            repaint();
        }


        // This method moves the player if the next square is not a wall.
        // It returns true when a move happens so the frame can update the move counter.

        public boolean movePlayer(int rowChange, int colChange) {
            int nextRow = playerRow + rowChange;
            int nextCol = playerCol + colChange;

            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                if (maze[nextRow][nextCol] != 1) {
                    playerRow = nextRow;
                    playerCol = nextCol;
                    repaint();

                    if (playerRow == goalRow && playerCol == goalCol) {
                        parentFrame.playerWon();
                    }
                    return true;
                }
            }
            return false;
        }


        // This method draws the maze walls, paths, player, and exit.
        // It is the main graphics method for the whole game.

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (maze[r][c] == 1) {
                        g2.setColor(Color.BLACK);
                        g2.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                    }
                    else if (maze[r][c] == 2) {
                        g2.setColor(Color.GREEN);
                        g2.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                    }
                    else {
                        g2.setColor(Color.WHITE);
                        g2.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                    }

                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
                }
            }

            g2.setColor(Color.BLUE);
            g2.fillOval(playerCol * cellSize + 4, playerRow * cellSize + 4, cellSize - 8, cellSize - 8);
        }
    }
}