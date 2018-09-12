# camunda-spring-boot-rabbitmq-messaging
Example project of a Camunda Spring Boot project that uses RabbitMQ to queue messages


# What this does

This provides a example implementation of using the /message Rest API endpoint from Camunda 
BPM and reusing the underlying implementation class to provide a RabbitMQ implementation 
that delivers messages based from the JSON payload that would typically be delivered 
through the Rest API, but in this case it is delivered through Rabbit.

Camunda Spring Boot is used.