package PatternCommand;

import Derivative.Derivative;
import Insurance.InsuranceObligations;
import org.example.kursova.ResultWindow;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayCommand implements Command{
    private Connection connection;
    private ResultWindow resultWindow;

    public DisplayCommand(Connection connection,ResultWindow resultWindow) {
        this.connection = connection;
        this.resultWindow = resultWindow;
    }

    @Override
    public void execute(Object... args) {

    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {

    }

    @Override
    public void execute() {
        try {
            // Запит до бази даних для отримання страхових зобов'язань
            String query = "SELECT * FROM contracts";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    ResultWindow.displayResult("Дериватив не має страхових зобов'язань.");
                } else {
                    ResultWindow.displayResult("Страхові зобов'язання в деривативі:\n");
                    do {
                        String policyNumber = resultSet.getString("policyNumber");
                        String policyType = resultSet.getString("policyType");
                        double levelRisk = resultSet.getDouble("levelRisk");
                        double value = resultSet.getDouble("value");
                        ResultWindow.displayResult(STR."Номер полісу: \{policyNumber}\nТип полісу: \{policyType}\nРизик: \{levelRisk}\nВартість: \{value}\n");
                    } while (resultSet.next());
                }
            }
        } catch (SQLException e) {
            ResultWindow.displayResult("Помилка при взаємодії з базою даних: " + e.getMessage());
        }
    }
}
