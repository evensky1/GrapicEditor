package com.poit.graphiceditor.serializator;

import com.poit.graphiceditor.factory.MapperFactory;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class SerializationService {

    public static final String PATH = "C:\\Users\\fromt\\IdeaSpace\\SimpleGrapicEditor\\src\\main\\resources\\datafolder";

    private final MapperFactory serializationFactory = new MapperFactory();
    private ConversionService conversionService = null;

    public SerializationService() {
        try {
        URL classUrl = new URL("file:/C:/Users/fromt/IdeaSpace/SimpleGrapicEditor/target/classes/");
        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl}, getClass().getClassLoader());
            Class<?> blueprint = classLoader
                    .loadClass("com.poit.graphiceditor.serializator.Lab6Service");

            conversionService = new Adapter(blueprint);
        } catch (ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void serialize(FigureWrapper figureWrapper, String path, String method) throws IOException {
        var fileOutputStream = new FileOutputStream(path + "\\dataraw." + method.toLowerCase(Locale.ROOT));
        var mapper = serializationFactory.getFactory(method);
        mapper.writeValue(fileOutputStream, figureWrapper);
    }

    public FigureWrapper deserialize(String path, String method) throws IOException {
        if (!path.equals(method.toLowerCase(Locale.ROOT))) {
            var fileInputStream = new FileInputStream(path + "\\dataraw." + method.toLowerCase(Locale.ROOT));
            var mapper = serializationFactory.getFactory(method);
            return mapper.readValue(fileInputStream, FigureWrapper.class);
        } else {
            return null;
        }
    }

    public void serializeWithConversion(FigureWrapper figureWrapper, String path) throws IOException {
//        var fileOutputStream = new FileOutputStream(path + "\\conversions\\result.json");
//        var mapper = serializationFactory.getFactory("XML");
//        var tempXmlHolder = mapper.writeValueAsString(figureWrapper);
//        fileOutputStream.write(XML.toJSONObject(tempXmlHolder).toString().getBytes(StandardCharsets.UTF_8));
        conversionService.serializeWithConversion(figureWrapper, path);
    }

    public FigureWrapper deserializeWithConversion(String path) throws IOException {
//        var fileInputStream = new FileInputStream(path + "\\conversions\\result.json");
//        var mapper = serializationFactory.getFactory("XML");
//        var tempJsonHolder = new JSONObject(new String(fileInputStream.readAllBytes()));
//        return mapper.readValue(XML.toString(tempJsonHolder), FigureWrapper.class);
        return conversionService.deserializeWithConversion(path);
    }
}
