package org.tmp.core.test;

import org.bson.types.ObjectId;

/**
 * Created by mf57 on 15.08.2016.
 */
public class TestCreatedEvent {

    private final ObjectId id;
    private final String name;
    private final String message;

    public TestCreatedEvent(ObjectId id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public ObjectId getId() {
        return id;
    }
}
