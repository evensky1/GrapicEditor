package com.poit.graphiceditor.controller;

import com.poit.graphiceditor.factory.FigureFactory;
import com.poit.graphiceditor.figures.Figure;
import com.poit.graphiceditor.serializator.FigureWrapper;
import com.poit.graphiceditor.utils.FiguresKeeper;
import com.poit.graphiceditor.serializator.SerializationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private ChoiceBox<String> serializatorPicker;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ChoiceBox<Integer> widthBox;

    @FXML
    private ChoiceBox<String> shapeBox;

    @FXML
    private TextField pluginNameInput;

    private Figure figure;

    private FigureFactory figureFactory;

    private SerializationService serializationService;

    private FiguresKeeper figuresKeeper;

    private FigureWrapper figureWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        widthBox.setValue(1);
        shapeBox.setValue("Line");
        serializatorPicker.setValue("BSON");
        figureWrapper = new FigureWrapper();
        figureFactory = new FigureFactory();
        figuresKeeper = new FiguresKeeper();
        serializationService = new SerializationService();
    }

    @FXML
    protected void onMousePressed(MouseEvent event) {
        canvas.getGraphicsContext2D().save();
        figure = figureFactory.getFigureViaName(shapeBox.getValue());
        figuresKeeper.setSaveFigures(figureWrapper.save());
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
        figureWrapper.add(figure);
    }

    @FXML
    public void serializeAction() {
        try {
            serializationService.serialize(figureWrapper, SerializationService.PATH, serializatorPicker.getValue());
        } catch (IOException e) {
            errorInformer("Serialization failed", "Something goes really wrong");
        }
    }

    @FXML
    public void deserializeAction() {
        try {
            figureWrapper = serializationService.deserialize(SerializationService.PATH, serializatorPicker.getValue());
            drawAll();
        } catch (IOException e) {
            errorInformer("Deserialization failed", "Something goes really wrong");
        }
    }

    private void clearCanvas() {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawAll() {
        var gc = canvas.getGraphicsContext2D();
        figureWrapper.getFigures().forEach(currentFigure -> {
            gc.setLineWidth(currentFigure.getLineWidth());
            gc.setStroke(Color.color(currentFigure.getRed(), currentFigure.getGreen(), currentFigure.getBlue()));
            currentFigure.draw(gc);
        });
    }

    @FXML
    public void onClear(ActionEvent actionEvent) {
        figureWrapper.clear();
        clearCanvas();
    }

    @FXML
    public void onLoadFigureClick(ActionEvent event) throws MalformedURLException {
        URL classUrl = new URL("file:/C:/Users/fromt/IdeaSpace/SimpleGrapicEditor/target/classes/");
        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl}, getClass().getClassLoader());
        try {
            Class<? extends Figure> figureBlueprint = classLoader
                    .loadClass("com.poit.graphiceditor.figures.impl." + pluginNameInput.getText())
                    .asSubclass(Figure.class);
            shapeBox.getItems().add(figureBlueprint.getSimpleName());
            figureFactory.addNewFigure(figureBlueprint);
        } catch (ClassNotFoundException e) {
            errorInformer("Plugin was not load", "Plugin was not found, try another name");
        }
    }

    public void onConversionSerialization() {
        try {
            serializationService.serializeWithConversion(figureWrapper, SerializationService.PATH);
        } catch (IOException e) {
            errorInformer("Serialization failed", "Something goes really wrong");
        }
    }

    public void onConversionDeserialization() {
        try {
            figureWrapper = serializationService.deserializeWithConversion(SerializationService.PATH);
            drawAll();
        } catch (IOException e) {
            errorInformer("Deserialization failed", "Something goes really wrong");
        }
    }

    private void errorInformer(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void onRollBackAction() {
        clearCanvas();
        figureWrapper.restore(figuresKeeper.getSaveFigures());
        if (figureWrapper.getFigures() != null) {
            drawAll();
        }
    }
}
