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
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();

        if (redoActivityList.isEmpty()) {
            return this;
        }

        AppAction action = redoActivityList.removeLast();
        if (action != null) {
            undoActivityList.add(action);
            action.execute();
        }

        if (redoActivityList.isEmpty()) {
            if (undoActivityList.isEmpty()) {
                return new StateDisableUndoDisableRedo(undoActivityList, redoActivityList);
            } else {
                return new StateEnableUndoDisableRedo(undoActivityList, redoActivityList);
            }
        } else {
            if (undoActivityList.isEmpty()) {
                return this;
            } else {
                return new StateEnableUndoEnableRedo(undoActivityList, redoActivityList);
            }
        }
    }
}