package RodionGraphicalLib;

import javax.swing.*;
import java.awt.*;

public class RJFrame extends JFrame {
    {
        setSize(900,900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        new Timer(1000/4,e->repaint()).start();
    }

    @Override
    public Component add(Component comp) {
        Component result = super.add(comp);
        revalidate();
        repaint();
        return result;
    }
}
