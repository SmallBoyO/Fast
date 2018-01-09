package com.zhanghe.Fast.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import com.zhanghe.Fast.service.RoleService;
import com.zhanghe.Fast.validator.RoleName;
/**  
 * RoleNameValidator
 *   
 * @author Clevo  
 * @date 2018/1/9 21:32
 */  
public class RoleNameValidator implements ConstraintValidator<RoleName, String> {

	@Autowired  
	private RoleService roleService;
	
	@Override
	public void initialize( RoleName constraintAnnotation ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid( String value ,ConstraintValidatorContext context ) {
		if(roleService.checkRoleByRoleNameAndId(value,null)){
			return false;
		}else{
			return true;
		}
	}

}
