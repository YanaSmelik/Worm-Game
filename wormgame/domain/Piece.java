package wormgame.domain;

public class Piece {

    private int x;
    private int y;
    private int width;

    public Piece(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    //returns true if the object has the same coordinates as the Piece instance received as parameter
    public boolean runsInto(Piece piece) {
        if (x == piece.getX() && y == piece.getY()) {
            return true;
        }
        return false;
    }
}
