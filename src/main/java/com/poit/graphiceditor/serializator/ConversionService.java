package com.poit.graphiceditor.serializator;

public interface ConversionService {
    void serializeWithConversion(FigureWrapper figureWrapper, String path);
    FigureWrapper deserializeWithConversion(String path);
}
