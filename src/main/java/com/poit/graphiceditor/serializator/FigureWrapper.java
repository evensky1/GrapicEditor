package com.poit.graphiceditor.serializator;

import com.poit.graphiceditor.figures.Figure;

import java.util.ArrayList;

public class FigureWrapper {
    private ArrayList<Figure> figures;

    public FigureWrapper(ArrayList<Figure> figures) {
        this.figures = figures;
    }

    public FigureWrapper() {}

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public void setFigures(ArrayList<Figure> figures) {
        this.figures = figures;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }
}
