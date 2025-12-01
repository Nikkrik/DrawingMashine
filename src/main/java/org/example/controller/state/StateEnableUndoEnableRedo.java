package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoEnableRedo extends UndoRedoState {

    protected StateEnableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        if (getUndoActivityList().isEmpty()) return this;

        AppAction action = getUndoActivityList().removeLast();
        getRedoActivityList().addLast(action);
        action.unexecute();

        return createNewState();
    }

    @Override
    public UndoRedoState redo() {
        if (getRedoActivityList().isEmpty()) return this;

        AppAction action = getRedoActivityList().removeLast();
        getUndoActivityList().addLast(action);
        action.execute();

        return createNewState();
    }

    private UndoRedoState createNewState() {
        boolean canUndo = !getUndoActivityList().isEmpty();
        boolean canRedo = !getRedoActivityList().isEmpty();

        if (canUndo && canRedo) return this;
        if (canUndo) return new StateEnableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
        if (canRedo) return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        return new StateDisableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
    }
}