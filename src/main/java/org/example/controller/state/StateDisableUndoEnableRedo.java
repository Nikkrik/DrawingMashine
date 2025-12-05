package org.example.controller.state;

import org.example.controller.actions.AppAction;
import java.util.LinkedList;

public class StateDisableUndoEnableRedo extends UndoRedoState{

    protected StateDisableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        return this;
    }

    @Override
    public UndoRedoState redo() {
        if (getRedoActivityList().isEmpty()) return this;

        AppAction action = getRedoActivityList().removeLast();
        getUndoActivityList().addLast(action);
        action.execute();

        boolean canUndo = !getUndoActivityList().isEmpty();
        boolean canRedo = !getRedoActivityList().isEmpty();

        if (canUndo && canRedo) return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        if (canUndo) return new StateEnableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
        return this;
    }
}