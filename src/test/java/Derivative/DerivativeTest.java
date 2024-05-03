package Derivative;

import Insurance.InsuranceObligations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DerivativeTest {

    @Test
    void testAddContracts() {
        Derivative derivative = new Derivative();
        InsuranceObligations contract = new InsuranceObligations("123", "Type1", 1000, 0.1);
        derivative.addContracts(contract);
        List<InsuranceObligations> contracts = derivative.getContracts();
        assertTrue(contracts.contains(contract));
    }

    @Test
    void testCalculateTotalCost() {
        Derivative derivative = new Derivative();
        InsuranceObligations contract1 = new InsuranceObligations("123", "Type1", 1000, 0.1);
        InsuranceObligations contract2 = new InsuranceObligations("456", "Type2", 2000, 0.2);
        derivative.addContracts(contract1);
        derivative.addContracts(contract2);
        assertEquals(500, derivative.CalculateTotalCost());
    }

    @Test
    void testSortByRiskLevel() {
        Derivative derivative = new Derivative();
        InsuranceObligations contract1 = new InsuranceObligations("123", "Type1", 1000, 0.1);
        InsuranceObligations contract2 = new InsuranceObligations("456", "Type2", 2000, 0.2);
        derivative.addContracts(contract1);
        derivative.addContracts(contract2);
        derivative.sortByRiskLevel();
        List<InsuranceObligations> contracts = derivative.getContracts();
        assertTrue(contracts.get(0).getLevelRisk() >= contracts.get(1).getLevelRisk());
    }

    @Test
    void testFindContractsInRiskRange() {
        Derivative derivative = new Derivative();
        InsuranceObligations contract1 = new InsuranceObligations("123", "Type1", 1000, 0.1);
        InsuranceObligations contract2 = new InsuranceObligations("456", "Type2", 2000, 0.2);
        derivative.addContracts(contract1);
        derivative.addContracts(contract2);
        List<InsuranceObligations> result = derivative.findContractsInRiskRange(0, 0.15);
        assertTrue(result.contains(contract1));
        assertFalse(result.contains(contract2));
    }

    @Test
    void testRemoveContractByPolicyNumber() {
        Derivative derivative = new Derivative();
        InsuranceObligations contract1 = new InsuranceObligations("123", "Type1", 1000, 0.1);
        InsuranceObligations contract2 = new InsuranceObligations("456", "Type2", 2000, 0.2);
        derivative.addContracts(contract1);
        derivative.addContracts(contract2);
        assertTrue(derivative.removeContractByPolicyNumber("123"));
        assertFalse(derivative.removeContractByPolicyNumber("789"));
    }
}
