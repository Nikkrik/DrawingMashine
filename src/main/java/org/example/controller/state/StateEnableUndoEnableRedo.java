package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoEnableRedo extends UndoRedoState {

    protected StateEnableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast();
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }

        if (undoActivityList.isEmpty()) {
            if (!redoActivityList.isEmpty()) {
                return new StateDisableUndoEnableRedo(undoActivityList, redoActivityList);
            } else {
                return new StateDisableUndoDisableRedo(undoActivityList, redoActivityList);
            }
        } else {
            if (!redoActivityList.isEmpty()) {
                return this;
            } else {
                return new StateEnableUndoDisableRedo(undoActivityList, redoActivityList);
            }
        }
    }

    @Override
    public UndoRedoState redo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = redoActivityList.pollLast();
        if (action != null) {
            undoActivityList.add(action);
            action.execute();
        }

        if (redoActivityList.isEmpty()) {
            if (!undoActivityList.isEmpty()) {
                return new StateEnableUndoDisableRedo(undoActivityList, redoActivityList);
            } else {
                return new StateDisableUndoDisableRedo(undoActivityList, redoActivityList);
            }
        } else {
            if (!undoActivityList.isEmpty()) {
                return this;
            } else {
                return new StateDisableUndoEnableRedo(undoActivityList, redoActivityList);
            }
        }
    }
}