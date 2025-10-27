package org.example.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenameObservers {
    private List<ModelObserver> observers =  new CopyOnWriteArrayList<>();

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.onModelChanged();
        }
    }

}
