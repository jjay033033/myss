/**
 * @copyright 2010 tianya.cn
 */
package top.lmoon.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json操作工具
 * 
 * @author hebenshi
 * @date 2010-5-20
 */
public class JsonUtil {
	/**
	 * 构造执行失败响应；符合前端格式
	 * 
	 * @param error
	 * @return
	 */
	public static String getFailedResponse(String error) {

		String response = null;

		try {
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			Map<Object, Object> map = new HashMap<Object, Object>();
			responseMap.put("success", 0);
			responseMap.put("code", "-1");
			responseMap.put("message", error);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}

		return response;
	}

	/**
	 * 构造执行失败响应
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getFailedResponse(String code, String message) {
		String response = null;
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			Map<Object, Object> responseMap = new HashMap<Object, Object>();
			responseMap.put("success", 0);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}

	/**
	 * 构造JSON请求
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonRequest(Map map) {
		String request = null;
		try {
			if (map == null) {
				map = new HashMap<Object, Object>();
			}
			request = toJson(map);
		} catch (Exception e) {
		}
		return request;
	}

	/**
	 * 构造JSON响应
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonResponse(Map<Object, Object> map) {
		String request = null;
		try {
			if (map == null) {
				map = new HashMap<Object, Object>();
			}
			request = toJson(map);
		} catch (Exception e) {
		}
		return request;
	}

	/**
	 * 把json字符串转换为Map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<Object, Object> getRequestMap(String request) {
		Map<Object, Object> requestMap = toBean(request, HashMap.class);
		return requestMap;
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static String getSuccessResponse(String code, String message) {
		Map map = new HashMap();
		return getSuccessResponse(code, message, map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param list
	 * @return
	 */
	public static String getSuccessResponse(String code, String message, List list) {
		Map map = new HashMap();
		map.put("list", list);
		return getSuccessResponse(code, message, map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @return
	 */
	public static String getSuccessResponse(String code, String message, Map map) {
		String response = null;
		try {
			Map responseMap = new HashMap();
			responseMap.put("success", 1);
			responseMap.put("code", code);
			responseMap.put("message", message);
			responseMap.put("data", map);
			response = toJson(responseMap);
		} catch (Exception e) {
		}
		return response;
	}
	
	public static String getSuccessResponse(List list) {
		Map map = new HashMap();
		map.put("list", list);
		return getSuccessResponse(map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param code
	 * @param message
	 * @param map
	 * @param list
	 * @return
	 */
	public static String getSuccessResponse(String code, String message, Map map,
			List list) {
		map.put("list", list);
		return getSuccessResponse(code, message, map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param error
	 * @return
	 */
	public static String getSuccessResponse() {

		Map map = new HashMap();
		return getSuccessResponse(map);
	}

	/**
	 * 构造执行成功响应
	 * 
	 * @param error
	 * @param map
	 * @return
	 */
	public static String getSuccessResponse(Map map) {

		String response = null;

		try {
			if (map == null) {
				map = new HashMap();
			}
			Map responseMap = new HashMap();
			responseMap.put("success", 1);
			responseMap.put("code", "1");
			responseMap.put("message", "");
			responseMap.put("data", map);
			response = JsonBaseUtil.toJson(responseMap);
		} catch (Exception e) {
		}

		return response;
	}

	/**
	 * JSON反序列化
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		return JsonBaseUtil.toBean(json, clazz);
	}

	/**
	 * JSON序列化
	 * 
	 * @param bean
	 * @return
	 */
	public static String toJson(Object bean) {
		return JsonBaseUtil.toJson(bean);
	}

}
