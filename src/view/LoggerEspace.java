package view;

import javafx.scene.control.TextArea;

public class LoggerEspace extends TextArea {
    public LoggerEspace() {
        setEditable(false);
        setPrefHeight(80);
        setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");
    }
}
