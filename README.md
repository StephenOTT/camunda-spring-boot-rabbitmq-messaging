# camunda-spring-boot-rabbitmq-messaging
Example project of a Camunda Spring Boot project that uses RabbitMQ to queue messages


# What this does

This provides a example implementation of using the /message Rest API endpoint from Camunda 
BPM and reusing the underlying implementation class to provide a RabbitMQ implementation 
that delivers messages based from the JSON payload that would typically be delivered 
through the Rest API, but in this case it is delivered through Rabbit.
Camunda Spring Boot is used for this example usage.

Note that the overall goal of this project is to provide/show a simple implementation.  
It is to provide message capabilities through a queue that mimic the current Rest API /message endpoint.  
Additional features/complexities that someone might want or need would be typically implemented as a custom build.

# Todo

1. Add retry configuration
1. Add correlation error handling
1. Add Thread controls for Message Receiver to control throughput
1. Abstract into Plugin (likely will use Camel for plugin so it will work regardless of App container)
1. Add breaker switch for too many failed delivery attempts and what to happen:  Current thinking is to default to providing a BPMN that will generate a Incident on the Message Start Event?
1. Provide a Kafka usage
