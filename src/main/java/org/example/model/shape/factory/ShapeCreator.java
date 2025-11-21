package org.example.model.shape.factory;

import org.example.controller.MenuState;
import org.example.model.MyShape;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;

import java.awt.*;


public class ShapeCreator {
    private static ShapeCreator instance;
    private MenuState menuState;

    private ShapeCreator(){

    }

    public static ShapeCreator getInstance(){

        if (instance == null) {
            instance = new ShapeCreator();
        }
        return instance;
    }

    public void configure(MenuState state){
        this.menuState = state;
    }

    public MyShape createShape(){
        if (menuState == null) {
            return MyShapeFactory.createShape(ShapeType.RECTANGLE, Color.GRAY, FillType.FILL);
        }
        return MyShapeFactory.createShape(
                menuState.getShapeType(),
                menuState.getColor(),
                menuState.getFillType()
        );
    }
}
