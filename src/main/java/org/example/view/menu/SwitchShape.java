package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.model.shape.ShapeType;
import javax.swing.*;

public class SwitchShape implements AppCommand {
    private MenuState menuState;
    private ShapeType shapeType;
    private JRadioButtonMenuItem radioButton;

    public SwitchShape(MenuState menuState, ShapeType shapeType, JRadioButtonMenuItem radioButton) {
        this.menuState = menuState;
        this.shapeType = shapeType;
        this.radioButton = radioButton;
    }

    @Override
    public void execute(){
        radioButton.setSelected(true);
        menuState.setShapeType(shapeType);
    }
}

