package com.dev.tenet.hackaton.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendEventMessage(Object obj, String topic) {
        try {
            String message = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(topic, message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Pull message=[{}] with offset=[{}] to topic {}", message,
                            result.getRecordMetadata().offset(),
                            topic
                    );
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to send message=[{}] due to :{}", message, ex.getMessage());
                }
            });
        } catch (JsonProcessingException e) {
            log.error("Kafka message serializer error = {}", e.getMessage(), e);
        }
    }
}
