package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;
import org.example.model.shape.fill.FillBehavior;
import java.awt.Color;


public class MyShapeFactory {
    public static MyShape createShape(ShapeType type, Color color, FillType fillType){

        var shape = type.create();
        FillBehavior fillBehavior = fillType.create();
        fillBehavior.setColor(color);
        return new MyShape(color, shape, fillBehavior);
    }
}
// от Type должна создаться фигура и заливка
