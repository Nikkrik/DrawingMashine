package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.model.shape.fill.FillType;


import javax.swing.*;

public class SwitchFill implements AppCommand {
    private MenuState menuState;
    private FillType fillType;
    private JRadioButtonMenuItem radioButton;

    public SwitchFill(MenuState menuState, FillType fillType, JRadioButtonMenuItem radioButton) {
        this.menuState = menuState;
        this.fillType = fillType;
        this.radioButton = radioButton;
    }

    @Override
    public void execute(){
        radioButton.setSelected(true);
        menuState.setFill(fillType == FillType.FILL);
    }
}
