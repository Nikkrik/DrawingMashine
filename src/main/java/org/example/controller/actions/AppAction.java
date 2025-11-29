package org.example.controller.actions;

import java.awt.geom.Point2D;

public interface AppAction {
    void mousePressed(Point2D point);
    void mouseDragged(Point2D point);
    void mouseReleased(Point2D point);
    void execute();
    void unexecute();
    AppAction cloneAction();
}
