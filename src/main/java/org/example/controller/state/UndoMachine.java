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
        return !currentState.getUndoActivityList().isEmpty();
    }

    public boolean isEnableRedo() {
        return !currentState.getRedoActivityList().isEmpty();
    }

    public void add(AppAction action) {
        if (action == null) return;

        currentState.clearHistory();
        currentState.addAction(action);

        // Пересоздаем состояние
        currentState = createAppropriateState();
        updateButtons();
    }

    public void clearHistory() {
        currentState.getUndoActivityList().clear();
        currentState.getRedoActivityList().clear();
        currentState = new StateDisableUndoDisableRedo(new LinkedList<>(), new LinkedList<>());
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
        if (undoActionListener != null) {
            undoActionListener.setEnabled(isEnableUndo());
        }
    }

    public void setRedoActionListener(CommandActionListener redoActionListener) {
        this.redoActionListener = redoActionListener;
        if (redoActionListener != null) {
            redoActionListener.setEnabled(isEnableRedo());
        }
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