package org.example.controller;

import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;
import java.awt.Color;

public class MenuState {
    private boolean fill = true;
    private Color color = Color.GRAY;
    private ShapeType shapeType = ShapeType.RECTANGLE;

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public FillType getFillType(){
        return fill ? FillType.FILL : FillType.NO_FILL;
    }
}
