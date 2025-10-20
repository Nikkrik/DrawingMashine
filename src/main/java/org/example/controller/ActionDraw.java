package org.example.controller;


import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw {
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionDraw(Model model) {
        this.model = model;
        this.firstPoint = new Point2D.Double();
        this.secondPoint = new Point2D.Double();
    }

    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    public void stretchShape(Point point) {
        secondPoint.setLocation(point);
    }



}
