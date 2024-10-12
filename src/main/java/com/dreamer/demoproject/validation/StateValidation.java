package com.dreamer.demoproject.validation;

import com.dreamer.demoproject.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        //校验逻辑
        if(value==null)return false;
        else return value.equals("已发布") | value.equals("草稿");
    }
}
