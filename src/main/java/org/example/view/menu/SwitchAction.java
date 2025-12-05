package org.example.view.menu;

import org.example.controller.Controller;

import javax.swing.*;

public class SwitchAction implements AppCommand {
    private final Controller controller;
    private final boolean isDrawingMode;
    private final JRadioButtonMenuItem radioButton;

    public SwitchAction(Controller controller, boolean isDrawingMode, JRadioButtonMenuItem radioButton) {
        this.controller = controller;
        this.isDrawingMode = isDrawingMode;
        this.radioButton = radioButton;
    }

    @Override
    public void execute() {
        if (controller != null) {
            if (isDrawingMode) {
                controller.setDrawingAction();
            } else {
                controller.setMovingAction();
            }
        }

        if (radioButton != null) {
            radioButton.setSelected(true);
        }
    }
}
