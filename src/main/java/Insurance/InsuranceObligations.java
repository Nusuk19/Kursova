package Insurance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class InsuranceObligations {
    private String policyNumber;
    private String policyType;
    protected double value;
    protected double LevelRisk;

    private static final Logger logger= LogManager.getLogger(InsuranceObligations.class);

    public InsuranceObligations(String policyNumber, String policyType, double value, double levelRisk) {
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.value = value;
        this.LevelRisk = levelRisk;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public double getValue() {
        return value;
    }

    public double calculateCost() {
        // Розрахунок вартості страхового зобов'язання
        return value * LevelRisk;
    }

    public double getLevelRisk() {
        return LevelRisk;
    }

    @Override
    public String toString() {
        return "InsuranceObligations{" +
                "policyNumber='" + policyNumber + '\'' +
                ", policyType='" + policyType + '\'' +
                ", value=" + value +
                ", LevelRisk=" + LevelRisk +
                '}';
    }

    public static InsuranceObligations fromString(String line) {

        String[] parts = line.split(",");
        if (parts.length == 4) {
            try {
                String policyNumber = parts[0].trim().split("=")[1].trim().replaceAll("'", "");
                String policyType = parts[1].trim().split("=")[1].trim().replaceAll("'", "");
                double value = Double.parseDouble(parts[2].trim().split("=")[1].trim());
                double levelRisk = Double.parseDouble(parts[3].trim().split("=")[1].trim().replaceAll("}", ""));
                logger.info("Дані успішно розбиті по частинах і записані");
                return new InsuranceObligations(policyNumber, policyType, value, levelRisk);
            } catch (NumberFormatException e) {
                logger.error("Помилка:", e);
            }
        }
        return null;
    }
}

