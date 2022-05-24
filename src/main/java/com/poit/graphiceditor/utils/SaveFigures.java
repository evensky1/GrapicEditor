package com.poit.graphiceditor.utils;

import com.poit.graphiceditor.figures.Figure;

import java.util.ArrayList;

public class SaveFigures {

    private final ArrayList<Figure> figures = new ArrayList<>();

    public SaveFigures(ArrayList<Figure> figures) {
        this.figures.addAll(figures);
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }
}
