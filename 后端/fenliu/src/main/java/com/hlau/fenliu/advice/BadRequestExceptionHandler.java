package com.hlau.fenliu.advice;


import com.hlau.fenliu.Utils.ResCode;
import com.hlau.fenliu.Utils.RestUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public LinkedHashMap validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        System.out.println(errors);
        return RestUtil.ERROR(ResCode.ASPIRATIONREPEAT,errors.get(0).getDefaultMessage());
    }
}
