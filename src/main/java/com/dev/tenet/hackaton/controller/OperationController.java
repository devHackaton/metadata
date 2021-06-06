package com.dev.tenet.hackaton.controller;

import com.dev.tenet.hackaton.enums.OperationState;
import com.dev.tenet.hackaton.holder.OperationsHolder;
import com.dev.tenet.hackaton.kafka.KafkaProducer;
import com.dev.tenet.hackaton.model.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.tenet.hackaton.dto.NextStepEvent.nextStepEvent;
import static com.dev.tenet.hackaton.dto.NextStepEventByOperation.nextStepEventByOperation;
import static com.dev.tenet.hackaton.utils.AuthUtil.getUserId;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {
    private final OperationsHolder operationsHolder;
    private final KafkaProducer kafkaProducer;
    @Value("${spring.kafka.topic.websocket}")
    private String websocketTopic;

    @GetMapping("/{name}")
    public Operation getOperationId(@PathVariable String name) {
        return operationsHolder.getByName(name);
    }

    @GetMapping
    public List<Operation> getAll() {
        return operationsHolder.getAll();
    }

    @GetMapping("/{operation}/{step}/{state}")
    public int initiateNextStep(@PathVariable Integer operation,
                                 @PathVariable Integer step,
                                 @PathVariable OperationState state) {

        Integer nextStep = operationsHolder.getById(operation)
                .getOperationDescriber()
                .nextStep(step, state);
        if (nextStep == -1) {
            //последний этап регистрации операции пройден, нужно
            //отдать клиенту форму подтверждения операции
        }
//        kafkaProducer.sendEventMessage(nextStepEvent(getUserId(), nextStep), websocketTopic);
        return nextStep;
    }

    @GetMapping("/{operation}/{step}")
    public void getStepMetadata(@PathVariable Integer operation,
                                 @PathVariable Integer step) {

        Operation operationById = operationsHolder.getById(operation);
        kafkaProducer.sendEventMessage(nextStepEventByOperation(getUserId(), operationById), websocketTopic);
    }
}
