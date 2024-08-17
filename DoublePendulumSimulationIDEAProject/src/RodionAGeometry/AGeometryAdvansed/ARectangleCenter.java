package RodionAGeometry.AGeometryAdvansed;

import RodionAGeometry.Dot;
import RodionAGeometry.Rectangle;

public class ARectangleCenter extends Dot {
    public ARectangleCenter(Rectangle rect) {
        super(()->rect.pos.x.get()+rect.size.width.get()/2,()->rect.pos.y.get()+rect.size.height.get()/2);
    }
}
