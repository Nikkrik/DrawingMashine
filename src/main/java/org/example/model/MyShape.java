package org.example.model;

import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable{
    private final Color color;
    private RectangularShape shape;
    private FillBehavior fb;

    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.shape = shape;
        this.color = color;
        this.fb = new Fill();
        this.fb.setColor(color);
        this.fb.setShape(shape);
    }

    public MyShape clone() {
        // Создаем копию формы
        RectangularShape shapeCopy = (RectangularShape) this.shape.clone();

        // Создаем новый FillBehavior того же типа
        FillBehavior fbCopy;
        if (this.fb instanceof Fill) {
            fbCopy = new Fill();
        } else if (this.fb instanceof NoFill) {
            fbCopy = new NoFill();
        } else {
            fbCopy = new Fill(); // fallback
        }
        fbCopy.setColor(this.color);
        fbCopy.setShape(shapeCopy);

        // Создаем новую фигуру
        MyShape cloned = new MyShape(this.color, shapeCopy, fbCopy);
        return cloned;
    }

    public RectangularShape getShape() {
        return shape;
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
        fb.setShape(shape);
        fb.setColor(color);
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);

    }
}
