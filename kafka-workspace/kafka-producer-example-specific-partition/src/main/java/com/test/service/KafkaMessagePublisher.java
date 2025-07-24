package com.test.service;

import com.test.dto.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToTopic(String message) {
        //CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("specific-partition", message);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("specific-partition",3,null, message);
        //future.get() //it will slow down the process

        //callback implementation
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

        //enable the below to test specific partition test
        /*kafkaTemplate.send("specific-partition",3,null, "from 3 -1");
        kafkaTemplate.send("specific-partition",2,null, "from 2 -1");
        kafkaTemplate.send("specific-partition",1,null, "from 1 -1");
        kafkaTemplate.send("specific-partition",1,null, "from 1 -1");
        kafkaTemplate.send("specific-partition",3,null, "from 3 -2");
        kafkaTemplate.send("specific-partition",4,null, "from 4 -1");*/
    }
    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("specific-partition", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + customer.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            customer.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }
}
