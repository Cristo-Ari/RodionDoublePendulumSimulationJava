package RodionGraphicalLib.ADrawGeometry;

import RodionAGeometry.Line;
import RodionAGeometry.Shape;
import RodionANumbers.ADouble;
import RodionGraphicalLib.Drawable;

import java.awt.*;

public class DrawableLine extends Line implements Drawable {
    public ADouble width;
    public DrawableLine(Line line, ADouble width) {
        super(line.pos, line.pos2);
        this.width=width;

    }

    @Override
    public Shape getShape() {
        return this;
    }

    @Override
    public Color getColor() {
        return Color.black;
    }
}
