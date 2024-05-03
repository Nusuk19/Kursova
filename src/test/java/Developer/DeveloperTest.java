package Developer;

import PatternCommand.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DeveloperTest {

    private Developer developer;
    private Command mockAddContractCommand;
    private Command mockDeleteContractCommand;
    private Command mockLoadDataCommand;
    private Command mockSearchByRiskCommand;
    private Command mockSortByRiskCommand;
    private Command mockCalculateTotalCostContactsCommand;
    private Command mockDisplayCommand;
    private Command mockSaveToFileCommand;
    private Command mockHelpCommand;
    private Command mockExitCommand;

    @BeforeEach
    void setUp() {
        mockAddContractCommand = mock(Command.class);
        mockDeleteContractCommand = mock(Command.class);
        mockLoadDataCommand = mock(Command.class);
        mockSearchByRiskCommand = mock(Command.class);
        mockSortByRiskCommand = mock(Command.class);
        mockCalculateTotalCostContactsCommand = mock(Command.class);
        mockDisplayCommand = mock(Command.class);
        mockSaveToFileCommand = mock(Command.class);
        mockHelpCommand = mock(Command.class);
        mockExitCommand = mock(Command.class);

        developer = new Developer(
                mockAddContractCommand,
                mockDeleteContractCommand,
                mockLoadDataCommand,
                mockSearchByRiskCommand,
                mockSortByRiskCommand,
                mockCalculateTotalCostContactsCommand,
                mockDisplayCommand,
                mockSaveToFileCommand,
                mockHelpCommand,
                mockExitCommand
        );
    }

    @Test
    void testDisplayMenu() {
        // Arrange
        // Capturing output to check printed menu
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()) {
            @Override
            public void println(String x) {
                output.append(x).append("\n");
            }
        });

        // Act
        developer.displayMenu();

        // Assert
        String expectedMenu = "Меню:\n" +
                "0. Добавити страхівку\n" +
                "1. Видалити страхівку\n" +
                "2. Пошук за ризиком\n" +
                "3. Сортування за ризиком\n" +
                "4. Обчислення загальної ціни\n" +
                "5. Показати усі страхівки\n" +
                "6. Зберегти дані у файл\n" +
                "7. Довідка\n" +
                "8. Вихід\n";
        assertEquals(expectedMenu, output.toString());
    }

    @Test
    void testHandleUserInput() {
        // Arrange
        String input = "3\n"; // Введення користувача "3" (вибір команди sortByRiskCommand)
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Act
        Command command = developer.handleUserInput();

        // Assert
        assertEquals(mockSortByRiskCommand, command);
    }
}
