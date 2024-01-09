package com.nurik.userservice.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = {})
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernamePasswordEmailConstraint {
    String message() default "Length should be between {min} and {max} characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minUsernameLength() default 8; // Minimum username length
    int maxUsernameLength() default 30; // Maximum username length
    int minPasswordLength() default 8; // Minimum password length
    int maxPasswordLength() default 30; // Maximum password length
}
