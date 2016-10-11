package com.cyou.runaway.Processor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Gang on 2016/10/11.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface MethodAnnotation
{
    enum Method_Type
    {
        Call,
        Get,
        Post,
    }

    String description();

    Method_Type type();

    String[] params() default {};

    String result() default "void";
}
