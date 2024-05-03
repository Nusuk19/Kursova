package PatternCommand;

import Derivative.Derivative;
import Insurance.InsuranceObligations;
import org.example.kursova.ResultWindow;

import javax.swing.*;
import java.util.List;

public class SearchByRiskCommand implements Command {
    private Derivative derivative;

    public SearchByRiskCommand(Derivative derivative) {
        this.derivative = derivative;
    }

    @Override
    public void execute(Object... args) {
        // Не реалізовано для цієї команди
    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {
        // Не реалізовано для цієї команди
    }

    @Override
    public void execute() {
        double minRisk = Double.parseDouble(JOptionPane.showInputDialog(null, "Введіть мінімальний ризик:"));
        double maxRisk = Double.parseDouble(JOptionPane.showInputDialog(null, "Введіть максимальний ризик:"));

        List<InsuranceObligations> result = derivative.findContractsInRiskRange(minRisk, maxRisk);

        if (!result.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("Результати пошуку:\n");
            for (InsuranceObligations contract : result) {
                stringBuilder.append("Номер Полісу: ").append(contract.getPolicyNumber()).append(", Тип полісу: ")
                        .append(contract.getPolicyType()).append(", Ризик: ").append(contract.getLevelRisk())
                        .append(", Вартість: ").append(contract.calculateCost()).append("\n");
            }
            ResultWindow.displayResult(stringBuilder.toString());
        } else {
            ResultWindow.displayResult("Немає результатів для заданих параметрів.");
        }
    }
}
