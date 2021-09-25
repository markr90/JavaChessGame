package Pieces;

import Game.Board;
import Game.Coordinate;
import Game.Move;
import Pieces.Movesets.MoveSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public abstract class Piece implements IPiece {
    private String symbol;
    private boolean isWhite;
    private ImageIcon icon;
    protected int steps = 0;
    private Coordinate currentCoordinate;
    private MoveSet[] moveSets;

    public Piece(String symbol, boolean isWhite, MoveSet[] moveSets){
        this.symbol = symbol;
        this.isWhite = isWhite;
        this.moveSets = moveSets;
        String fn = fileName();


        URL imageFile = getClass().getResource("/Resources/Images/" + fn);
        try {
            BufferedImage image = ImageIO.read(new File(imageFile.getPath()));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        return (obj == this);
    }

    @Override
    public void setCurrentCoordinate(Coordinate newCoord) {
        currentCoordinate = newCoord;
    }

    @Override
    public Coordinate getCurrentCoordinate() {
        return currentCoordinate;
    }

    @Override
    public void incrementMoveCounter() {
        this.steps++;
    }

    @Override
    public void reduceMoveCounter() {
        this.steps--;
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

    @Override
    public ArrayList<Move> generateAllValidMoves(Board board) {
        ArrayList<Move> validMoves = new ArrayList<>();
        Coordinate from = getCurrentCoordinate();
        Coordinate to;
        Move move;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                to = new Coordinate(i, j);
                if (!from.equals(to)) {
                    move = new Move(from, to);
                    if (isMoveLegal(board, move)) {
                        validMoves.add(move);
                    }
                }
            }
        }

        return validMoves;
    }


    @Override
    public boolean isMoveLegal(Board board, Move move) {
        for (MoveSet moveSet: moveSets) {
            if (moveSet.isValidMove(board, move)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    public abstract String fileName();

    protected String colorSubstring() {
        return isWhite ? "wh" : "bl";
    }
}
