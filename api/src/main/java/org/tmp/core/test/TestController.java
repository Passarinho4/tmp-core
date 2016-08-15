package org.tmp.core.test;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mf57 on 15.08.2016.
 */
@RestController
public class TestController {

    @Autowired
    private CommandBus commandBus;

    @RequestMapping(value = "/api/tests", method = RequestMethod.POST)
    public void create(@RequestBody TestRequest body) {
        System.out.println("Jestem w controllerze");
        CreateTestCommand command = new CreateTestCommand(body.getName(), body.getMessage());
        commandBus.dispatch(new GenericCommandMessage<Object>(command));
    }
}
