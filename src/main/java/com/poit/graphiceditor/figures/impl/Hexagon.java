package com.poit.graphiceditor.figures.impl;

import com.poit.graphiceditor.figures.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Hexagon extends Figure {

    public Hexagon(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public Hexagon() {}

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.beginPath();
        graphicsContext.moveTo((x1 + x2) / 2, y1);
        graphicsContext.lineTo(x2, y1 + (Math.abs(y1 - y2) / 3));
        graphicsContext.lineTo(x2, y2 - (Math.abs(y1 - y2) / 3));
        graphicsContext.lineTo((x1 + x2) / 2, y2);
        graphicsContext.lineTo(x1, y2 - (Math.abs(y1 - y2) / 3));
        graphicsContext.lineTo(x1, y1 + (Math.abs(y1 - y2) / 3));
        graphicsContext.closePath();
        graphicsContext.stroke();
    }
}
