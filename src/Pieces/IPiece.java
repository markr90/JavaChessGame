package Pieces;

import Game.Board;
import Game.Move;

import java.awt.image.BufferedImage;

public interface IPiece {
    public boolean IsMoveLegal(Board board, Move move);
    public String Symbol();
    public BufferedImage Image();
    public boolean IsWhite();
}
