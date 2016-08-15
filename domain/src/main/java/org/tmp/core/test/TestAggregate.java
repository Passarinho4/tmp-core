package org.tmp.core.test;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.bson.types.ObjectId;

/**
 * Created by mf57 on 15.08.2016.
 */
public class TestAggregate extends AbstractAnnotatedAggregateRoot {
    @AggregateIdentifier
    private ObjectId id;
    private String name;
    private String message;

    public TestAggregate() {}

    public TestAggregate(String name, String message) {
        apply(new TestCreatedEvent(ObjectId.get(), name, message));
    }

    @EventHandler
    public void onTestCreated(TestCreatedEvent event) {
        this.id = event.getId();
        this.message = event.getMessage();
        this.name = event.getName();
        System.out.println("Jestem w event handlerze");
    }
}
