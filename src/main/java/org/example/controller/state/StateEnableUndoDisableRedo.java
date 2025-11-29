package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();

        if (undoActivityList.isEmpty()) {
            return this;
        }

        AppAction action = undoActivityList.removeLast();
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }

        if (undoActivityList.isEmpty()) {
            if (redoActivityList.isEmpty()) {
                return new StateDisableUndoDisableRedo(undoActivityList, redoActivityList);
            } else {
                return new StateDisableUndoEnableRedo(undoActivityList, redoActivityList);
            }
        } else {
            if (redoActivityList.isEmpty()) {
                return this;
            } else {
                return new StateEnableUndoEnableRedo(undoActivityList, redoActivityList);
            }
        }
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}