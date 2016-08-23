package org.tmp.core.config;

import com.mongodb.Mongo;
import org.axonframework.cache.WeakReferenceCache;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.axonframework.eventstore.mongo.MongoTemplate;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.*;
import org.tmp.core.test.TestAggregate;

import java.util.concurrent.Executors;


@Configuration
@ImportResource("classpath:domain-config.xml")
@ComponentScan("org.tmp")
@Profile("production")
public class ProdAxonConfig extends BaseConfig {

    @Bean
    public CommandBus commandBus() throws Exception {
        return new AsynchronousCommandBus(Executors.newFixedThreadPool(10));
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public EventStore eventStore() throws Exception {
        return new MongoEventStore(defaultAxonMongoTemplate());
    }

    @Bean
    public MongoTemplate defaultAxonMongoTemplate() throws Exception {
        return new DefaultMongoTemplate(new Mongo("51.255.48.55", 27017), "tmp-core", "domainEvents",
                "snapshotEvents", "admin", "root", "root123".toCharArray());
    }

    @Bean(name = "testRepository")
    public Repository<TestAggregate> testRepository() throws Exception {
        CachingEventSourcingRepository<TestAggregate> repository;
        repository = new CachingEventSourcingRepository<>(new GenericAggregateFactory<>(TestAggregate.class), eventStore());
        repository.setEventBus(eventBus());
        repository.setCache(new WeakReferenceCache());
        return repository;
    }
}
