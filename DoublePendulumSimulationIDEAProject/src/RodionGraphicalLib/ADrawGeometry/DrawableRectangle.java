package RodionGraphicalLib.ADrawGeometry;

import RodionAGeometry.Rectangle;
import RodionAGeometry.Shape;
import RodionGraphicalLib.Drawable;

import java.awt.*;

public class DrawableRectangle extends Rectangle implements Drawable {
    public DrawableRectangle(Rectangle rect) {
        super(rect.pos, rect.size);
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
