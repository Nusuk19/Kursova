package org.example.kursova;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ResultController {
    @FXML
    private TextArea resultTextArea;

    public void appendResult(String result) {
        resultTextArea.appendText(result + "\n"); // Додавання тексту та перенесення на новий рядок
    }

}