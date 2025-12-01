package org.example.controller.state;

import org.example.controller.actions.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState currentState;
    private CommandActionListener undoActionListener;
    private CommandActionListener redoActionListener;

    public UndoMachine() {
        currentState = new StateDisableUndoDisableRedo(new LinkedList<>(), new LinkedList<>());
    }

    public void executeRedo() {
        currentState = currentState.redo();
        updateButtons();
    }

    public void executeUndo() {
        currentState = currentState.undo();
        updateButtons();
    }

    public boolean isEnableUndo() {
        return currentState.canUndo();
    }

    public boolean isEnableRedo() {
        return currentState.canRedo();
    }

    public void add(AppAction action) {
        currentState.clearHistory();
        currentState.addAction(action);

        // Пересоздаем состояние с учетом новых списков
        currentState = createAppropriateState();
        updateButtons();
    }

    private UndoRedoState createAppropriateState() {
        LinkedList<AppAction> undoList = currentState.getUndoActivityList();
        LinkedList<AppAction> redoList = currentState.getRedoActivityList();

        if (undoList.isEmpty()) {
            return redoList.isEmpty() ?
                    new StateDisableUndoDisableRedo(undoList, redoList) :
                    new StateDisableUndoEnableRedo(undoList, redoList);
        } else {
            return redoList.isEmpty() ?
                    new StateEnableUndoDisableRedo(undoList, redoList) :
                    new StateEnableUndoEnableRedo(undoList, redoList);
        }
    }

    public void setUndoActionListener(CommandActionListener undoActionListener) {
        this.undoActionListener = undoActionListener;
    }

    public void setRedoActionListener(CommandActionListener redoActionListener) {
        this.redoActionListener = redoActionListener;
    }

    public void updateButtons() {
        if (undoActionListener != null) {
            undoActionListener.setEnabled(isEnableUndo());
        }
        if (redoActionListener != null) {
            redoActionListener.setEnabled(isEnableRedo());
        }
    }
}