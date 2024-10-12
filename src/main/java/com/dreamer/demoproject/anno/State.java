package com.dreamer.demoproject.anno;

import com.dreamer.demoproject.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented//元注解，指示该注解应该被包含在Java文档中
@Constraint(
        validatedBy = {StateValidation.class}
)//元注解，指定对该注解进行校验的类为StateValidation
@Target({ElementType.FIELD})//元注解，表示注解所能使用的地方，这里只保留FIELD(元素)
@Retention(RetentionPolicy.RUNTIME)//元注解，表示注解会用在哪个阶段(编译/运行)，
// 由于State注解程序运行时任然需要使用，因此为RUNTIME
public @interface State {
    //校验失败提示信息
    String message() default "state只能时已发布或草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载 获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
