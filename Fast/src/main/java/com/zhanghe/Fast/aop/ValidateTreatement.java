package com.zhanghe.Fast.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.zhanghe.Fast.util.ReturnValue;
/**
 * ValidateTreatement
 *
 * @author Clevo
 * @date 2018/1/9 21:33
 */
@Aspect
@Configuration
public class ValidateTreatement {
	
	 /**
     *定义一个切入点.
     *解释下：
     *
     * ~第一个 * 代表任意修饰符及任意返回值.
     * ~第二个 * 任意包名
     * ~第三个 * 代表任意方法.
     * ~第四个 * 定义在web包或者子包
     * ~第五个 * 任意方法
     * ~ ..匹配任意数量的参数.
     */
     @Pointcut("execution(* com.zhanghe.Fast.controller.*.*(..))")
     public void webLog(){}
     
     @Around("execution(* com.zhanghe.Fast.controller.*.*(..))")
     public Object twiceAsOld1(ProceedingJoinPoint pjp) throws Throwable{
         Object[] args = pjp.getArgs();
         for(Object arg:args){
        	 if(arg instanceof BindingResult){
        		 BindingResult result = (BindingResult) arg;
        		 if (result.getErrorCount() > 0) {
        		 		//验证出错
        				ReturnValue<String> errorresult = new ReturnValue<String>(-2, "参数验证不通过！");
        				String errormsg = "";
        				for (FieldError error : result.getFieldErrors()) {
        	                errormsg+=error.getField() + ":" + error.getDefaultMessage()+",";
        	            }
        				errorresult.setObj(errormsg);
        				return errorresult.toJson();
        		}
        	 }
         }
         return pjp.proceed();
     }
     
}
