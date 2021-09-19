package Game;

public class Coordinate {
    private static char[] columnIndices = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private int _x;
    private int _y;

    public Coordinate(int x, int y) {
        _x = x;
        _y = y;
    }

    public int X() {
        return _x;
    }

    public int Y() {
        return _y;
    }

    public char[] Algebraic() {
        char[] algebraic = new char[] {columnIndices[_x], Character.forDigit(_y + 1, 10)};
        return algebraic;
    }

    public boolean IsInbounds() {
        return _x < 8 && _x >= 0 && _y >= 0 && _y < 8;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate other = (Coordinate) o;

        return this.X() == other.X() && this.Y() == other.Y();

    }
}
