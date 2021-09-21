package Pieces;

import Game.Board;
import Game.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class Piece implements IPiece {
    private String symbol;
    private boolean isWhite;
    private ImageIcon icon;
    protected int steps = 0;

    public Piece(String symbol, boolean isWhite){
        this.symbol = symbol;
        this.isWhite = isWhite;
        String fn = fileName();


        URL imageFile = getClass().getResource("/Resources/Images/" + fn);
        try {
            BufferedImage image = ImageIO.read(new File(imageFile.getPath()));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void incrementMoveCounter() {
        this.steps++;
    }

    @Override
    public int numberOfTimesMoved() {
        return this.steps;
    }

    @Override
    public ImageIcon icon() {
        return icon;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }


    public abstract boolean isMoveLegal(Board board, Move move);

    @Override
    public String symbol() {
        return symbol;
    }

    public abstract String fileName();

    protected String colorSubstring() {
        return isWhite ? "wh" : "bl";
    }
}
