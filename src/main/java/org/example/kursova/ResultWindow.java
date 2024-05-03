package org.example.kursova;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultWindow {
    private static Stage stage;
    private static ResultController controller;

    public static void displayResult(String result) {
        try {
            if (stage == null) { // Перевірка, чи вікно ще не створено
                FXMLLoader loader = new FXMLLoader(ResultWindow.class.getResource("result.fxml"));
                Parent root = loader.load();
                controller = loader.getController(); // Отримання контролера

                stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Results");
            }
            controller.appendResult(result); // Додавання тексту

            if (!stage.isShowing()) { // Перевірка, чи вікно ще не відображено
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
