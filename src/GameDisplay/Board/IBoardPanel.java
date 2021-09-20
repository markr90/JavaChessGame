package GameDisplay.Board;

import Game.Board;

import java.awt.event.MouseEvent;

public interface IBoardPanel {
    Board getBoard();
    BoardSquare getSquareClicked(MouseEvent mouseEvent);
}
