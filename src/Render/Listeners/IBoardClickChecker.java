package Render.Listeners;

import Render.Drawers.BoardSquare;

import java.awt.event.MouseEvent;

public interface IBoardClickChecker {
    BoardSquare getSquareClicked(MouseEvent mouseEvent);
}
