package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.controller.Controller;

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
        controller.setFillType(fill ?
                org.example.model.shape.fill.FillType.FILL :
                org.example.model.shape.fill.FillType.NO_FILL);
    }
}