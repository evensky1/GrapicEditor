package com.poit.graphiceditor.factory;

import com.poit.graphiceditor.figures.Figure;
import com.poit.graphiceditor.figures.impl.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class FigureFactory {

    private HashMap<String, Class<? extends Figure>> newFigures;

    public FigureFactory() {
        newFigures = new HashMap<>();
    }

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
        } else if (newFigures.containsKey(figureName)) {
            try {
                return newFigures.get(figureName).getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException |
                    InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public void addNewFigure(Class<? extends Figure> cls) {
        newFigures.put(cls.getSimpleName(), cls);
    }
}
