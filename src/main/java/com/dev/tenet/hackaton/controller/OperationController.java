package com.dev.tenet.hackaton.controller;

import com.dev.tenet.hackaton.enums.OperationState;
import com.dev.tenet.hackaton.holder.OperationsHolder;
import com.dev.tenet.hackaton.model.Operation;
import com.dev.tenet.hackaton.service.SocketServiceSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.tenet.hackaton.utils.AuthUtil.getUserId;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {
//PersonToPersonTransferOperation
    private final SocketServiceSender<Object> socketService;
    private final OperationsHolder operationsHolder;

    @GetMapping("/{name}")
    public Operation getOperationId(@PathVariable String name) {
        return operationsHolder.getByName(name);
    }

    @GetMapping
    public List<Operation> getAll() {
        return operationsHolder.getAll();
    }

    @GetMapping("/{operation}/{step}/{state}")
    public void initiateNextStep(@PathVariable Integer operation,
                                 @PathVariable Integer step,
                                 @PathVariable OperationState state) {

        Integer integer = operationsHolder.getById(operation)
                .getOperationDescriber()
                .nextStep(step, state);
        socketService.sendTo(getUserId(), integer);
    }

    @GetMapping("/{operation}/{step}")
    public void initiateNextStep(@PathVariable Integer operation,
                                 @PathVariable Integer step) {

        Operation operationById = operationsHolder.getById(operation);
        socketService.sendTo(getUserId(), operationById);
    }
}
