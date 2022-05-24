package com.poit.graphiceditor.figures.impl;

import com.poit.graphiceditor.figures.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Figure {
    public Ellipse(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public Ellipse() {
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.beginPath();
        graphicsContext.arc((x1 + x2) / 2.0,
                (y1 + y2) / 2.0,
                Math.abs(x2 - x1) / 2.0,
                Math.abs(y2 - y1) / 2.0,
                0,
                360);

        graphicsContext.stroke();
    }
}
