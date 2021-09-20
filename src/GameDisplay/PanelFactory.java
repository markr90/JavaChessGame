package GameDisplay;

import Game.Game;
import GameDisplay.Board.AnnouncerPanel;
import GameDisplay.Board.ChessBoard;
import GameDisplay.MouseClicks.MouseClickDistributor;

import javax.swing.*;
import java.awt.*;

public class PanelFactory {
    private Game game;
    private int width;
    private int height;
    private int boardPanelWidth;
    private MouseClickDistributor mouseClickDistributor;

    public PanelFactory(Game game, int frameWidth, int frameHeight) {
        boardPanelWidth = frameHeight;
        this.game = game;
        this.width = frameWidth;
        this.height = frameHeight;
        mouseClickDistributor = new MouseClickDistributor();
    }

    public JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(createBoardPanel());
        mainPanel.add(createMoveHistoryPanel());
        mainPanel.addMouseListener(mouseClickDistributor);
        return mainPanel;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");

        fileMenu.add(save);
        fileMenu.add(load);

        JMenu gameMenu = new JMenu("Game");
        JMenuItem restart = new JMenuItem("Restart");

        gameMenu.add(restart);

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);

        return menuBar;
    }

    public JPanel createChessBoardPanel() {
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        ChessBoard chessBoard = new ChessBoard(game);
        mouseClickDistributor.subscribe(chessBoard);
        boardConstrain.add(chessBoard);

        boardConstrain.setBackground(Color.LIGHT_GRAY);

        return boardConstrain;
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.add(createAnnouncerPanel());
        boardPanel.add(createChessBoardPanel());
        return boardPanel;
    }

    private JPanel createAnnouncerPanel() {
        return new AnnouncerPanel(game);
    }

    private JPanel createMoveHistoryPanel() {
        JPanel moveHistoryPanel = new JPanel();
        moveHistoryPanel.setLayout(new BoxLayout(moveHistoryPanel, BoxLayout.Y_AXIS));
        moveHistoryPanel.add(new JLabel("Move history"));
        moveHistoryPanel.add(new MoveHistoryPanel(game));
        return moveHistoryPanel;
    }
}
