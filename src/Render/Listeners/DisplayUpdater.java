package Render.Listeners;

import Render.IDisplay;

import java.util.ArrayList;

public class DisplayUpdater implements IDisplayUpdater {
    private ArrayList<IDisplay> displays = new ArrayList<IDisplay>();

    @Override
    public void attachDisplay(IDisplay display) {
        displays.add(display);
    }

    @Override
    public void updateDisplays() {
        for (IDisplay display : displays) {
            display.Update();
        }
    }
}
