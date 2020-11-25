public class Cell {

    int x;
    int y;
    boolean living;
    boolean nextStatus;

    /**
     * Create new cell with x/y coordinate and status (alive/dead).
     * @param x - Int; x-coordinate.
     * @param y - Int; y-coordinate.
     * @param living Boolean; Alive or not.
     */
    public Cell(int x, int y, boolean living) {
        this.x = x;
        this.y = y;
        this.living = living;
    }

    /**
     * Change the current status to next status.
     */
    public void update() {
        this.living = this.nextStatus;
    }

    /**
     * Determine if this cell should be alive or dead on next tick.
     * @param cellGrid Cell[][]; The grid of Cell objects.
     * @param gridWidth int; Width of grid.
     */
    public void tick(Cell[][] cellGrid, int gridWidth) {
        // Determine what the cell does.
        int neighbors = 0;
        for (int checkX = this.x - 1; checkX <= this.x + 1; checkX++) {
            for (int checkY = this.y - 1; checkY <= this.y + 1; checkY++) {
                if (checkX == this.x && checkY == this.y) {
                } else {
                    // Neighbors
                    int realX = checkX;
                    int realY = checkY;

                    // Adjust for grid borders
                    if (realX < 0) realX = gridWidth + realX;
                    if (realY < 0) realY = gridWidth + realY;
                    if (realX >= gridWidth) realX = realX - gridWidth;
                    if (realY >= gridWidth) realY = realY - gridWidth;

                    neighbors += (cellGrid[realX][realY].living) ? 1 : 0;
                }
            }
        }

        // Logic
        if (living) {
            if (neighbors < 2) {
                nextStatus = false;
            } else if (neighbors < 4) {
                nextStatus = true;
            } else {
                nextStatus = false;
            }
        } else {
            if (neighbors == 3) {
                nextStatus = true;
            }
        }

    }
}
