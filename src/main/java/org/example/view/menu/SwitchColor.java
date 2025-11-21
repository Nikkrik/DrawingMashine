package org.example.view.menu;

import org.example.controller.MenuState;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand {
    private MenuState menuState;
    private boolean useDefault;
    private Color defaultColor;

    public SwitchColor(MenuState menuState, boolean useDefault, Color defaultColor) {
        this.menuState = menuState;
        this.useDefault = useDefault;
        this.defaultColor = defaultColor;
    }

    @Override
    public void execute() {
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        if (color != null) {
            menuState.setColor(color);
        }
    }
}


