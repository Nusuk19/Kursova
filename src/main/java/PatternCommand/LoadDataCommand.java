package PatternCommand;

import Derivative.Derivative;
import File.LoadFromDatabase;
import Insurance.InsuranceObligations;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class LoadDataCommand implements Command {
    private Derivative derivative;
    private LoadFromDatabase loadFromDatabase;
    private Connection connection; // Додайте змінну для зберігання з'єднання з базою даних

    public LoadDataCommand(Derivative derivative, LoadFromDatabase loadFromDatabase, Connection connection) {
        this.derivative = derivative;
        this.loadFromDatabase = loadFromDatabase;
        this.connection = connection; // Збереження з'єднання з базою даних
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
        System.out.print("Введіть шлях до файлу: ");
        String filename = scanner.nextLine();
        List<InsuranceObligations> loadedContracts = loadFromDatabase.loadFromDatabase(connection);
        if (loadedContracts != null) {
            for (InsuranceObligations contract : loadedContracts) {
                derivative.addContracts(contract);
            }
        }
    }
}
