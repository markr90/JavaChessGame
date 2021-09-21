package Pieces;

import Game.Board;
import Game.Move;

import javax.swing.*;

public interface IPiece {
    boolean isMoveLegal(Board board, Move move);
    void incrementMoveCounter();
    int numberOfTimesMoved();
    String symbol();
    ImageIcon icon();
    boolean isWhite();
}
