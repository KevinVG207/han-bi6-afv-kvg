import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameOfLife {

    int gridWidth;
    Cell[][] cellGrid;
    JPanel p;
    JButton updateButton;

    /**
     * Creates a grid of randomized cells, based on user input width.
     */
    public GameOfLife() {
        Random r = new Random();

        String inputWidth = JOptionPane.showInputDialog("Enter grid size (0~240): ");
        try {
            gridWidth = Integer.parseInt(inputWidth);
            cellGrid = new Cell[gridWidth][gridWidth];
            for (int x = 0; x < gridWidth; x++) {
                for (int y = 0; y < gridWidth; y++) {
                    cellGrid[x][y] = new Cell(x, y, r.nextBoolean());
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect value entered.");
        }
    }

    /**
     * Creates GoL JFrame.
     * @throws InterruptedException
     */
    public void run() throws InterruptedException {
        // Setting up JFrame
        JFrame f = new JFrame("CCGoL — Crappy Conway's Game of Life");
        f.setLayout(new FlowLayout());
        f.setSize(500, 560);
        f.setResizable(false);

        // Add a JPanel canvas
        p = new JPanel();
        p.setPreferredSize(new Dimension(480, 480));
        p.setBackground(Color.lightGray);

        f.add(p);

        updateButton = new JButton("Tick");
        updateButton.addActionListener(e ->
                update());
        f.add(updateButton);

        //Create GUI
        f.setVisible(true);
        f.setLocationRelativeTo(null);

        // Wait a small amount of time, otherwise getGraphics() returns null(?)
        Thread.sleep(100);
        drawScreen();
    }

    /**
     * Called when grid needs to be updated.
     * Every cell gets ticked and then actually updated.
     */
    private void update() {
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridWidth; y++) {
                // Every cell
                cellGrid[x][y].tick(cellGrid, gridWidth);
            }
        }
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridWidth; y++) {
                // Every cell
                cellGrid[x][y].update();
            }
        }
        drawScreen();
    }

    /**
     * Draws the GoL grid to the JPanel.
     */
    private void drawScreen() {
        int panelWidth = p.getWidth();
        Graphics g = p.getGraphics();
        int cellDistance = (int) (panelWidth / (float) gridWidth);
        int cellWidth = cellDistance - 1; // To add 1 pixel border.

        g.clearRect(0, 0, panelWidth, panelWidth);
        g.setColor(Color.black);
        g.fillRect(0, 0, panelWidth, panelWidth);

        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridWidth; y++) {
                if (cellGrid[x][y].living) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(Color.darkGray);
                }
                g.fillRect(x * cellDistance, y * cellDistance, cellWidth, cellWidth);
            }
        }

    }

}
