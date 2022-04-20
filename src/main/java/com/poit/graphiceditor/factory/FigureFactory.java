package com.poit.graphiceditor.factory;

import com.poit.graphiceditor.figures.Figure;
import com.poit.graphiceditor.figures.impl.*;

public class FigureFactory {
    public Figure getFigureViaName(String figureName) {
        if (figureName.equalsIgnoreCase("line")) {
            return new Line();
        } else if (figureName.equalsIgnoreCase("ellipse")) {
            return new Ellipse();
        } else if (figureName.equalsIgnoreCase("hexagon")) {
            return new Hexagon();
        } else if (figureName.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (figureName.equalsIgnoreCase("rhombus")) {
            return new Rhombus();
        } else if (figureName.equalsIgnoreCase("triangle")) {
            return new Triangle();
        } else {
            return null;
        }
    }
}
