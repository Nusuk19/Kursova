package PatternCommand;

import org.example.kursova.ResultWindow;

public class HelpCommand implements Command {
    @Override
    public void execute(Object... args) {

    }

    @Override
    public void execute(String policyNumber, String policyType, double value, double levelRisk) {

    }

    @Override
    public void execute() {
        ResultWindow.displayResult("Довідка:");
        ResultWindow.displayResult("0. Help -Пояснення команд");
        ResultWindow.displayResult("1. Display -  Показати усі страхівки");
        ResultWindow.displayResult("2. AddContract - додавання страхівки до деративи");
        ResultWindow.displayResult("3. DeleteContract - видалення страхівки з деративи");
        ResultWindow.displayResult("4. SaveToDatabase -  Зберегти дані у базу даних");
        ResultWindow.displayResult("5. Exit - заврешення роботи програми");
        ResultWindow.displayResult("6. CalculateTotalCost - Обчислення загальної ціни");
        ResultWindow.displayResult("7. SearchByRisk - вводимо діапазон ризику(кінці включно)");
        ResultWindow.displayResult("8. SortByRisk - сортуємо страхівки по спаданню ризиків\n");
    }
}
