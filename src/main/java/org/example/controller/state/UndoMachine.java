package org.example.controller.state;

import org.example.controller.actions.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    private CommandActionListener undoActionListener;
    private CommandActionListener redoActionListener;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
        updateButtons();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
        updateButtons();
    }

    public boolean isEnableUndo() {
        return undoRedoState.getUndoActivityList().size() > 0;
    }

    public boolean isEnableRedo() {
        return undoRedoState.getRedoActivityList().size() > 0;
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);

        // Определяем новое состояние после добавления действия
        LinkedList<AppAction> undoList = undoRedoState.getUndoActivityList();
        LinkedList<AppAction> redoList = undoRedoState.getRedoActivityList();

        if (undoList.size() > 0) {
            if (redoList.size() > 0) {
                undoRedoState = new StateEnableUndoEnableRedo(undoList, redoList);
            } else {
                undoRedoState = new StateEnableUndoDisableRedo(undoList, redoList);
            }
        } else {
            if (redoList.size() > 0) {
                undoRedoState = new StateDisableUndoEnableRedo(undoList, redoList);
            } else {
                undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
            }
        }
        updateButtons();
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