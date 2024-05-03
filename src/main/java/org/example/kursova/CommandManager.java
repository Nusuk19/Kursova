package org.example.kursova;

import PatternCommand.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commandMap;

    public CommandManager() {
        commandMap = new HashMap<>();
    }

    // Додати команду до менеджера
    public void addCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    private String policyNumber;
    private String policyType;
    private double value;
    private double levelRisk;

    // Викликати команду за її ім'ям
    public void executeCommand(String commandName) {
        Command command = commandMap.get(commandName);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Команда '" + commandName + "' не знайдена.");
        }
    }

    public void executeCommand1(String commandName, String policyNumber, String policyType, double value, double levelRisk) {
        Command command = commandMap.get(commandName);
        if (command != null) {
            command.execute(policyNumber, policyType, value, levelRisk);
        } else {
            System.out.println(STR."Команда '\{commandName}' не знайдена.");
        }
    }

    public void executeCommand2(String commandName, Object... args) {
        Command command = commandMap.get(commandName);
        if (command != null) {
            command.execute(args);
        } else {
            System.out.println("Команда '" + commandName + "' не знайдена.");
        }
    }

    // Повернути всі доступні команди
    public Map<String, Command> getCommands() {
        return commandMap;
    }
}
