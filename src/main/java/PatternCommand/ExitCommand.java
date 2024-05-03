package PatternCommand;

public class ExitCommand implements Command{
    @Override
    public void execute(Object... args) {

    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {

    }

    @Override
    public void execute() {
        System.out.println("Завершення роботи програми.");
        System.exit(0);
    }
}
