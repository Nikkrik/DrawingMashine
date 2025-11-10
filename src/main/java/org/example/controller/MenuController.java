package org.example.controller;

import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;

import javax.swing.*;
import java.awt.*;

public class MenuController {
    private static MenuController instance;
    private Controller mainController;

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    private MenuController() {
        // Не инициализируем mainController здесь
    }

    public void setMainController(Controller controller) {
        this.mainController = controller;
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

        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(fillMenu);

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
}