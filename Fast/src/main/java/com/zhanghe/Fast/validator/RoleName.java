package com.zhanghe.Fast.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.zhanghe.Fast.validator.impl.RoleNameValidator;

import static java.lang.annotation.ElementType.*;  


@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleNameValidator.class)  
@Documented
public @interface RoleName{
	
	String message() default "角色名重复！";
	
	Class<?>[] groups()  default {};
	
	Class<? extends Payload>[] payload()  default {};
	
	String feild() default "";

}
