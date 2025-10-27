package org.example.model;

import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable {
    private final Color color;
    private RectangularShape shape;
    private FillBehavior fb;

    public MyShape(RectangularShape shape) {
        this.shape = shape;
        color = Color.GRAY;
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);
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

    // TODO: Попробовать вызовы через разные конструкторы, затем переделать создание через фабрику
    public MyShape() {
        color = Color.BLUE;
        shape = new Rectangle2D.Double();
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    // TODO: Попробовать вызовы через разные конструкторы, затем переделать создание через фабрику
    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.color = color;
        this.shape = shape;
        this.fb = fb;
        this.fb.setShape(shape);
        this.fb.setColor(color);
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
