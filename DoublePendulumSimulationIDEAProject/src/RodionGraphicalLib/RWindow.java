package RodionGraphicalLib;

import RodionAGeometry.Oval;
import RodionAGeometry.Rectangle;
import RodionAGeometry.Shape;
import RodionANumbers.ABoolean;
import RodionANumbers.ADouble;
import RodionGraphicalLib.ADrawGeometry.DrawableLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class RWindow {
    public List<Drawable> drawableObjects = new ArrayList<Drawable>();

    int mX=0,mY=0;
    boolean mDown = false;

    public ADouble mousePosX = new ADouble() {
        @Override
        public double get() {
            return mX;
        }
    };
    public ADouble mousePosY = new ADouble() {
        public double get() {
            return mY;
        }
    };
    public ABoolean mouseDown = new ABoolean() {
        @Override
        public boolean get() {
            return mDown;
        }
    };

    public JButton mainButton = new JButton() {
        {
            addMouseMotionListener(new MouseMotionListener() {
                public void mouseDragged(MouseEvent e) {
                    mX = e.getX();
                    mY = e.getY();
                    mDragged(e);

                    repaint();
                }
                public void mouseMoved(MouseEvent e) {
                    mX = e.getX();
                    mY = e.getY();
                    mMoved(e);

                    repaint();
                }
            });
            addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    mClicked(e);
                    repaint();
                }
                public void mousePressed(MouseEvent e) {
                    mDown=true;
                    mPressed(e);
                    repaint();
                }
                public void mouseReleased(MouseEvent e) {
                    mDown=false;
                    mReleased(e);
                    repaint();
                }
                public void mouseEntered(MouseEvent e) {
                    mEntered(e);
                    repaint();
                }
                public void mouseExited(MouseEvent e) {
                    mExited(e);
                    repaint();
                }
            });
            addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                    kTyped(e);
                    repaint();
                }
                public void keyPressed(KeyEvent e) {
                    kPressed(e);
                    repaint();
                }
                public void keyReleased(KeyEvent e) {
                    kReleased(e);
                    repaint();
                }
            });
        }

        public void paint(Graphics g){
            super.paint(g);
            for (Drawable crtObj: drawableObjects){
                Shape crtShape = crtObj.getShape();

                g.setColor(crtObj.getColor());
                if (crtShape instanceof Oval){
                    Oval oval = ((Oval) crtShape);

                    int ovalWidth = (int) oval.size.width.get();
                    int ovalHeight = (int) oval.size.height.get();

                    g.fillOval(
                            (int) oval.pos.x.get()-ovalHeight/2,
                            (int) oval.pos.y.get()-ovalWidth/2,
                            ovalWidth,
                            ovalHeight
                    );
                }

                if (crtShape instanceof RodionAGeometry.Rectangle){
                    RodionAGeometry.Rectangle rect = (Rectangle) crtShape;
                    g.drawRect(
                        (int) rect.pos.x.get(),
                        (int) rect.pos.y.get(),
                        (int) rect.size.width.get(),
                        (int) rect.size.height.get());
                }

                if (crtShape instanceof DrawableLine){
                    DrawableLine line = (DrawableLine) crtShape;

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke((float) line.width.get()));
                    g2d.draw(new Line2D.Double(
                            line.pos.x.get(),
                            line.pos.y.get(),
                            line.pos2.x.get(),
                            line.pos2.y.get()
                    ));
                    g2d.setStroke(new BasicStroke(1));
                }

            }
        }
    };

    public ADouble width = new ADouble() {
        @Override
        public double get() {
            return mainButton.getWidth();
        }
    };
    public ADouble height = new ADouble() {
        @Override
        public double get() {
            return mainButton.getHeight();
        }
    };

    public RJFrame jFrame = new RJFrame() {
        {
            add(mainButton);
        }
    };

    public void mDragged(MouseEvent e) {}
    public void mMoved(MouseEvent e) {}

    public void mClicked(MouseEvent e) {}
    public void mPressed(MouseEvent e) {}
    public void mReleased(MouseEvent e) {}
    public void mEntered(MouseEvent e) {}
    public void mExited(MouseEvent e) {}

    public void kTyped(KeyEvent e) {}
    public void kPressed(KeyEvent e) {}
    public void kReleased(KeyEvent e) {}
}