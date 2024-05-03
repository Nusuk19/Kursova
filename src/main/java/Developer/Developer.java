package Developer;

import PatternCommand.Command;

import java.util.Scanner;

public class Developer {
    private Command addContractCommand;
    private Command deleteContractCommand;
    private Command loadDataCommand;
    private Command searchByRiskCommand;
    private Command sortByRiskCommand;
    private Command calculateTotalCostContactsCommand;
    private Command displayCommand;
    private Command saveToFileCommand;
    private Command helpCommand;
    private Command exitCommand;

    public Developer(
            Command addContractCommand,
            Command deleteContractCommand,
            Command loadDataCommand,
            Command searchByRiskCommand,
            Command sortByRiskCommand,
            Command calculateTotalCostContactsCommand,
            Command displayCommand,
            Command saveToFileCommand,
            Command helpCommand,
            Command exitCommand
    ) {
        this.addContractCommand = addContractCommand;
        this.deleteContractCommand = deleteContractCommand;
        this.loadDataCommand = loadDataCommand;
        this.searchByRiskCommand = searchByRiskCommand;
        this.sortByRiskCommand = sortByRiskCommand;
        this.calculateTotalCostContactsCommand = calculateTotalCostContactsCommand;
        this.displayCommand = displayCommand;
        this.saveToFileCommand = saveToFileCommand;
        this.helpCommand = helpCommand;
        this.exitCommand = exitCommand;
    }

    public void displayMenu() {
        System.out.println("Меню:");
        System.out.println("0. Добавити страхівку");
        System.out.println("1. Видалити страхівку");
        System.out.println("2. Пошук за ризиком");
        System.out.println("3. Сортування за ризиком");
        System.out.println("4. Обчислення загальної ціни");
        System.out.println("5. Показати усі страхівки");
        System.out.println("6. Зберегти дані у файл");
        System.out.println("7. Довідка");
        System.out.println("8. Вихід");
    }

    public Command handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                return addContractCommand;
            case 1:
                return deleteContractCommand;
            case 2:
                return searchByRiskCommand;
            case 3:
                return sortByRiskCommand;
            case 4:
                return calculateTotalCostContactsCommand;
            case 5:
                return displayCommand;
            case 6:
                return saveToFileCommand;
            case 7:
                return helpCommand;
            case 8:
                return exitCommand;
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
                return null;
        }
    }
}
