package com.poit.graphiceditor.factory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.undercouch.bson4jackson.BsonFactory;


public class MapperFactory {
    public ObjectMapper getFactory(String name) {
        if (name.equalsIgnoreCase("json")) {
            return new ObjectMapper(new JsonFactory());
        } else if (name.equalsIgnoreCase("bson")) {
            return new ObjectMapper(new BsonFactory());
        } else if (name.equalsIgnoreCase("xml")) {
            return new XmlMapper();
        } else {
            return null;
        }
    }
}
