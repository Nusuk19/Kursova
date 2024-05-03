package org.example.kursova;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import Developer.Developer;
import Derivative.Derivative;
import PatternCommand.*;
import javafx.scene.control.TextField;

public class DeveloperController {

    // Оголошення кнопок з FXML
    @FXML
    private Button AddContract;
    @FXML
    private Button CalculateTotalCost;
    @FXML
    private Button DeleteContract;
    @FXML
    private Button Display;
    @FXML
    private Button Exit;
    @FXML
    private Button Help;
    @FXML
    private Button SaveToDatabase;
    @FXML
    private Button SearchByRisk;
    @FXML
    private Button SortByRisk;
    @FXML
    private TextField totalCostTextField;

    @FXML
    private TextField policyNumberField;
    @FXML
    private TextField policyTypeField;
    @FXML
    private TextField levelRiskField;
    @FXML
    private TextField valueField;

    public String getPolicyNumber() {
        return policyNumberField.getText();
    }
    public String getPolicyType() {
        return policyTypeField.getText();
    }
    public String getLevelRisk(){
        return levelRiskField.getText();
    }
    public String getValue(){
        return valueField.getText();
    }

    private Developer developer; // Додано визначення змінної developer
    private MainApp mainApp; // Додано посилання на головний клас додатка

    // Метод для ініціалізації об'єкта Developer
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setTotalCostTextField(TextField totalCostTextField) {
        this.totalCostTextField = totalCostTextField;
    }

    // Метод для ініціалізації посилання на головний клас додатка
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    // Метод для обробки події натискання на кнопку "Розрахувати загальну вартість"
    @FXML
    private void calculateTotalCost(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("calculateTotalCost");
        }
    }

    @FXML
    private void DisplayContract(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("displayContract");
        }
    }

    // Метод для обробки події натискання на кнопку "Додати контракт"
    @FXML
    private void handleAddContractButton(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("addContract");
        }
    }

    @FXML
    private void handleHelpButton(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("help");
        }
    }

    @FXML
    private void SortByRiskButton(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("sortByRisk");
        }
    }

    @FXML
    private void ExitButton(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("exit");
        }
    }

    @FXML
    public void addContractButtonClicked() {
        String policyNumber = policyNumberField.getText();
        String policyType = policyTypeField.getText();
        String valueString = valueField.getText();
        String levelRiskString = levelRiskField.getText();

        // Перевірка на те, що рядки не є порожніми
        if (!valueString.isEmpty() && !levelRiskString.isEmpty()) {
            // Конвертування значень у числа
            double value = Double.parseDouble(valueString);
            double levelRisk = Double.parseDouble(levelRiskString);

            // Викликати метод для виконання команди "addContract", передавши отримані значення
            MainApp.getInstance().getCommandManager().executeCommand1("addContract", policyNumber, policyType, value, levelRisk);
        } else {
            System.out.println("Поля вводу не можуть бути порожніми!");
        }
    }

    @FXML
    private void handleDeleteContractButton(ActionEvent event) {
        if (mainApp != null) {
            String policyNumberToDelete = policyNumberField.getText();
            mainApp.getCommandManager().executeCommand2("deleteContract", policyNumberToDelete);
        }
    }

    @FXML
    private void SaveToDatabaseButton(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("saveToFile");
        }
    }

    @FXML
    private void SearchByRisk(ActionEvent event) {
        if (mainApp != null) {
            mainApp.getCommandManager().executeCommand("searchByRisk");
        }
    }

}
