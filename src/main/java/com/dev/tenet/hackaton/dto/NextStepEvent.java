package com.dev.tenet.hackaton.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NextStepEvent extends EventDto {
    private Long userId;
    private Integer nextStep;

    public static NextStepEvent nextStepEvent(Long userId, Integer nextStep) {
        return new NextStepEvent(userId, nextStep);
    }
}
