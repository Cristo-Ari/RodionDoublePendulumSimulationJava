package RodionAGeometry;

public class Line extends Shape{
    public Dot pos2;

    public Line(Dot pos1,Dot pos2) {
        super(pos1);
        this.pos2=pos2;
    }
}
