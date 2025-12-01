package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        if (getUndoActivityList().isEmpty()) return this;

        AppAction action = getUndoActivityList().removeLast();
        getRedoActivityList().addLast(action);
        action.unexecute();

        boolean canUndo = !getUndoActivityList().isEmpty();
        boolean canRedo = !getRedoActivityList().isEmpty();

        if (canUndo && canRedo) return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        if (canRedo) return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        return this;
    }

    @Override
    public UndoRedoState redo() {
        return this; // Redo недоступен
    }
}