package org.tmp.core.test;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mf57 on 15.08.2016.
 */
@Component
public class TestCommandHandler {

    @Autowired
    Repository<TestAggregate> testAggregateRepository;

    @CommandHandler
    public void createTestAggregate(CreateTestCommand command) {
        System.out.println("Jestem w command handlerze");
        TestAggregate aggregate = new TestAggregate(command.getName(), command.getMessage());
        testAggregateRepository.add(aggregate);

    }
}
