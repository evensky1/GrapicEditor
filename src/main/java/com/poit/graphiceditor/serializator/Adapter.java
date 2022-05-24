package com.poit.graphiceditor.serializator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Adapter implements ConversionService {

    private Class<?> blueprint;
    private Method serializeToXml = null;
    private Method convertXmlToJson = null;
    private Method deserializeFromXml = null;
    private Method convertJsonToXml = null;

    Adapter(Class<?> blueprint) {
        this.blueprint = blueprint;
        try {
            serializeToXml = blueprint.getMethod("serializeToXml", FigureWrapper.class);
            convertXmlToJson = blueprint.getMethod("convertXmlToJson", String.class, String.class);
            deserializeFromXml = blueprint.getMethod("deserializeFromXml", String.class);
            convertJsonToXml = blueprint.getMethod("convertJsonToXml", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serializeWithConversion(FigureWrapper figureWrapper, String path) {
        String xml = "";
        try {
            xml = (String) serializeToXml.invoke(blueprint.getDeclaredConstructor().newInstance(), figureWrapper);
            convertXmlToJson.invoke(blueprint.getDeclaredConstructor().newInstance(), xml, path);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FigureWrapper deserializeWithConversion(String path) {
        String xml = "";
        try {
            xml = (String) convertJsonToXml.invoke(blueprint.getDeclaredConstructor().newInstance(), path);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }

        try {
            return (FigureWrapper) deserializeFromXml.invoke(blueprint.getDeclaredConstructor().newInstance(), xml);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            return new FigureWrapper();
        }
    }
}
