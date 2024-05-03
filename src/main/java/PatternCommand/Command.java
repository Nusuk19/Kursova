package PatternCommand;

public interface Command {
    void execute(Object... args);

    void execute(String policyNumber, String policyType, double value, double levelRisk);

    void execute();
}
