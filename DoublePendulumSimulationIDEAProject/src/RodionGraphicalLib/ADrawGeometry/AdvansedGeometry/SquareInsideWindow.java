package RodionGraphicalLib.ADrawGeometry.AdvansedGeometry;

import RodionAGeometry.Dot;
import RodionAGeometry.Rectangle;
import RodionAGeometry.Size;
import RodionANumbers.ADouble;
import RodionGraphicalLib.RWindow;

public class SquareInsideWindow extends Rectangle {

    public SquareInsideWindow(RWindow someWindow, ADouble offset) {
        super(new Dot(
                        ()->{
                            double width = someWindow.width.get()<=someWindow.height.get()?
                                    someWindow.width.get()*(offset.get()):
                                    someWindow.height.get()*(offset.get());

                            double additionalOffset = Math.abs(someWindow.width.get()>=someWindow.height.get()? Math.abs(someWindow.width.get()-someWindow.height.get())/2:0);

                            return width+additionalOffset;
                        },
                        ()->{
                            double height = someWindow.width.get()>=someWindow.height.get()?
                                    someWindow.height.get()*(offset.get()):
                                    someWindow.width.get()*(offset.get());

                            double additionalOffset = Math.abs(someWindow.width.get()<=someWindow.height.get()? Math.abs(someWindow.width.get()-someWindow.height.get())/2:0);

                            return height+additionalOffset;
                        }
                ),
                new Size(
                        ()->someWindow.width.get()<=someWindow.height.get()?
                                someWindow.width.get()*(1-offset.get()*2):
                                someWindow.height.get()*(1-offset.get()*2),
                        ()->someWindow.width.get()<=someWindow.height.get()?
                                someWindow.width.get()*(1-offset.get()*2):
                                someWindow.height.get()*(1-offset.get()*2)
                )
        );

    }
}
