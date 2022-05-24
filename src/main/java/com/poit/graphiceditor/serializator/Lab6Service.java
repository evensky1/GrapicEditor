package com.poit.graphiceditor.serializator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Lab6Service {

    public String serializeToXml(FigureWrapper figureWrapper) throws JsonProcessingException {
        var mapper = new XmlMapper();
        return mapper.writeValueAsString(figureWrapper);
    }

    public void convertXmlToJson(String xml, String path) throws IOException {
        var fileOutputStream = new FileOutputStream(path + "\\conversions\\result.json");
        fileOutputStream.write(XML.toJSONObject(xml).toString().getBytes(StandardCharsets.UTF_8));
    }

    public String convertJsonToXml(String path) throws IOException {
        var fileInputStream = new FileInputStream(path + "\\conversions\\result.json");
        var tempJsonHolder = new JSONObject(new String(fileInputStream.readAllBytes()));
        return XML.toString(tempJsonHolder);
    }

    public FigureWrapper deserializeFromXml(String xml) throws JsonProcessingException {
        var mapper = new XmlMapper();
        return mapper.readValue(xml, FigureWrapper.class);
    }
}
