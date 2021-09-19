package Game;

import Pieces.IPiece;

public class Spot {
    private IPiece _piece;
    private int x;
    private int y;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    public String symbolRepresentation() {
        return _piece != null ? _piece.Symbol() : ".";
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
