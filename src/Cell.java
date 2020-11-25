public class Cell {

    int x;
    int y;
    boolean living;
    boolean nextStatus;

    public Cell(int x, int y, boolean living) {
        this.x = x;
        this.y = y;
        this.living = living;
    }

    public void update() {
        this.living = this.nextStatus;
    }

    public void tick(Cell[][] cellGrid, int gridWidth) {
        // Determine what the cell does.
        int neighbors = 0;
        //System.out.println("Checking:");
        //System.out.println(x + " " + y);
        for (int checkX = this.x - 1; checkX <= this.x + 1; checkX++) {
            for (int checkY = this.y - 1; checkY <= this.y + 1; checkY++) {
                //System.out.println(checkX + " " + checkY);
                if (checkX == this.x && checkY == this.y) {

                } else {
                    // Neighbors
                    int realX = checkX;
                    int realY = checkY;
                    if (realX < 0) realX = gridWidth + realX;
                    if (realY < 0) realY = gridWidth + realY;
                    if (realX >= gridWidth) realX = realX - gridWidth;
                    if (realY >= gridWidth) realY = realY - gridWidth;
                    neighbors += (cellGrid[realX][realY].living) ? 1 : 0;
                    //System.out.println("checking neighbor " + realX +" "+ realY);
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
