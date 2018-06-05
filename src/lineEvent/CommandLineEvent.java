package lineEvent;

import java.util.Arrays;
import java.util.EventObject;

public class CommandLineEvent extends EventObject {
    private String command;
    private String[] args = null;

    public CommandLineEvent(Object source, String args) {
        super(source);
        String[] argsArray = args.split("\\s+");
        this.command = argsArray[0];
        if (argsArray.length > 1) {
            this.args = Arrays.copyOfRange(argsArray, 1, argsArray.length);
        }
    }

    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        if (args != null) {
            for (String str : args) {
                builder.append(str).append(" ");
            }
        }
        return command + " " + builder.toString();
    }

    public String getCommand() {
        return this.command;
    }

    public String[] getArgs() {
        return this.args;
    }
}
