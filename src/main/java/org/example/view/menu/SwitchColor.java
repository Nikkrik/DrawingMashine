package org.example.view.menu;

import org.example.controller.MenuState;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand {
    private MenuState menuState;
    private boolean useDefault;
    private Color defaultColor;
    private JRadioButtonMenuItem radioButton;

    public SwitchColor(MenuState menuState, boolean useDefault, Color defaultColor, JRadioButtonMenuItem radioButton) {
        this.menuState = menuState;
        this.useDefault = useDefault;
        this.defaultColor = defaultColor;
        this.radioButton = radioButton;
    }

    @Override
    public void execute() {
        radioButton.setSelected(!useDefault);
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        menuState.setColor(color);
    }
}


