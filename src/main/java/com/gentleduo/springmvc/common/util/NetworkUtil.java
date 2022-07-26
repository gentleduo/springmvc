package com.gentleduo.springmvc.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class NetworkUtil {

	public static boolean isAjaxRequest(HttpServletRequest request) {

		String contentType = request.getContentType() == null ? "" : request.getContentType().toLowerCase();
		String requestedWith = request.getHeader("x-requested-with");
		if (contentType.contains("application/json")
				|| (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest"))) {
			return true;
		} else {
			return false;
		}
	}

	public static String getRequestJson(HttpServletRequest request) throws IOException {
		String json = getRequestJsonString(request);
		return json;
	}

	/***
	 * 获取request中json字符串的内容
	 */
	public static String getRequestJsonString(HttpServletRequest request) throws IOException {
		String submitMehtod = request.getMethod();
		// GET
		if (submitMehtod.equals("GET")) {
			return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
			// POST
		} else {
			return getRequestPostStr(request);
		}
	}

	/**
	 * 描述:获取 post请求的 byte[]数组
	 */
	public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	/**
	 * 描述:获取 post 请求内容
	 */
	public static String getRequestPostStr(HttpServletRequest request) throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}

}
