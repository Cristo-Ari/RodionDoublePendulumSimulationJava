package RodionAGeometry.AGeometryAdvansed;

import RodionAGeometry.Size;
import RodionANumbers.ADouble;

public class AMultipliedSize extends Size {
    public AMultipliedSize(Size size, ADouble multiplier) {
        super(()->size.width.get()*multiplier.get(), ()->size.height.get()*multiplier.get());
    }
}
