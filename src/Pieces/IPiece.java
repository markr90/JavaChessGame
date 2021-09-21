package Pieces;

import Game.Board;
import Game.Move;

import java.awt.image.BufferedImage;

public interface IPiece {
    boolean isMoveLegal(Board board, Move move);
    void incrementMoveCounter();
    String symbol();
    BufferedImage image();
    boolean isWhite();
}
