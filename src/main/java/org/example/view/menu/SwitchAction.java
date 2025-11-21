package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.controller.action.AppAction;

public class SwitchAction implements AppCommand{
    private AppAction action;
    private MenuState menuState;

    public SwitchAction(AppAction action, MenuState menuState) {
        this.action = action;
        this.menuState = menuState;
    }

    @Override
    public void execute(){}
}
