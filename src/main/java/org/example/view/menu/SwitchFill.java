package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.controller.Controller;

import static org.example.model.shape.fill.FillType.FILL;
import static org.example.model.shape.fill.FillType.NO_FILL;

public class SwitchFill implements AppCommand {
    private MenuState menuState;
    private boolean fill;

    public SwitchFill(MenuState menuState, boolean fill) {
        this.menuState = menuState;
        this.fill = fill;
    }
    @Override
    public void execute() {
        menuState.setFill(fill);
        Controller controller = Controller.getInstance();
        controller.setFillType(fill ? FILL : NO_FILL);
    }
}