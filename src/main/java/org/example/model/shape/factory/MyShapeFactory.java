package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.FillType;
import org.example.model.shape.fill.NoFill;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;




public class MyShapeFactory {
    public static MyShape createShape(ShapeType type, Color color, FillType fillType) {

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

        FillBehavior fillBehavior;

        switch (fillType) {
            case FILL:
                fillBehavior = new Fill();
                break;
            case NO_FILL:
                fillBehavior = new NoFill();
                break;
            default:
                fillBehavior = new Fill();
        }
        fillBehavior.setColor(color);

        return new MyShape(color, shape, fillBehavior);
    }
}
