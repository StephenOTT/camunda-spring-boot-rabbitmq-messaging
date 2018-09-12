package io.digitalstate.camunda.custom.microservice.rabbitmq

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.ws.rs.core.Response
import java.util.concurrent.CountDownLatch

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    CamundaMessageProcessor messageProcessor

    public void receiveMessage(String message) {
        try {
            Response response = messageProcessor.processMessage(message)
        }catch(all){
            // @TODO: Fix up error catching with the service
            println all.getMessage()
        }finally{
            latch.countDown()
        }

    }

    public CountDownLatch getLatch() {
        return latch;
    }
}