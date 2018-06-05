package commandFactory;

import commands.*;
import field.LogoField;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

abstract class Command {
    public abstract void execute(LogoField context, String[] args);
//    protected void checkIntArguments(String commandName, String[] args) throws InvalidArgumentException {
//        try {
//            for (String arg : args) {
//                if (Integer.parseInt(arg) < 0) {
//                    throw new InvalidArgumentException(commandName);
//                }
//            }
//        } catch (NumberFormatException ex) {
//            throw new InvalidArgumentException(commandName);
//        }
//    }
}

class Move extends Command {
    public Move() {}

    @Override
    public void execute(LogoField context, String[] args) {
        char dir = args[0].charAt(0);
        int offset = Integer.parseInt(args[1]);
        context.getTurtle().move(dir, offset);
    }
}

public class Factory {
    private static final String RSC_NAME = "/config.csv";
    private static final String PACKAGE_NAME = "commandFactory.";

    private static Map<String, String> config = new TreeMap<>();
    private static Map<String, Command> cache = new TreeMap<>();

    static {
        InputStream inputstream = null;
        try {
            inputstream = Factory.class.getResourceAsStream(RSC_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split(",\\s+");
                config.put(args[0], args[1]);
            }
        }
        catch (IOException exception) {
            System.err.println("Error");
        }
        finally {
            if (inputstream != null) {
                try { inputstream.close(); }
                catch (IOException e) { System.err.println(e.getLocalizedMessage()); }
            }
        }
    }

    public static Command create(String stringCommand) {
        System.out.println("I am in factory");
        String commandClassName = config.get(stringCommand);
        if (commandClassName == null) {
            //throw new InvalidCommandException(stringCommand);
        }
        if (cache.containsKey(commandClassName)) {
            return cache.get(commandClassName);
        }
        Command command = null;
        try {
            System.out.println(PACKAGE_NAME + commandClassName);
            command = (Command) Class.forName(PACKAGE_NAME + commandClassName).getConstructor().newInstance();
            cache.put(commandClassName, command);
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException
                | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return command;
    }
}
