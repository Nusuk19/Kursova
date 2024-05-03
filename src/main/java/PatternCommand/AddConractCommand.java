package PatternCommand;

import File.SaveToDatabase;
import Insurance.InsuranceObligations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.kursova.ResultWindow;

import java.sql.Connection;
import java.sql.SQLException;

public class AddConractCommand implements Command {
    private Connection connection;
    private String policyNumber;
    private String policyType;
    private double value;
    private double levelRisk;
    private static final Logger logger = LogManager.getLogger(AddConractCommand.class);

    public AddConractCommand(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void execute(Object... args) {

    }

    @Override
       public void execute(String policyNumber, String policyType, double value, double levelRisk) {
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.value = value;
        this.levelRisk = levelRisk;

        InsuranceObligations contract = new InsuranceObligations(policyNumber, policyType, value, levelRisk);

        // Збереження даних у базі даних
        try {
            SaveToDatabase.saveToDatabase(connection, contract);
            ResultWindow.displayResult("Страхове зобов'язання додане до бази даних.");
            logger.info("Страхове зобов'язання додане до бази даних.\n");
        } catch (SQLException e) {
            ResultWindow.displayResult("Помилка при збереженні даних у базі даних: " + e.getMessage()+"\n");
            logger.error("Помилка при збереженні даних у базі даних: " + e.getMessage());
        }
    }

    @Override
    public void execute() {

    }
}
