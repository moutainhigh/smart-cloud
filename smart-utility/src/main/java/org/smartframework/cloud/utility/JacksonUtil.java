package org.smartframework.cloud.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * jackson工具类
 *
 * @author liyulin
 * @date 2020-05-23
 */
@Slf4j
public final class JacksonUtil {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	/**
	 * 对象转json
	 * 
	 * @param value
	 * @return
	 */
	public static final String toJson(Object value) {
		String result = null;
		try {
			result = objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.error("mask error", e);
		}
		return result;
	}

	/**
	 * json转对象
	 * 
	 * @param content
	 * @param valueType
	 * @return
	 */
	public static <T> T parseObject(String content, Class<T> valueType) {
		T t = null;
		try {
			t = objectMapper.readValue(content, valueType);
		} catch (JsonProcessingException e) {
			log.error("parse object error", e);
		}

		return t;
	}

	/**
	 * json转对象
	 * 
	 * @param content
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T parseObject(String content, TypeReference<T> valueTypeRef) {
		T t = null;
		try {
			t = objectMapper.readValue(content, valueTypeRef);
		} catch (JsonProcessingException e) {
			log.error("parse object error", e);
		}

		return t;
	}

	public static JsonNode parseObject(String content) {
		JsonNode t = null;
		try {
			t = objectMapper.readTree(content);
		} catch (JsonProcessingException e) {
			log.error("parse object error", e);
		}

		return t;
	}

}