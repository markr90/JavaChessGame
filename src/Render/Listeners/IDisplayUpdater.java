package Render.Listeners;

import Render.IDisplay;

public interface IDisplayUpdater {
    void attachDisplay(IDisplay display);
    void updateDisplays();
}
