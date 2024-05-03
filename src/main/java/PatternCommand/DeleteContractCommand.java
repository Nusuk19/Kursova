package PatternCommand;

import Derivative.Derivative;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.kursova.ResultWindow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteContractCommand implements Command {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(DeleteContractCommand.class);

    public DeleteContractCommand(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void execute(Object... args) {
        if (args.length == 1 && args[0] instanceof String) {
            String policyNumberToDelete = (String) args[0];
            deleteContract(policyNumberToDelete);
        } else {
            ResultWindow.displayResult("Неправильні аргументи для виконання команди.\n");
        }
    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {
        // Не використовується в цьому випадку
    }

    @Override
    public void execute() {
        // Не використовується в цьому випадку
    }

    private void deleteContract(String policyNumberToDelete) {
        try {
            // Запит до бази даних для видалення страхового зобов'язання
            String deleteQuery = "DELETE FROM contracts WHERE policyNumber = ?";
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.setString(1, policyNumberToDelete);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    ResultWindow.displayResult("Страхове зобов'язання з номером " + policyNumberToDelete + " було видалено.\n");
                    logger.info("Страхове зобов'язання видалене з бази даних.");
                } else {
                    ResultWindow.displayResult("Страхове зобов'язання з номером " + policyNumberToDelete + " не було знайдено.\n");
                    logger.info("Страхове зобов'язання не знайдене в базі даних.");
                }
            }
        } catch (SQLException e) {
            ResultWindow.displayResult(STR."Помилка при видаленні страхового зобов'язання з бази даних: \{e.getMessage()}\n");
            logger.error("Помилка при видаленні страхового зобов'язання з бази даних: " + e.getMessage());
        }
    }
}

