package RodionAGeometry.AGeometryAdvansed;

import RodionAGeometry.Dot;
import RodionAGeometry.Size;
import RodionGraphicalLib.Drawable;
import RodionAGeometry.Rectangle;
import RodionAGeometry.Shape;

import java.awt.*;

public class HalfWindowSizeSquare extends Rectangle implements Drawable {

    public HalfWindowSizeSquare(Dot pos, Size size) {
        super(pos, size);
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }
}
