package com.dev.tenet.hackaton.model;

import com.dev.tenet.hackaton.describer.OperationDescriber;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class Operation {
    private transient OperationDescriber operationDescriber;
    private final String name;
    private final Integer id;
    private final List<FieldMetadata> fields;
}
