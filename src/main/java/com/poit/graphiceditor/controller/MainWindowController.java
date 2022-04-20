package com.poit.graphiceditor.controller;

import com.poit.graphiceditor.factory.FigureFactory;
import com.poit.graphiceditor.figures.Figure;
import com.poit.graphiceditor.serializator.BsonSerializator;
import com.poit.graphiceditor.serializator.FigureWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ChoiceBox<Integer> widthBox;

    @FXML
    private ChoiceBox<String> shapeBox;

    private Figure figure;

    private ArrayList<Figure> figures;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        widthBox.setValue(1);
        shapeBox.setValue("Line");
        figures = new ArrayList<>();
    }

    @FXML
    protected void onMousePressed(MouseEvent event) {
        var figureFactory = new FigureFactory();
        figure = figureFactory.getFigureViaName(shapeBox.getValue());
        figure.setX1(event.getX());
        figure.setY1(event.getY());
    }

    @FXML
    protected void onMouseReleased(MouseEvent event) {
        var gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(widthBox.getValue());
        gc.setStroke(colorPicker.getValue());
        figure.setX2(event.getX());
        figure.setY2(event.getY());
        figure.setLineWidth(widthBox.getValue());
        figure.setRgb(new double[]{
                colorPicker.getValue().getRed(),
                colorPicker.getValue().getGreen(),
                colorPicker.getValue().getBlue()
        });

        figure.draw(gc);
        figures.add(figure);
    }

    @FXML
    public void serializeAction(ActionEvent actionEvent) {
        BsonSerializator serializator = new BsonSerializator();
        try {
            FigureWrapper figureWrapper = new FigureWrapper(figures);
            serializator.serialize(figureWrapper, BsonSerializator.PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deserializeAction(ActionEvent actionEvent) {
        BsonSerializator serializator = new BsonSerializator();
        try {
            FigureWrapper tempFigures = serializator.deserialize(BsonSerializator.PATH);
            figures.addAll(tempFigures.getFigures());
            drawAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearCanvas() {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawAll() {
        var gc = canvas.getGraphicsContext2D();
        figures.forEach(currentFigure -> {
            gc.setLineWidth(currentFigure.getLineWidth());
            gc.setStroke(Color.color(currentFigure.getRed(), currentFigure.getGreen(), currentFigure.getBlue()));
            currentFigure.draw(gc);
        });
    }

    @FXML
    public void onClear(ActionEvent actionEvent) {
        figures.clear();
        clearCanvas();
    }
}
