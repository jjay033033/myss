package top.lmoon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.lmoon.mail.MailUtil;

public class JsonResponse {


	/**
	 * 创建实例
	 * 
	 * @param response
	 *            Json响应
	 * @return
	 */
	public static JsonResponse newInstance(String response) {
		return new JsonResponse(response);
	}

	/**
	 * 结果代码
	 */
	private String code = null;

	/**
	 * 数据Map
	 */
	private Map<Object, Object> dataMap = null;

	/**
	 * 结果信息
	 */
	private String message = null;

	/**
	 * Json响应
	 */
	private String response = null;

	/**
	 * 成功标识
	 */
	private boolean success = false;

	/**
	 * 构造函数
	 */
	private JsonResponse() {
	}

	/**
	 * 构造函数，初始化Json响应
	 * 
	 * @param response
	 */
	private JsonResponse(String response) {
		this.response = response;
		parseResult();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the dataMap
	 */
	public Map<Object, Object> getDataMap() {
		return dataMap;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 解析Json响应
	 */
	@SuppressWarnings("unchecked")
	private void parseResult() {

		// 判断是否为空
		if (response == null || response.trim().equals("")) {
			return;
		}

		try {
			// 将Json字符串转成Map
			Map responseMap = JsonBaseUtil.toBean(response,
					HashMap.class);

			// 获取success
			if (responseMap.containsKey("success")) {
				success = (Integer.parseInt(String.valueOf(responseMap
						.get("success"))) == 1);
			}

			// 获取code
			if (responseMap.containsKey("code")) {
				code = String.valueOf(responseMap.get("code"));
			}

			// 获取message
			if (responseMap.containsKey("message")) {
				message = String.valueOf(responseMap.get("message"));
			}

			// 获取data
			if (responseMap.containsKey("data")) {
				dataMap = (Map) responseMap.get("data");
			}

		} catch (Exception e) {
			e.printStackTrace();System.out.println(ExceptionUtil.getExceptionMessage(e));
			MailUtil.asyncSendErrorEmail(e);
		}
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 按key获得list
	 * @param key
	 * @return
	 */
	public List getList(String key){
		if(isSuccess() && dataMap!=null){
			List list = (List) dataMap.get(key);
			if(list!=null){
				return list;
			}
		}
		return emptyList;
	}
	
	private static List emptyList = new ArrayList();
}
