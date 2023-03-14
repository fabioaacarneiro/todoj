package com.fabioaacarneiro.ToDoJ.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldAndErrorHandler {
    private String field;
    private String error;
}
