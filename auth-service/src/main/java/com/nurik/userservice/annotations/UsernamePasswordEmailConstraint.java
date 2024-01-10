package com.nurik.userservice.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Constraint(validatedBy = {})
@Size(min = 8)
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernamePasswordEmailConstraint {
    String message() default "Length should be more than 8 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
