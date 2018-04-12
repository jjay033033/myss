package top.lmoon.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonBaseUtil {
	private static Logger logger = LoggerFactory.getLogger(JsonBaseUtil.class);
	private static JsonFactory jsonFactory = new JsonFactory();
	private static ObjectMapper mapper = null;

	static {
		jsonFactory.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		jsonFactory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper = new ObjectMapper(jsonFactory);
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static JsonFactory getJsonFactory() {
		return jsonFactory;
	}

	public static <T> T toBean(String json, Class<T> clazz) {
		Object rtv = null;
		try {
			rtv = mapper.readValue(json, clazz);
		} catch (Exception ex) {
			throw new IllegalArgumentException("json字符串转成java bean异常", ex);
		}
		return (T) rtv;
	}

	public static String toJson(Object bean) {
		String rtv = null;
		try {
			rtv = mapper.writeValueAsString(bean);
		} catch (Exception ex) {
			throw new IllegalArgumentException("java bean转成json字符串异常", ex);
		}
		return rtv;
	}

	public static <T> T toBean(String json, TypeReference<T> refer) {
		if (StringUtil.isNullOrBlank((String) json)) {
			throw new IllegalArgumentException("json can not null");
		}
		Object entity = null;
		try {
			entity = mapper.reader(refer).readValue(json);
		} catch (Exception e) {
			throw new IllegalArgumentException("json字符串转成java bean异常", e);
		}
		return (T) entity;
	}

	public static <T> List<T> toBeanList(String json, Class<T> clazz) {
		if (StringUtil.isNullOrBlank((String) json)) {
			throw new IllegalArgumentException("json can not null");
		}
		ArrayList result = null;
		try {
			JsonNode jn = mapper.readTree(json);
			result = new ArrayList();
			if (jn.isArray()) {
				Iterator iter = jn.iterator();
				while (iter.hasNext()) {
					JsonBaseUtil.parseBeanAddToList((JsonNode) iter.next(), result, clazz);
				}
			} else {
				JsonBaseUtil.parseBeanAddToList(jn, result, clazz);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("json字符串转成java List<bean>异常", e);
		}
		return result;
	}

	private static <T> void parseBeanAddToList(JsonNode js, List<T> list, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		T rtv = mapper.readValue(js.toString(), clazz);
		list.add(rtv);
	}
}
