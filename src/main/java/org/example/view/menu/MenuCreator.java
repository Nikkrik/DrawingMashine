package org.example.view.menu;

import org.example.controller.Controller;
import org.example.controller.MenuState;
import org.example.model.Model;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MenuCreator {
    private static MenuCreator instance;
    private Controller mainController;
    private MenuState menuState;
    private Model model;

    public static MenuCreator getInstance() {
        if (instance == null) {
            instance = new MenuCreator();
        }
        return instance;
    }

    private MenuCreator() {
        // Не инициализируем mainController здесь
    }

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }
    public void setState(MenuState menuState) {
        this.menuState = menuState;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Меню выбора фигуры
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup shapeGroup = new ButtonGroup();

        JRadioButtonMenuItem rectangleItem = new JRadioButtonMenuItem("Прямоугольник");
        rectangleItem.setSelected(true);
        rectangleItem.addActionListener(e -> {
            if (mainController != null) {
                mainController.setShapeType(ShapeType.RECTANGLE);
            }
        });

        JRadioButtonMenuItem ellipseItem = new JRadioButtonMenuItem("Эллипс");
        ellipseItem.addActionListener(e -> {
            if (mainController != null) {
                mainController.setShapeType(ShapeType.ELLIPSE);
            }
        });

        shapeGroup.add(rectangleItem);
        shapeGroup.add(ellipseItem);
        shapeMenu.add(rectangleItem);
        shapeMenu.add(ellipseItem);

        JMenu colorMenu = new JMenu("Цвет");

        JMenuItem chooseColorItem  = new JMenuItem("Выбрать цвет...");
        chooseColorItem.addActionListener(e -> showColorChooser());

        JMenuItem blackColor  = new JMenuItem("Черный");
        blackColor.addActionListener(e -> setCurrentColor(Color.BLACK));

        JMenuItem redColor  = new JMenuItem("Красный");
        redColor.addActionListener(e -> setCurrentColor(Color.RED));

        JMenuItem blueColor  = new JMenuItem("Синий");
        blueColor.addActionListener(e -> setCurrentColor(Color.BLUE));

        JMenuItem greenColor  = new JMenuItem("Зеленый");
        greenColor.addActionListener(e -> setCurrentColor(Color.GREEN));

        colorMenu.add(chooseColorItem);
        colorMenu.addSeparator();
        colorMenu.add(blackColor);
        colorMenu.add(redColor);
        colorMenu.add(blueColor);
        colorMenu.add(greenColor);

        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup fillGroup = new ButtonGroup();

        JRadioButtonMenuItem fillItem = new JRadioButtonMenuItem("С заливкой");
        fillItem.setSelected(true);
        fillItem.addActionListener(e -> {
            if (mainController != null) {
                mainController.setFillType(FillType.FILL);
            }
        });

        JRadioButtonMenuItem noFillItem = new JRadioButtonMenuItem("Без заливки");
        noFillItem.addActionListener(e -> {
            if (mainController != null) {
                mainController.setFillType(FillType.NO_FILL);
            }
        });

        fillGroup.add(fillItem);
        fillGroup.add(noFillItem);
        fillMenu.add(fillItem);
        fillMenu.add(noFillItem);

        // Меню выбора действия
        JMenu actionMenu = new JMenu("Режим");
        ButtonGroup actionGroup = new ButtonGroup();

        JRadioButtonMenuItem drawItem = new JRadioButtonMenuItem("Рисование");
        drawItem.setSelected(true);
        drawItem.addActionListener(e -> {
            if (mainController != null) {
                mainController.setDrawingAction();
            }
        });

        JRadioButtonMenuItem moveItem = new JRadioButtonMenuItem("Перемещение");
        moveItem.addActionListener(e -> {
            if (mainController != null) {
                mainController.setMovingAction();
            }
        });

        actionGroup.add(drawItem);
        actionGroup.add(moveItem);
        actionMenu.add(drawItem);
        actionMenu.add(moveItem);

        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(fillMenu);
        menuBar.add(actionMenu);

        return menuBar;
    }

    private void showColorChooser(){
        if (mainController != null){
            Color chosenColor = JColorChooser.showDialog(null, "Выберете цвет", mainController.getCurrentColor());
            if(chosenColor != null){
                mainController.setCurrentColor(chosenColor);
            }
        }
    }

    private void setCurrentColor(Color color){
        if (mainController != null){
            mainController.setCurrentColor(color);
        }
    }

    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();

        for(Action x : subMenuItems){
            jToolBar.add(x);
        }
        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems(){
        ArrayList<Action> menuItems = new ArrayList<>();

        URL colorUrl = getClass().getClassLoader().getResource("image/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        JRadioButtonMenuItem rgbButton = new JRadioButtonMenuItem(colorIco);
        AppCommand colorCommand = new SwitchColor(menuState, false, null, rgbButton);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        return menuItems;
    }
}