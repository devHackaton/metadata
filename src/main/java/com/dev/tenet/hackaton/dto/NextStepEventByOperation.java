package com.dev.tenet.hackaton.dto;

import com.dev.tenet.hackaton.model.Operation;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NextStepEventByOperation extends EventDto {
    private Long userId;
    private Operation operation;

    public static NextStepEventByOperation nextStepEventByOperation(Long userId, Operation operation) {
        return new NextStepEventByOperation(userId, operation);
    }
}
