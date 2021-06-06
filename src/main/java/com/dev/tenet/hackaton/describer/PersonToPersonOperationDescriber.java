package com.dev.tenet.hackaton.describer;

import com.dev.tenet.hackaton.enums.OperationState;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class PersonToPersonOperationDescriber extends OperationDescriber {

    @PostConstruct
    public void initOperationStepMap() {
        Map<OperationState, Integer> operationState2NextStepForFirstStep = new HashMap<>();
        operationState2NextStepForFirstStep.put(OperationState.PASSED, 2);
        operationState2NextStepForFirstStep.put(OperationState.NOT_PASSED, 3);

        Map<OperationState, Integer> operationState2NextStepForSndStep = new HashMap<>();
        operationState2NextStepForSndStep.put(OperationState.PASSED, -1);
        operationState2NextStepForSndStep.put(OperationState.NOT_PASSED, 3);

        map.put(1, operationState2NextStepForFirstStep);
        map.put(2, operationState2NextStepForSndStep);
    }

    @Override
    public Integer firstStep() {
        return 1;
    }
}
