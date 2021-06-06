package com.dev.tenet.hackaton.describer;

import com.dev.tenet.hackaton.enums.OperationState;
import com.dev.tenet.hackaton.model.OperationStep;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class OperationDescriber {
    private final Map<Integer, Map<OperationState, Integer>> map = new ConcurrentHashMap<>();
    private final Map<Integer, OperationStep> stepMap = new ConcurrentHashMap<>();

    public Integer nextStep(int currentStep, OperationState state) {
        return map.get(currentStep).get(state);
    }

    public abstract Integer firstStep();

    public OperationStep getStep(Integer step) {
        return stepMap.get(step);
    }
}
