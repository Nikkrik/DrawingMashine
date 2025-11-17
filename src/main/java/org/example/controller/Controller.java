package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeCreator;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private AppAction currentAction;
    private MenuState menuState;
    private ShapeCreator shapeCreator;
    private ActionDraw actionDraw; // Сохраняем ссылку на ActionDraw

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {
                instance = new Controller();
            }
            return instance;
        }
    }

    private Controller() {
        model = new Model();
        menuState = new MenuState();

        // Инициализируем и настраиваем ShapeCreator
        shapeCreator = ShapeCreator.getInstance();
        shapeCreator.configure(menuState);

        // Создаем ActionDraw один раз
        MyShape sampleShape = shapeCreator.createShape();
        actionDraw = new ActionDraw(model, sampleShape);
        currentAction = actionDraw;

        panel = new MyPanel(this);
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        // Устанавливаем связь между контроллерами после полной инициализации
        MenuController menuController = MenuController.getInstance();
        menuController.setMainController(this);
        frame.setMenu();
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

    public Color getCurrentColor() {
        return menuState.getColor();
    }

    public void setDrawingAction() {
        // Обновляем образец фигуры в существующем ActionDraw
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
            // Обновляем образец фигуры в существующем ActionDraw
            shapeCreator.configure(menuState);
            MyShape sampleShape = shapeCreator.createShape();
            actionDraw.setSampleShape(sampleShape);
        }
        // Для ActionMove обновление не требуется
    }

    public void startDrawing(Point2D p) {
        currentAction.mousePressed(p);
    }

    public void updateDrawing(Point2D p) {
        currentAction.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}