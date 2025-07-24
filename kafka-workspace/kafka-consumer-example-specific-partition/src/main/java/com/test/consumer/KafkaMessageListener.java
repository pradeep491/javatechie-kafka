package com.test.consumer;

import com.test.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    static Long counter = 0L;
    /*@KafkaListener(topics = "specific-partition",groupId = "jt-group-1")
    public void consumeEvents(Customer customer) {
        log.info("consumer consume the events {} ", customer.toString());
    }*/

    @KafkaListener(topics = "specific-partition", groupId = "jt-group-1",
            topicPartitions = {@TopicPartition(topic = "specific-partition",partitions = {"3"})})
    public void consume1(String message) {
        log.info("message count:{}",counter++);
        log.info("consumer1 consume the message {} ", message);
    }

    /*@KafkaListener(topics = "javatechie-demo3", groupId = "jt-group-1")
    public void consume2(String message) {
        log.info("consumer2 consume the message {} ", message);
    }

    @KafkaListener(topics = "javatechie-demo3", groupId = "jt-group-1")
    public void consume3(String message) {
        log.info("consumer3 consume the message {} ", message);
    }
    @KafkaListener(topics = "javatechie-demo3", groupId = "jt-group-1")
    public void consume4(String message) {
        log.info("consumer4 consume the message {} ", message);
    }
    @KafkaListener(topics = "javatechie-demo3", groupId = "jt-group-1")
    public void consume5(String message) {
        log.info("consumer5 consume the message {} ", message);
    }
    @KafkaListener(topics = "javatechie-demo3", groupId = "jt-group-1")
    public void consume6(String message) {
        log.info("consumer6 consume the message {} ", message);
    }*/
}
