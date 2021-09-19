package Render.Listeners;

import Game.Spot;

import java.awt.event.MouseEvent;

public interface IBoardClickChecker {
    Spot getSpotClicked(MouseEvent mouseEvent);
}
