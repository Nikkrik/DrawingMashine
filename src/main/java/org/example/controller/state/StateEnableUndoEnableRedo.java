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
                return new StateEnableUndoDisableRedo(undoActivityList, redoActivityList);
            } else {
                return this;
            }
        }
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
                return new StateDisableUndoEnableRedo(undoActivityList, redoActivityList);
            } else {
                return this;
            }
        }
    }
}