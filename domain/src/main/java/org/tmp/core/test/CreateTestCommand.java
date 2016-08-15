package org.tmp.core.test;

/**
 * Created by mf57 on 15.08.2016.
 */
public class CreateTestCommand {
    private String name;
    private String message;


    public CreateTestCommand(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
