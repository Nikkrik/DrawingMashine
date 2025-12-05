package org.example.view.menu;

import org.example.controller.Controller;
import org.example.controller.MenuState;
import org.example.controller.state.UndoMachine;
import org.example.model.shape.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MenuCreator {
    private static MenuCreator instance;
    private Controller mainController;
    private MenuState menuState;
    private UndoMachine undoMachine;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private CommandActionListener undoToolbarAction;
    private CommandActionListener redoToolbarAction;

    public static MenuCreator getInstance() {
        if (instance == null) {
            instance = new MenuCreator();
        }
        return instance;
    }

    private MenuCreator() {
    }

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    public void setState(MenuState menuState) {
        this.menuState = menuState;
    }

    public void setUndoMachine(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Меню выбора фигуры
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup shapeGroup = new ButtonGroup();

        JRadioButtonMenuItem rectangleItem = new JRadioButtonMenuItem("Прямоугольник");
        rectangleItem.setSelected(true);
        CommandActionListener rectangleActionListener = new CommandActionListener(new SwitchShape(menuState, mainController, ShapeType.RECTANGLE, rectangleItem));
        rectangleItem.addActionListener(rectangleActionListener);
        shapeGroup.add(rectangleItem);

        JRadioButtonMenuItem ellipseItem = new JRadioButtonMenuItem("Эллипс");
        CommandActionListener ellipseActionListener = new CommandActionListener(new SwitchShape(menuState, mainController, ShapeType.ELLIPSE, ellipseItem));
        ellipseItem.addActionListener(ellipseActionListener);
        shapeGroup.add(ellipseItem);

        shapeMenu.add(rectangleItem);
        shapeMenu.add(ellipseItem);

        JMenu colorMenu = new JMenu("Цвет");

        JMenuItem chooseColorItem = new JMenuItem("Выбрать цвет...");
        CommandActionListener chooseColorActionListener = new CommandActionListener(new SwitchColor(menuState, false, null, null, mainController));
        chooseColorItem.addActionListener(chooseColorActionListener);

        JMenuItem blackColor = new JMenuItem("Черный");
        CommandActionListener blackColorActionListener = new CommandActionListener(new SwitchColor(menuState, true, Color.BLACK, null, mainController));
        blackColor.addActionListener(blackColorActionListener);

        JMenuItem redColor = new JMenuItem("Красный");
        CommandActionListener redColorActionListener = new CommandActionListener(new SwitchColor(menuState, true, Color.RED, null, mainController));
        redColor.addActionListener(redColorActionListener);

        JMenuItem blueColor = new JMenuItem("Синий");
        CommandActionListener blueColorActionListener = new CommandActionListener(new SwitchColor(menuState, true, Color.BLUE, null, mainController));
        blueColor.addActionListener(blueColorActionListener);

        JMenuItem greenColor = new JMenuItem("Зеленый");
        CommandActionListener greenColorActionListener = new CommandActionListener(new SwitchColor(menuState, true, Color.GREEN, null, mainController));
        greenColor.addActionListener(greenColorActionListener);

        colorMenu.add(chooseColorItem);
        colorMenu.addSeparator();
        colorMenu.add(blackColor);
        colorMenu.add(redColor);
        colorMenu.add(blueColor);
        colorMenu.add(greenColor);

        // Меню заливки
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup fillGroup = new ButtonGroup();

        JRadioButtonMenuItem fillItem = new JRadioButtonMenuItem("С заливкой");
        fillItem.setSelected(true);
        CommandActionListener fillActionListener = new CommandActionListener(new SwitchFill(menuState, mainController, true, fillItem));
        fillItem.addActionListener(fillActionListener);
        fillGroup.add(fillItem);

        JRadioButtonMenuItem noFillItem = new JRadioButtonMenuItem("Без заливки");
        CommandActionListener noFillActionListener = new CommandActionListener(new SwitchFill(menuState, mainController, false, noFillItem));
        noFillItem.addActionListener(noFillActionListener);
        fillGroup.add(noFillItem);

        fillMenu.add(fillItem);
        fillMenu.add(noFillItem);

        // Меню режима
        JMenu actionMenu = new JMenu("Режим");
        ButtonGroup actionGroup = new ButtonGroup();

        JRadioButtonMenuItem drawItem = new JRadioButtonMenuItem("Рисование");
        drawItem.setSelected(true);
        CommandActionListener drawActionListener = new CommandActionListener(new SwitchAction(mainController, true, drawItem));
        drawItem.addActionListener(drawActionListener);
        actionGroup.add(drawItem);

        JRadioButtonMenuItem moveItem = new JRadioButtonMenuItem("Перемещение");
        CommandActionListener moveActionListener = new CommandActionListener(new SwitchAction(mainController, false, moveItem));
        moveItem.addActionListener(moveActionListener);
        actionGroup.add(moveItem);

        actionMenu.add(drawItem);
        actionMenu.add(moveItem);

        // Меню Edit с Undo/Redo
        JMenu editMenu = new JMenu("Правка");

        // Undo
        undoMenuItem = new JMenuItem("Отменить");
        CommandActionListener undoActionListener = new CommandActionListener(new SwitchUndo(undoMachine));
        undoMenuItem.addActionListener(undoActionListener);
        undoMenuItem.setEnabled(false); // Изначально выключена

        // Redo
        redoMenuItem = new JMenuItem("Повторить");
        CommandActionListener redoActionListener = new CommandActionListener(new SwitchRedo(undoMachine));
        redoMenuItem.addActionListener(redoActionListener);
        redoMenuItem.setEnabled(false); // Изначально выключена

        // Передаем слушатели в UndoMachine
        undoMachine.setUndoActionListener(undoActionListener);
        undoMachine.setRedoActionListener(redoActionListener);

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);

        // Добавляем все меню в панель меню
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(fillMenu);
        menuBar.add(actionMenu);
        menuBar.add(editMenu);

        return menuBar;
    }

    public void updateMenuButtons() {
        if (undoMenuItem != null) {
            undoMenuItem.setEnabled(undoMachine != null && undoMachine.isEnableUndo());
        }
        if (redoMenuItem != null) {
            redoMenuItem.setEnabled(undoMachine != null && undoMachine.isEnableRedo());
        }
        if (undoToolbarAction != null) {
            undoToolbarAction.setEnabled(undoMachine != null && undoMachine.isEnableUndo());
        }
        if (redoToolbarAction != null) {
            redoToolbarAction.setEnabled(undoMachine != null && undoMachine.isEnableRedo());
        }
    }

    public JToolBar createToolBar() {
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();

        for (Action x : subMenuItems) {
            jToolBar.add(x);
        }
        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        // Кнопка выбора цвета
        URL colorUrl = getClass().getClassLoader().getResource("image/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(menuState, false, null, null, mainController);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        // Кнопка режима рисования
        URL drawUrl = getClass().getClassLoader().getResource("image/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawToolCommand = new SwitchAction(mainController, true, null);
        CommandActionListener drawToolAction = new CommandActionListener("Рисование", drawIco, drawToolCommand);
        menuItems.add(drawToolAction);

        // Кнопка режима перемещения
        URL moveUrl = getClass().getClassLoader().getResource("image/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveToolCommand = new SwitchAction(mainController, false, null);
        CommandActionListener moveToolAction = new CommandActionListener("Перемещение", moveIco, moveToolCommand);
        menuItems.add(moveToolAction);

        // Кнопка заливки
        URL fillUrl = getClass().getClassLoader().getResource("image/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillToolCommand = new SwitchFill(menuState, mainController, true, null);
        CommandActionListener fillToolAction = new CommandActionListener("С заливкой", fillIco, fillToolCommand);
        menuItems.add(fillToolAction);

        // Кнопка без заливки
        URL noFillUrl = getClass().getClassLoader().getResource("image/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillToolCommand = new SwitchFill(menuState, mainController, false, null);
        CommandActionListener noFillToolAction = new CommandActionListener("Без заливки", noFillIco, noFillToolCommand);
        menuItems.add(noFillToolAction);

        // Кнопка Undo
        URL undoToolbarUrl = getClass().getClassLoader().getResource("image/undo_16x16.png");
        ImageIcon undoToolbarIco = undoToolbarUrl == null ? null : new ImageIcon(undoToolbarUrl);
        AppCommand undoToolbarCommand = new SwitchUndo(undoMachine);
        undoToolbarAction = new CommandActionListener("Отменить", undoToolbarIco, undoToolbarCommand);
        undoToolbarAction.setEnabled(false); // Изначально выключена
        menuItems.add(undoToolbarAction);

        // Кнопка Redo
        URL redoToolbarUrl = getClass().getClassLoader().getResource("image/redo_16x16.png");
        ImageIcon redoToolbarIco = redoToolbarUrl == null ? null : new ImageIcon(redoToolbarUrl);
        AppCommand redoToolbarCommand = new SwitchRedo(undoMachine);
        redoToolbarAction = new CommandActionListener("Повторить", redoToolbarIco, redoToolbarCommand);
        redoToolbarAction.setEnabled(false); // Изначально выключена
        menuItems.add(redoToolbarAction);

        // Передаем слушатели в UndoMachine
        if (undoMachine != null) {
            undoMachine.setUndoActionListener(undoToolbarAction);
            undoMachine.setRedoActionListener(redoToolbarAction);
        }

        return menuItems;
    }

    public void setModel() {
    }
}