package com.poit.graphiceditor.figures.impl;

import com.poit.graphiceditor.figures.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure {
    public Line(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public Line() {
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.strokeLine(x1, y1, x2, y2);
    }
}
