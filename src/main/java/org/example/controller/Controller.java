package org.example.controller;

import org.example.controller.actions.ActionDraw;
import org.example.controller.actions.ActionMove;
import org.example.controller.actions.AppAction;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeCreator;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;
import org.example.view.menu.MenuCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private AppAction currentAction;
    private final MenuState menuState;
    private final ShapeCreator shapeCreator;
    private final ActionDraw actionDraw;
    private final UndoMachine undoMachine;
    private AppAction pendingAction;
    private final MenuCreator menuCreator;


    public static void getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {
                instance = new Controller();
            }
        }
    }

    private Controller() {
        model = new Model();
        menuState = new MenuState();
        undoMachine = new UndoMachine();

        shapeCreator = ShapeCreator.getInstance();
        shapeCreator.configure(menuState);

        MyShape sampleShape = shapeCreator.createShape();
        actionDraw = new ActionDraw(model, sampleShape);
        currentAction = actionDraw;

        MyPanel panel = new MyPanel(this);
        model.addObserver(panel);

        MyFrame frame = new MyFrame();
        frame.setPanel(panel);

        menuCreator = MenuCreator.getInstance();
        menuCreator.setState(menuState);
        menuCreator.setModel();
        menuCreator.setMainController(this);
        menuCreator.setUndoMachine(undoMachine);

        JToolBar toolBar = menuCreator.createToolBar();
        toolBar.setOrientation(JToolBar.VERTICAL);
        frame.add(toolBar, BorderLayout.WEST);
        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.revalidate();
    }

    public void setShapeType(ShapeType type) {
        this.menuState.setShapeType(type);
        updateCurrentAction();
    }

    public void setCurrentColor(Color color) {
        this.menuState.setColor(color);
        updateCurrentAction();
    }

    public void setFillType(FillType fillType) {
        this.menuState.setFill(fillType == FillType.FILL);
        updateCurrentAction();
    }


    public void setDrawingAction() {
        shapeCreator.configure(menuState);
        MyShape sampleShape = shapeCreator.createShape();
        actionDraw.setSampleShape(sampleShape);
        currentAction = actionDraw;
    }

    public void setMovingAction() {
        currentAction = new ActionMove(model);
    }

    private void updateCurrentAction() {
        if (currentAction instanceof ActionDraw) {
            shapeCreator.configure(menuState);
            MyShape sampleShape = shapeCreator.createShape();
            actionDraw.setSampleShape(sampleShape);
        }
    }

    public void startDrawing(Point2D p) {
        currentAction.mousePressed(p);
        if (currentAction instanceof ActionDraw) {
            pendingAction = currentAction.cloneAction();
        }
    }

    public void updateDrawing(Point2D p) {
        currentAction.mouseDragged(p);
    }

    public void finishDrawing(Point2D p) {
        if (currentAction instanceof ActionMove) {
            pendingAction = currentAction.cloneAction();
        }

        if (pendingAction != null) {
            undoMachine.add(pendingAction);
            pendingAction = null;
            updateUndoRedoButtons();
        }
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public void updateUndoRedoButtons() {
        undoMachine.updateButtons();
        if (menuCreator != null) {
            menuCreator.updateMenuButtons();
        }
    }
}