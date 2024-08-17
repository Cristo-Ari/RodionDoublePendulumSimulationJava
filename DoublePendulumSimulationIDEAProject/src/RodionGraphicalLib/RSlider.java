package RodionGraphicalLib;

import javax.swing.*;

public abstract class RSlider extends JSlider {
    RSlider(double startValue){
        super(0,2000,(int)(startValue*2000));
        addChangeListener(e -> valueChanged((double)getValue()/2000));
    }

    abstract void valueChanged(double value);

}
