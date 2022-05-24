package com.poit.graphiceditor.serializator;

import com.poit.graphiceditor.figures.Figure;
import com.poit.graphiceditor.utils.SaveFigures;

import java.util.ArrayList;

public class FigureWrapper {
    private ArrayList<Figure> figures;

    public FigureWrapper(ArrayList<Figure> figures) {
        this.figures = figures;
    }

    public FigureWrapper() {
        figures = new ArrayList<>();
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public void setFigures(ArrayList<Figure> figures) {
        this.figures = figures;
    }

    public void add(Figure figure) {
        figures.add(figure);
    }

    public SaveFigures save() {
        return new SaveFigures(figures);
    }

    public void restore(SaveFigures save) {
        if (save != null) {
            figures = save.getFigures();
        } else {
            figures = null;
        }
    }

    public void clear() {
        figures.clear();
    }
}
