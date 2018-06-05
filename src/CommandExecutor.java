package commandFactory;

import field.LogoField;

import java.util.Arrays;

public class CommandExecutor {
    public static void executeCommand(String commandName, String[] args, LogoField context) {
        System.out.println("Command: " + commandName + " args: " + Arrays.toString(args));
        Command command = Factory.create(commandName.toUpperCase());

        command.execute(context, args);
    }
}
