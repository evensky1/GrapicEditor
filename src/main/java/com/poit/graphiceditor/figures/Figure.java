package com.poit.graphiceditor.figures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.poit.graphiceditor.figures.impl.*;
import javafx.scene.canvas.GraphicsContext;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public abstract class Figure {

    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    protected int lineWidth;
    protected double[] rgb = new double[3];

    protected Figure(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    protected Figure() {
    }

    @JsonIgnore
    public double getRed() {
        return rgb[0];
    }

    @JsonIgnore
    public double getGreen() {
        return rgb[1];
    }

    @JsonIgnore
    public double getBlue() {
        return rgb[2];
    }

    public double[] getRgb() {
        return rgb;
    }

    public void setRgb(double[] rgb) {
        this.rgb = rgb;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public abstract void draw(GraphicsContext graphicsContext);

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

}

