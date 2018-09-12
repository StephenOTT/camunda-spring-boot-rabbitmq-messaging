package io.digitalstate.camunda.custom.microservice.rabbitmq

import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.rest.dto.message.CorrelationMessageDto
import org.camunda.bpm.engine.rest.impl.MessageRestServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.ws.rs.core.Response

@Service
public class CamundaMessageProcessor{

    @Autowired
    private ProcessEngine engine

    @Autowired
    private ObjectMapper objectMapper

    public Response processMessage(String message){
        CorrelationMessageDto messageDto = objectMapper.readValue(message, CorrelationMessageDto.class)
        MessageRestServiceImpl service = new MessageRestServiceImpl(engine.getName(), objectMapper)
        Response response = service.deliverMessage(messageDto)
        return response
    }
}