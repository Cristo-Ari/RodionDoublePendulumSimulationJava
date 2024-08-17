package RodionGraphicalLib.ADrawGeometry;

import RodionAGeometry.Oval;
import RodionAGeometry.Shape;
import RodionGraphicalLib.Drawable;

import java.awt.*;

public class DrawableOval extends Oval implements Drawable {

    public DrawableOval(Oval oval) {
        super(oval.pos,oval.size);
    }

    @Override
    public Shape getShape() {
        return this;
    }

    @Override
    public Color getColor() {
        return Color.blue;
    }
}
