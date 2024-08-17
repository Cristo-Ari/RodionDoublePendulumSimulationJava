package DoublePendulumSimulation;

import RodionANumbers.ADoubleChangeObserver;
import RodionGraphicalLib.RWindow;

public class OvalDrawWindow extends RWindow implements ADoubleChangeObserver {
    public void valueChanged() {
        jFrame.repaint();
    }
}