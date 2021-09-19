package Game;

public class Coordinate {
    private static char[] columnIndices = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private int _row;
    private int _col;

    public Coordinate(int row, int col) {
        _row = row;
        _col = col;
    }

    public int row() {
        return _row;
    }

    public int col() {
        return _col;
    }

    public char[] algebraic() {
        char[] algebraic = new char[] {columnIndices[_col], Character.forDigit(8 - (_row), 10)};
        return algebraic;
    }

    public char[] arrayIndices() {
        char[] arrayIndices = new char[] {Character.forDigit(_row, 10), Character.forDigit(_col, 10)};
        return arrayIndices;
    }

    public boolean IsInbounds() {
        return _row < 8 && _row >= 0 && _col >= 0 && _col < 8;
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

        return this.row() == other.row() && this.col() == other.col();

    }
}
