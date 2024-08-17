package DoublePendulumSimulation;

import RodionAGeometry.Dot;
import RodionAGeometry.Line;
import RodionAGeometry.Oval;
import RodionAGeometry.AGeometryAdvansed.AMultipliedSize;
import RodionAGeometry.AGeometryAdvansed.ARectangleCenter;
import RodionGraphicalLib.ADrawGeometry.AdvansedGeometry.SquareInsideWindow;
import RodionGraphicalLib.ADrawGeometry.DrawableLine;
import RodionGraphicalLib.ADrawGeometry.DrawableOval;
import RodionGraphicalLib.ADrawGeometry.DrawableRectangle;
import RodionGraphicalLib.DisplayedDouble;

import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        OvalDrawWindow winodow = new OvalDrawWindow();
        final DisplayedDouble[] someValues = {
                new DisplayedDouble(0.1,1, 0.5,"line1 length"),
                new DisplayedDouble(-Math.PI*4,Math.PI*2, 1,"line1 angle"),
                new DisplayedDouble(0.1,1, 0.5,"line2 length"),
                new DisplayedDouble(0,3.14*2, 2,"line2 angle"),
                new DisplayedDouble(0,0.5, 0.01,"windowOffset"),
                new DisplayedDouble(9,1000, 1000,"Delta Time"),
                new DisplayedDouble(0,1, 0,"Resistant factor"),
                new DisplayedDouble(0,10, 1,"Mass Green"),
                new DisplayedDouble(0,10, 10,"Mass Red"),
        };
        Arrays.stream(someValues).forEach(x->x.addObserver(winodow));
        DrawableRectangle screenRect = new DrawableRectangle(new SquareInsideWindow(winodow,someValues[4].value));

        DoublePendulumParameters model = new DoublePendulumParameters(){
            {
                dot1= new ARectangleCenter(screenRect);
                firstLineLength = ()->someValues[0].value.get()*screenRect.size.width.get()/4;
                firstLineAngle = someValues[1].value;
                secondLineLength = ()->someValues[2].value.get()*screenRect.size.width.get()/4;
                secondLineAngle = ()-> someValues[3].value.get();
            }
        };

        //--Вычисляем точки
        Dot dot2 = new Dot(
                ()-> model.dot1.x.get()+Math.sin(model.firstLineAngle.get())*model.firstLineLength.get(),
                ()-> model.dot1.y.get()+Math.cos(model.firstLineAngle.get())*model.firstLineLength.get()
        );
        Dot dot3 = new Dot(
                ()-> dot2.x.get()+Math.sin(model.secondLineAngle.get())*model.secondLineLength.get(),
                ()-> dot2.y.get()+Math.cos(model.secondLineAngle.get())*model.secondLineLength.get()
        );


        //--Определяем Фигуры отрисовки


        DrawableOval dot1draw = new DrawableOval(new Oval(
                model.dot1,
                new AMultipliedSize(screenRect.size,()->0.05)
        ));
        DrawableOval dot2draw = new DrawableOval(new Oval(
                dot2,
                new AMultipliedSize(screenRect.size,()->0.05)
        )){
            public Color getColor() {
                return Color.green;
            }
        };
        DrawableLine drawLine1 = new DrawableLine(
                new Line(
                        dot1draw.pos,dot2draw.pos
                ),
                ()->screenRect.size.height.get()*0.02
        ){
            public Color getColor() {
                return Color.green;
            }
        };
        DrawableOval dot3draw = new DrawableOval(new Oval(
                dot3,
                new AMultipliedSize(screenRect.size,()->0.05)
        )){
            public Color getColor() {
                return Color.red;
            }
        };

        DrawableLine drawLine2 = new DrawableLine(
                new Line(
                        dot2draw.pos,dot3draw.pos
                ),
                ()->screenRect.size.height.get()*0.02
        ){
            public Color getColor() {
                return Color.red;
            }
        };

        //Добавляем фигуры к окну
        winodow.drawableObjects.add(drawLine1);
        winodow.drawableObjects.add(drawLine2);
        winodow.drawableObjects.add(dot1draw);
        winodow.drawableObjects.add(dot2draw);
        winodow.drawableObjects.add(dot3draw);
        winodow.drawableObjects.add(screenRect);

        new DoublePendulumSimulation(someValues);
    }
}