package Render.Drawers;
import Game.Board;

import java.awt.*;

public abstract class BoardDecorator implements IBoardDrawer {
    protected IBoardDrawer graphic;

    public BoardDecorator(IBoardDrawer graphic) {
        this.graphic = graphic;
    }

    public void draw(Graphics g) {
        graphic.draw(g);
    }

    @Override
    public int getBorderWidth() {
        return graphic.getBorderWidth();
    }

    @Override
    public int getSquareWidth() {
        return graphic.getSquareWidth();
    }

    @Override
    public Board getBoard() {
        return graphic.getBoard();
    }
}
