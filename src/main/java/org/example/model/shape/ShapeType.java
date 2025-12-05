package org.example.model.shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

public enum ShapeType {
    RECTANGLE{
        @Override
        public RectangularShape create(){
            return new Rectangle2D.Double();
        }
    },
    ELLIPSE{
        @Override
        public RectangularShape create(){
            return new Ellipse2D.Double();
        }
    };

    public abstract RectangularShape create();
}
