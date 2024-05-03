package File;

import Insurance.InsuranceObligations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadFromDatabase {
    private static final Logger logger = LogManager.getLogger(LoadFromDatabase.class);

    public List<InsuranceObligations> loadFromDatabase(Connection connection) {
        List<InsuranceObligations> contracts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT policyNumber,policyType,value,levelRisk  FROM contracts");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Отримання даних з результатів запиту та створення об'єктів InsuranceObligations
                String policyNumber = resultSet.getString("policyNumber");
                String policyType = resultSet.getString("policyType");
                double value = resultSet.getDouble("value");
                double levelRisk = resultSet.getDouble("levelRisk");

                InsuranceObligations contract = new InsuranceObligations(policyNumber, policyType, value, levelRisk);
                contracts.add(contract);
            }
            logger.info("Успішне завантаження даних з бази даних");
        } catch (SQLException e) {
            logger.error("Помилка:", e);
        }

        return contracts;
    }
}