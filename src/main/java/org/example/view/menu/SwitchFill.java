package org.example.view.menu;

import org.example.controller.Controller;
import org.example.controller.MenuState;
import org.example.model.shape.fill.FillType;

import javax.swing.*;

public class SwitchFill implements AppCommand {
    private final MenuState menuState;
    private final Controller controller;
    private final boolean fill;
    private final JRadioButtonMenuItem radioButton;

    public SwitchFill(MenuState menuState, Controller controller, boolean fill, JRadioButtonMenuItem radioButton) {
        this.menuState = menuState;
        this.controller = controller;
        this.fill = fill;
        this.radioButton = radioButton;
    }

    @Override
    public void execute() {
        menuState.setFill(fill);

        if (controller != null) {
            controller.setFillType(fill ? FillType.FILL : FillType.NO_FILL);
        }

        if (radioButton != null) {
            radioButton.setSelected(true);
        }
    }
}