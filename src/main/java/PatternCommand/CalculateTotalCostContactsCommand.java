package PatternCommand;

import Derivative.Derivative;
import org.example.kursova.ResultWindow;

public class CalculateTotalCostContactsCommand implements Command{
    private Derivative derivative;

    public CalculateTotalCostContactsCommand(Derivative derivative) {
        this.derivative = derivative;
    }

    @Override
    public void execute(Object... args) {

    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {

    }

    @Override
    public void execute() {
        double totalCost = derivative.CalculateTotalCost();
        System.out.println("Загальна вартість деривативу: " + totalCost);
        ResultWindow.displayResult(STR."Загальна вартість деривативу: \{totalCost}\n");
    }
}

