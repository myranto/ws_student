package com.my.ws_student.utils.inter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TableAnnotation {
    public String nameTable() default "";

}
