package Derivative;

import Insurance.InsuranceObligations;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Derivative {
    private List<InsuranceObligations> contracts = new ArrayList<>();

    public void addContracts(InsuranceObligations contract) {
        contracts.add(contract);
    }

    public double CalculateTotalCost() {
        double TotalSum = 0.0;
        for (InsuranceObligations contract : contracts) {
            TotalSum += contract.calculateCost();
        }
        return TotalSum;
    }

    public void sortByRiskLevel() {
        contracts.sort((c1, c2) -> Double.compare(c2.getLevelRisk(), c1.getLevelRisk()));
    }

    public List<InsuranceObligations> findContractsInRiskRange(double minRisk, double maxRisk) {
        List<InsuranceObligations> result = new ArrayList<>();
        for (InsuranceObligations contract : contracts) {
            double riskLevel = contract.getLevelRisk();
            if (riskLevel >= minRisk && riskLevel <= maxRisk) {
                result.add(contract);
            }
        }
        return result;
    }

    public List<InsuranceObligations> getContracts() {
        return contracts;
    }

    public boolean removeContractByPolicyNumber(String policyNumber) {
        Iterator<InsuranceObligations> iterator = contracts.iterator();
        while (iterator.hasNext()) {
            InsuranceObligations contract = iterator.next();
            if (contract.getPolicyNumber().equals(policyNumber)) {
                iterator.remove();
                return true; // Знайдено і видалено
            }
        }
        return false; // Не знайдено
    }
}
