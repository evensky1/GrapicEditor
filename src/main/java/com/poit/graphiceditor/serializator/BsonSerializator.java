package com.poit.graphiceditor.serializator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.poit.graphiceditor.figures.Figure;
import de.undercouch.bson4jackson.BsonFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BsonSerializator {

    public static final String PATH = "C:\\Users\\fromt\\IdeaSpace\\SimpleGrapicEditor\\src\\main\\resources\\com\\poit\\graphiceditor\\datafolder\\dataraw.json";

    public void serialize(FigureWrapper figureWrapper, String path) throws IOException {
        var fileOutputStream = new FileOutputStream(path);
        var bsonFactory = new BsonFactory();
        var mapper = new ObjectMapper(bsonFactory);
        mapper.writeValue(fileOutputStream, figureWrapper);
    }

    public FigureWrapper deserialize(String path) throws IOException {
        var fileInputStream = new FileInputStream(path);
        var bsonFactory = new BsonFactory();
        var mapper = new ObjectMapper(bsonFactory);
        return mapper.readValue(fileInputStream, FigureWrapper.class);
    }
}
