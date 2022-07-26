/**   
 * @Title: CustomControllerLog.java 
 * @Package com.wind.pac.common.annotation 
 * @Description: TODO
 * @author zdli
 * @date 2017年2月3日 下午12:29:52 
 * @version V1.0   
 */
package com.gentleduo.springmvc.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizeLogAnnotation {

    /** 方法名称描述 */
    String description() default "";

    // /** 是否保存请求的参数 */
    // boolean isSaveRequestData() default false;
}
