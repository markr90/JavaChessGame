package Render.Announcer;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerTurnAnnouncer extends JPanel {
    private Game game;
    private int posX;
    private int posY;
    private int fontSize;

    public PlayerTurnAnnouncer(Game game, int gamewidth, int borderWidth) {
        posX = gamewidth / 2;
        posY = borderWidth / 2;

        fontSize = borderWidth / 3;

        this.game = game;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        String message = "It is " + game.CurPlayer().Name() + "'s turn";
        Rectangle rect = new Rectangle(0, 0, posX * 2, posY*2);
        drawCenteredString(g, message, rect, new Font("Arial", Font.BOLD, fontSize));
    }


    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}
