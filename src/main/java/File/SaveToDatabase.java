package File;

import Insurance.InsuranceObligations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveToDatabase {
    public static void saveToDatabase(Connection connection, InsuranceObligations contract) throws SQLException {
        // Перевірка наявності контракту з вказаним policyNumber
        if (contractExists(connection, contract.getPolicyNumber())) {
            System.out.println("Контракт з таким номером полісу вже існує у базі даних.");
            return; // Вийти з методу, не додаваючи новий контракт
        }
        else {
            // Вставка нового контракту
            String insertQuery = "INSERT INTO contracts (policyNumber, policyType, value, levelRisk) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, contract.getPolicyNumber());
                insertStatement.setString(2, contract.getPolicyType());
                insertStatement.setDouble(3, contract.getValue());
                insertStatement.setDouble(4, contract.getLevelRisk());
                insertStatement.executeUpdate();
                System.out.println("Новий контракт успішно додано до бази даних.");
            }
        }

    }

    // Метод для перевірки наявності контракту з вказаним policyNumber
    private static boolean contractExists(Connection connection, String policyNumber) throws SQLException {
        String query = "SELECT * FROM contracts WHERE policyNumber = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, policyNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Повертає true, якщо контракт знайдено
            }
        }
    }

}
