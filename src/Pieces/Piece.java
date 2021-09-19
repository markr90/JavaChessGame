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

    public Piece(String symbol, boolean isWhite) throws IOException {
        this.symbol = symbol;
        this.isWhite = isWhite;
        String fn = fileName(symbol, isWhite);
        URL imageFile = getClass().getResource("/Resources/Images/" + fn);
        try {
            image = ImageIO.read(new File(imageFile.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage Image() {
        return image;
    }

    @Override
    public boolean IsWhite() {
        return isWhite;
    }

    public abstract boolean IsMoveLegal(Board board, Move move);

    @Override
    public String Symbol() {
        return symbol;
    }

    private String fileName(String symbol, boolean isWhite) {
        String color = isWhite ? "wh" : "bl";
        if (symbol.equals("P")) {
            return "pawn_" + color + ".png";
        }
        if (symbol.equals("R")) {
            return "rook_" + color + ".png";
        }
        return "";
    }
}
