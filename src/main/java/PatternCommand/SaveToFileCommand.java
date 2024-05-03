package PatternCommand;

import File.SaveToDatabase;
import Insurance.InsuranceObligations;
import Derivative.Derivative;
import org.example.kursova.ResultWindow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SaveToFileCommand implements Command {
    private Derivative derivative;
    private SaveToDatabase saveToDatabase;
    private Connection connection;

    public SaveToFileCommand(Derivative derivative, SaveToDatabase saveToDatabase, Connection connection) {
        this.derivative = derivative;
        this.saveToDatabase = saveToDatabase;
        this.connection = connection;
    }

    @Override
    public void execute(Object... args) {

    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {

    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            List<InsuranceObligations> saveContracts = derivative.getContracts();
            if (saveContracts.isEmpty()) {
                throw new RuntimeException("No insurance obligations to save.");
            }
            for (InsuranceObligations contract : saveContracts) {
                if (!checkDuplicate(contract)) {
                    saveToDatabase.saveToDatabase(connection, contract); // Passing the connection to the database
                }
            }
            ResultWindow.displayResult("Страхові забов'язання збережено у базу даних.\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkDuplicate(InsuranceObligations contract) {
        try {
            String query = "SELECT * FROM contracts WHERE policyNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, contract.getPolicyNumber());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Повертаємо true, якщо контракт знайдено
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while checking for duplicate contracts: " + e.getMessage());
        }
    }


}
