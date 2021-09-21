package Game;

import Pieces.IPiece;

public class Spot {
    private IPiece _piece;
    private int row;
    private int col;

    public Spot(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(row, col);
    }

    public String symbolRepresentation() {
        return _piece != null ? _piece.symbol() : ".";
    }

    public boolean hasPiece() {
        return _piece != null;
    }

    public void clearPiece() { _piece = null; }

    public IPiece getPiece() {
        return _piece;
    }

    public void setPiece(IPiece piece) {
        _piece = piece;
    }
}
