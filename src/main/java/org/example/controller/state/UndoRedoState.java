package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public abstract class UndoRedoState {
    private static final int MAX_UNDO = 50;
    private final LinkedList<AppAction> undoActivityList;
    private final LinkedList<AppAction> redoActivityList;

    protected UndoRedoState(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        this.undoActivityList = undoActivityList;
        this.redoActivityList = redoActivity;
    }

    public abstract UndoRedoState undo();
    public abstract UndoRedoState redo();

    public void clearHistory() {
        redoActivityList.clear();
    }

    public void clearAll() {
        undoActivityList.clear();
        redoActivityList.clear();
    }

    public void addAction(AppAction action) {
        if (undoActivityList.size() >= MAX_UNDO) {
            undoActivityList.removeFirst(); // Удаляем самое старое действие
        }
        undoActivityList.addLast(action);
    }

    public LinkedList<AppAction> getUndoActivityList() {
        return undoActivityList;
    }

    public LinkedList<AppAction> getRedoActivityList() {
        return redoActivityList;
    }

    public boolean canUndo() {
        return !undoActivityList.isEmpty();
    }

    public boolean canRedo() {
        return !redoActivityList.isEmpty();
    }
}