package org.example.view.menu;

import org.example.controller.Controller;
import org.example.controller.MenuState;
import org.example.model.shape.ShapeType;

import javax.swing.*;

public class SwitchShape implements AppCommand {
    private final MenuState menuState;
    private final Controller controller;
    private final ShapeType shapeType;
    private final JRadioButtonMenuItem radioButton;

    public SwitchShape(MenuState menuState, Controller controller, ShapeType shapeType, JRadioButtonMenuItem radioButton) {
        this.menuState = menuState;
        this.controller = controller;
        this.shapeType = shapeType;
        this.radioButton = radioButton;
    }

    @Override
    public void execute() {
        menuState.setShapeType(shapeType);

        if (controller != null) {
            controller.setShapeType(shapeType);
        }

        if (radioButton != null) {
            radioButton.setSelected(true);
        }
    }
}