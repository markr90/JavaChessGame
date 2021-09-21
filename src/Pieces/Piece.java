package Pieces;

import Game.Board;
import Game.Move;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class Piece implements IPiece {
    private String symbol;
    private boolean isWhite;
    private BufferedImage image;
    private int numberOfTimesMoved = 0;

    public Piece(String symbol, boolean isWhite){
        this.symbol = symbol;
        this.isWhite = isWhite;
        String fn = fileName();


        URL imageFile = getClass().getResource("/Resources/Images/" + fn);
        try {
            image = ImageIO.read(new File(imageFile.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incremementMoveCounter() {
        numberOfTimesMoved++;
    }

    @Override
    public BufferedImage image() {
        return image;
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
