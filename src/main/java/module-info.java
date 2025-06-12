module com.example.alanyadesktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.example.alanyadesktop to javafx.fxml;
    exports com.example.alanyadesktop;
    exports com.example.alanyadesktop.controller;
    exports com.example.alanyadesktop.model;
    exports com.example.alanyadesktop.service;


    opens com.example.alanyadesktop.controller to javafx.fxml;
}