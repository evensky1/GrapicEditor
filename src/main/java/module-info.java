module com.poit.graphiceditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires bson4jackson;
    requires com.fasterxml.jackson.databind;
    requires  com.fasterxml.jackson.dataformat.xml;
    requires org.json;

    opens com.poit.graphiceditor.controller to javafx.fxml;
    exports com.poit.graphiceditor;
    exports com.poit.graphiceditor.figures to com.fasterxml.jackson.databind, com.fasterxml.jackson.dataformat.xml;
    exports com.poit.graphiceditor.figures.impl to com.fasterxml.jackson.databind, com.fasterxml.jackson.dataformat.xml;
    exports com.poit.graphiceditor.serializator to com.fasterxml.jackson.databind, com.fasterxml.jackson.dataformat.xml;
    exports com.poit.graphiceditor.utils to com.fasterxml.jackson.databind, com.fasterxml.jackson.dataformat.xml;
}