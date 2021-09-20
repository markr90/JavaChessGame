package GameDisplay.MouseClicks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class MouseClickDistributor implements MouseListener {
    private HashMap<String, IMouseClickHandler> mouseClickHandlers;

    public MouseClickDistributor() {
        mouseClickHandlers = new HashMap<>();
    }

    public void subscribe(IMouseClickHandler mouseClickHandler) {
        mouseClickHandlers.put(mouseClickHandler.mouseClickHandlerId(), mouseClickHandler);
    }

    public void unsubscribe(IMouseClickHandler mouseClickHandler) {
        mouseClickHandlers.remove(mouseClickHandler.mouseClickHandlerId());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mousepressed");
        for (IMouseClickHandler mouseClickHandler: mouseClickHandlers.values()) {
            mouseClickHandler.handleMouseClick(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
