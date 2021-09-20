package GameDisplay.MouseClicks;

import java.awt.event.MouseEvent;

public interface IMouseClickHandler {
    String mouseClickHandlerId();
    void handleMouseClick(MouseEvent mouseEvent);
}
