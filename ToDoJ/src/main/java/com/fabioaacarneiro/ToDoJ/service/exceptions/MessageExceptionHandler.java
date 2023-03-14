package com.fabioaacarneiro.ToDoJ.service.exceptions;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MessageExceptionHandler
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageExceptionHandler {

    private Date timestamp;
    private Integer status;
    private String message;
    private List<FieldAndErrorHandler> errors;
}
