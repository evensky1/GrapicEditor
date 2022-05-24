package com.poit.graphiceditor.figures.impl;

import com.poit.graphiceditor.figures.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {

    public Rectangle(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public Rectangle() {}

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.beginPath();
        graphicsContext.moveTo(x1, y1);
        graphicsContext.lineTo(x1, y2);
        graphicsContext.lineTo(x2, y2);
        graphicsContext.lineTo(x2, y1);
        graphicsContext.closePath();
        graphicsContext.stroke();
    }
}
