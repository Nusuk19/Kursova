package PatternCommand;

import Derivative.Derivative;
import File.LoadFromDatabase;
import Insurance.InsuranceObligations;
import org.example.kursova.ResultWindow;

import java.sql.Connection;
import java.util.List;

public class SortByRiskCommand implements Command{
    private Derivative derivative;
    private Connection connection;

    public SortByRiskCommand(Derivative derivative, Connection connection) {
        this.derivative = derivative;
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
        LoadFromDatabase dataDatabaseHandler = new LoadFromDatabase();
        List<InsuranceObligations> initialContracts = dataDatabaseHandler.loadFromDatabase(connection);
        for (InsuranceObligations contract : initialContracts) {
            derivative.addContracts(contract);
        }
        derivative.sortByRiskLevel();
        ResultWindow.displayResult("Зобов'язання відсортовані за рівнем ризику.\n");
        List<InsuranceObligations> contracts = derivative.getContracts();
        if (contracts.isEmpty()) {
            ResultWindow.displayResult("Дериватив не має страхових зобов'язань.\n");
        } else {
            ResultWindow.displayResult("Страхові зобов'язання в деривативі:\n");
            for (InsuranceObligations contract : contracts) {
                ResultWindow.displayResult(STR."Номер полісу: \{contract.getPolicyNumber()}\nТип полісу \{contract.getPolicyType()}\nРизик: \{contract.getLevelRisk()}\nВартість: \{contract.getValue()}\n");
            }
        }
    }
}

