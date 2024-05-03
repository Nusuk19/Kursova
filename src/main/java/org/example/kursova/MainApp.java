package org.example.kursova;

import File.LoadFromDatabase;
import File.SaveToDatabase;
import Insurance.InsuranceObligations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import PatternCommand.*;
import Derivative.Derivative;
import org.example.kursova.CommandManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainApp extends Application {

    private static MainApp instance;
    private CommandManager commandManager;
    private Derivative derivative;
    private Connection connection;
    private ResultWindow resultWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Insurance Management System");
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 600, 400));
        DeveloperController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.show();

        commandManager = new CommandManager();
        derivative = new Derivative();
        resultWindow = new ResultWindow(); // Створення об'єкту ResultWindow
        // Підключення до бази даних
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance_db", "root", "1324576809Aa");
            LoadFromDatabase dataDatabaseHandler = new LoadFromDatabase();
            SaveToDatabase saveToDatabase = new SaveToDatabase();

            // Передача з'єднання до бази даних в об'єкти роботи з базою даних
            List<InsuranceObligations> initialContracts = dataDatabaseHandler.loadFromDatabase(connection);
            for (InsuranceObligations contract : initialContracts) {
                derivative.addContracts(contract);
            }

            // Додавання команд до менеджера команд
            commandManager.addCommand("addContract", new AddConractCommand(connection));
            commandManager.addCommand("calculateTotalCost", new CalculateTotalCostContactsCommand(derivative));
            commandManager.addCommand("deleteContract", new DeleteContractCommand(connection));
            commandManager.addCommand("displayContract",new DisplayCommand(connection,resultWindow));
            commandManager.addCommand("exit", new ExitCommand());
            commandManager.addCommand("help", new HelpCommand());
            commandManager.addCommand("saveToFile",new SaveToFileCommand(derivative,saveToDatabase,connection));
            commandManager.addCommand("searchByRisk", new SearchByRiskCommand(derivative));
            commandManager.addCommand("sortByRisk", new SortByRiskCommand(derivative,connection));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MainApp getInstance() {
        return instance;
    }

    // Метод для отримання менеджера команд
    public CommandManager getCommandManager() {
        return commandManager;
    }

    // Метод для закриття з'єднання з базою даних
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
