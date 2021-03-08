package com.fico.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "You do not have enough Permissions to perform this task")
public class OperationNotAllowedException extends RuntimeException {
}
