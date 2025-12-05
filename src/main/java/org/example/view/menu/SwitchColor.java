package org.example.view.menu;

import org.example.controller.Controller;
import org.example.controller.MenuState;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand {
    private final MenuState menuState;
    private final boolean useDefault;
    private final Color defaultColor;
    private final JRadioButtonMenuItem radioButton;
    private final Controller controller;

    public SwitchColor(MenuState menuState, boolean useDefault, Color defaultColor, JRadioButtonMenuItem radioButton, Controller controller) {
        this.menuState = menuState;
        this.useDefault = useDefault;
        this.defaultColor = defaultColor;
        this.radioButton = radioButton;
        this.controller = controller;
    }

    @Override
    public void execute() {
        if (radioButton != null) {
            radioButton.setSelected(!useDefault);
        }

        Color color;
        if (useDefault) {
            color = defaultColor;
        } else {
            Color currentColor = menuState.getColor();
            color = JColorChooser.showDialog(null, "Выбор цвета",
                    currentColor != null ? currentColor : Color.BLACK);

            if (color == null){
                return;
            }
        }

        menuState.setColor(color);

        if (controller != null){
            controller.setCurrentColor(color);
        }
    }
}


