package org.example.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenameObservers {
    private final List<ModelObserver> observers =  new CopyOnWriteArrayList<>();

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.onModelChanged();
        }
    }
}
