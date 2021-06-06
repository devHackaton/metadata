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
    protected transient OperationDescriber operationDescriber;
    protected String name;
    protected Integer id;
    protected List<FieldMetadata> fields;
}
