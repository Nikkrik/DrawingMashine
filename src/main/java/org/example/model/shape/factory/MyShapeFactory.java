package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.ShapeType;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;


public class MyShapeFactory {
    public static MyShape createShape(ShapeType type, Color color) {
        RectangularShape shape;

        switch (type) {
            case RECTANGLE:
                shape = new Rectangle2D.Double();
                break;
            case ELLIPSE:
                shape = new Ellipse2D.Double();
                break;
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }

        Fill fill = new Fill();
        fill.setColor(color);

        return new MyShape(color, shape, fill);

    }

    public static MyShape createShape(ShapeType type){
        return createShape(type, Color.GRAY);
    }

}
