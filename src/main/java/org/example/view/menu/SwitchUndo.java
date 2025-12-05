package org.example.view.menu;

import org.example.controller.state.UndoMachine;

public class SwitchUndo implements AppCommand{
    private final UndoMachine undoMachine;
    public SwitchUndo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    @Override
    public void execute(){
        undoMachine.executeUndo();
        undoMachine.updateButtons();
    }
}
