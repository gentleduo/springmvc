/**   
 * @Title: CustomExceptionHandler.java 
 * @Package com.wind.pac.common.exception 
 * @Description: 实现HandlerExceptionResolver接口的统一异常处理器
 * @author zdli
 * @date 2017年1月25日 下午4:49:21 
 * @version V1.0   
 */
package com.gentleduo.springmvc.common.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.gentleduo.springmvc.common.util.NetworkUtil;

@Component
public class CustomizeExceptionHandler implements HandlerExceptionResolver {

	static Logger logger = LogManager.getLogger("com.gentleduo");

	@Override
	public ModelAndView resolveException(HttpServletRequest paramHttpServletRequest, HttpServletResponse out,
			Object paramObject, Exception paramException) {

		HandlerMethod handlerMethod = (HandlerMethod) paramObject;
		// 判断请求方法中是否含有ResponseBody注解
		Object annotation = handlerMethod.getMethodAnnotation(ResponseBody.class);

		// 日志输出详细的错误信息
		logger.error("============================异常信息.Start============================");
		logger.error("异常对象：" + handlerMethod.getBeanType().getName());
		logger.error("异常方法：" + handlerMethod.getMethod().getName());
		logger.error("异常名称：" + paramException.getClass().toString());
		logger.error(paramException.getMessage(), paramException);
		logger.error("============================异常信息.End============================");

		// 判断是否是返回json数据
		if (annotation != null) {
			// //判断是否是ajax请求
			// if (NetworkUtil.isAjaxRequest(paramHttpServletRequest)) {

			// ModelAndView mv = new ModelAndView();
			// /* 使用response返回 */
			// out.setStatus(HttpStatus.OK.value()); // 设置状态码
			// out.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			// out.setCharacterEncoding("UTF-8"); // 避免乱码
			// out.setHeader("Cache-Control", "no-cache, must-revalidate");
			// try {
			// out.getWriter().write("{\"success\":false,\"msg\":\"" +
			// paramException.getMessage() + "\"}");
			// } catch (IOException e) {
			// logger.error("与客户端通讯异常:" + e.getMessage(), e);
			// }
			// return mv;

			ModelAndView mv = new ModelAndView();
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			mv.setView(view);
			mv.addObject("status", "fail");
			mv.addObject("message", "系统错误");
			return mv;
		} else {

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("exception", paramException);
			return new ModelAndView("comjsp/systemerror", model);

		}

	}
}