package com.dev.tenet.hackaton.holder;

import com.dev.tenet.hackaton.model.Operation;
import com.dev.tenet.hackaton.service.SocketServiceSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;

@RequiredArgsConstructor
@Component
public class OperationsHolder {
    private final SocketServiceSender<Integer> socketService;

    private final Map<String, Operation> name2Operation = new ConcurrentHashMap<>();
    private final Map<Integer, Operation> id2Operation = new ConcurrentHashMap<>();

    @Autowired
    public void register(List<Operation> operations) {
        operations.stream()
                .collect(Collectors.toMap(Operation::getName, identity()))
                .forEach(this.name2Operation::putIfAbsent);
        operations.stream()
                .collect(Collectors.toMap(Operation::getId, identity()))
                .forEach(this.id2Operation::putIfAbsent);
    }

    public Operation getByName(String name) {
        return name2Operation.get(name);
    }

    public List<Operation> getAll() {
        return new ArrayList<>(name2Operation.values());
    }

    public Operation getById(Integer id) {
        return id2Operation.get(id);
    }
}
