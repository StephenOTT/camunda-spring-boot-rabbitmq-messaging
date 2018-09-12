package io.digitalstate.camunda.custom.microservice

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.camunda.custom.microservice.rabbitmq.CamundaMessageExchange
import io.digitalstate.camunda.custom.microservice.rabbitmq.Receiver
import org.camunda.bpm.engine.rest.dto.message.CorrelationMessageDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.TimeUnit

@RestController
public class CamundaMessageRabbitRestController{

    @Autowired
    private RabbitTemplate rabbitTemplate

    @Autowired
    private Receiver receiver

    @Autowired
    private ObjectMapper objectMapper

    @RequestMapping(value = "/rabbit/message",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity addMessage(@RequestBody String message){
        try {
            // @TODO Add better error handling for covertValue method as the true error is not bubbled.
            // Will likely require abstraction replacement of the validation functions of MessageRestServiceImpl
            CorrelationMessageDto dto = objectMapper.readValue(message, CorrelationMessageDto.class)
        }catch(all){
            throw new HttpMessageNotReadableException(all.getMessage())
        }

        rabbitTemplate.convertAndSend(CamundaMessageExchange.topicExchangeName, "camunda.bpmn.message", message);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

        return new ResponseEntity(HttpStatus.ACCEPTED)
    }
}