package com.rest_with_spring_and_java_erudio.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private java.util.Date timestamp;
    private String message;
    private String detail;


}
