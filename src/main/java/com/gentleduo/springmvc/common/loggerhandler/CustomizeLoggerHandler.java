package com.gentleduo.springmvc.common.loggerhandler;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomizeLoggerHandler {

	// execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?
	// name-pattern(param-pattern)throws-pattern?)
	// ret-type-pattern,name-pattern,param-pattern是必须的
	// ret-type-pattern:可以为*表示任何返回值,全路径的类名等.
	// name-pattern:指定方法名,*代表所以,set*,代表以set开头的所有方法.
	// param-pattern:指定方法参数(声明的类型),(..)代表所有参数,(*)代表一个参数,(*,String)代表第一个参数为任何值,第二个为String类型.
	// 举例说明:
	// 1）execution(* *(..))
	// //表示匹配所有方法
	// 2）execution(public * com.savage.service.UserService.*(..))
	// //表示匹配com.savage.server.UserService中所有的公有方法
	// 3）execution(* com.savage.server..*.*(..))
	// //表示匹配com.savage.server包及其子包下的所有方法
	// 也可以自定义注解的方式实现切面
	// @Pointcut("@annotation(com.wind.pac.common.annotation.CustomControllerLog)")
	@Pointcut("execution (* com.gentleduo.springmvc.service..*.*(..))")
	private void aspectjMethod() {

	}

	/**
	 * @Description: 在核心业务执行前执行此Advice
	 */
	@Before("aspectjMethod()")
	public void before(JoinPoint joinPoint) {

		Logger logger = LogManager.getLogger("com.gentleduo");
		// logger.info(getMethodNameAndArgs(joinPoint) + " Start");
		logger.info("=================【Before Advice】=================");
	}

	/**
	 * @Description: 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
	 */
	@After(value = "aspectjMethod()")
	public void after(JoinPoint joinPoint) {

		Logger logger = LogManager.getLogger("com.gentleduo");
		// logger.info(getMethodNameAndArgs(joinPoint) + " End");
		logger.info("=================【After Advice】=================");
	}

	/**
	 * @Description: 手动控制调用核心业务逻辑
	 * 正常情况：@Around start ==> @Before ==> method invoke(被代理对象的Method) ==> @Around end ==> @After ==> @AfterReturning
	 * 异常情况：@Around start ==> @Before ==> method invoke(被代理对象的Method) ==> @After ==> @AfterReturning
	 */
	@Around(value = "aspectjMethod()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {

		Logger logger = LogManager.getLogger("com.gentleduo");
		// @Before Advice之前执行@Around Advise before;
		logger.info("=================【Around Advice Before】=================");
		// 调用核心逻辑：即被代理对象的Method
		Object retVal = pjp.proceed();
		// @After Advice之前执行(异常的时候则不会执行这部分的代码) @Around Advise after
		logger.info("=================【Around Advice After】=================");
		return retVal;
	}

	/**
	 * @Description: 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 *               声明retVal时指定的类型会限制目标方法必须返回指定类型的值或没有返回值(如目标方法的返回值和指定类型不匹配则不会触发此Advice)
	 *               此处将retVal的类型声明为Object，意味着对目标方法的返回值不加限制
	 */
	@AfterReturning(value = "aspectjMethod()", returning = "retVal")
	public void afterReturning(JoinPoint joinPoint, Object retVal) {

		Logger logger = LogManager.getLogger("com.gentleduo");
		logger.info("=================【afterReturning Advice】=================");
	}

	/**
	 * @Description: 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息 注意：执行顺序在@After Advice之后
	 * 
	 */
	@AfterThrowing(value = "aspectjMethod()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {

		Logger logger = LogManager.getLogger("com.gentleduo");
		logger.info("=================【AfterThrowing Advice】=================");
	}

	// /**
	// * @Title getController
	// * @Description: 是否存在注解，如果存在就记录日志
	// * @param joinPoint
	// * @return
	// * @return CustomControllerLog
	// * @throws
	// */
	// @SuppressWarnings("unused")
	// private static CustomControllerLog getAnnotation(JoinPoint joinPoint) {
	//
	// Signature signature = joinPoint.getSignature();
	// MethodSignature methodSignature = (MethodSignature) signature;
	// Method method = methodSignature.getMethod();
	//
	// if (method != null) {
	// return method.getAnnotation(CustomControllerLog.class);
	// }
	// return null;
	// }

	// /**
	// * @Title getControllerMethodDescription
	// * @Description: 获取注解中对方法的描述信息 用于Controller层注解
	// * @param controllerLog
	// * @return String
	// * @throws
	// */
	// @SuppressWarnings("unused")
	// private static String getControllerMethodDescription(
	// CustomControllerLog controllerLog) {
	// // 方法名称描述
	// return controllerLog.description();
	// }

	/**
	 * @Title getMethodNameAndArgs @Description: 获取方法名和参数 @param
	 *        joinPoint @return @return String @throws
	 */
	private String getMethodNameAndArgs(JoinPoint joinPoint) {

		Object[] args = joinPoint.getArgs();
		StringBuffer sb = new StringBuffer("Method() ");
		sb.append(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "(");
		for (int i = 0; i < args.length; i++) {
			sb.append("arg[" + i + "]: " + args[i] + ",");
		}
		if (args.length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(")");
		return sb.toString();
	}
}
