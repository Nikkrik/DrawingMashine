package org.example.view.menu;

import org.example.controller.Controller;

public class SwitchAction implements AppCommand {
    private Controller controller;
    private boolean isDrawingMode;
    public SwitchAction(Controller controller, boolean isDrawingMode) {
        this.controller = controller;
        this.isDrawingMode = isDrawingMode;
    }
    @Override
    public void execute() {
        if (isDrawingMode) {
            controller.setDrawingAction();
        } else {
            controller.setMovingAction();
        }
    }
}