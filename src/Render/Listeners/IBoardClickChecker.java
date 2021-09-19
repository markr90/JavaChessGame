package Render.Listeners;

import Render.Board.BoardSquare;

import java.awt.event.MouseEvent;

public interface IBoardClickChecker {
    BoardSquare getSquareClicked(MouseEvent mouseEvent);
}
