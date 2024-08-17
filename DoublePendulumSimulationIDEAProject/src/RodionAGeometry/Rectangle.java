package RodionAGeometry;

public class Rectangle extends Shape {
    public Size size;

    public Rectangle(Dot pos,Size size) {
        super(pos);
        this.size=size;
    }
}
