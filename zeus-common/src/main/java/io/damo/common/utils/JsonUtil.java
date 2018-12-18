package io.damo.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Logger;

/*
projectName:pjh-server
packageName:com.pjh.cbs.dto
author:nangua
date:2017-08-21 15:35
*/
public class JsonUtil {

    private static final Logger logger = Logger.getLogger(JsonUtil.class.getName());

	public static Gson getGson() {
		return new Gson();
	}

	
	
	
	/**
	 * 将对象转为JSON字符串(忽略NULL值)
	 * 
	 * @param src
	 * @return
	 */
	public static String toJson(Object src) {
		if(src == null){
			return null;
		}
		if(src instanceof String){
			return (String)src;
		}
		return getGson().toJson(src);
	}

	/**
	 * 将对象转为JSON字符串(不忽略NULL值)
	 * @param src
	 * @param serializeNulls
	 * @return
	 */
	public static String toJson(Object src, boolean serializeNulls) {
		if (serializeNulls)
			return new GsonBuilder().serializeNulls().create().toJson(src);
		return toJson(src);
	}

	/**
	 * 将JSON字符串转为对象
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 * @throws JsonSyntaxException
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
		return getGson().fromJson(json, classOfT);
	}

    public static <T> List<T> fromTResultJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        List<T> list = new ArrayList<T>();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement el = jsonArray.get(i);
            T object = null;
            try {
            	object = fromJson(el.toString(),classOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            list.add(object);
        }
        logger.info("响应：{}"+JsonUtil.toJson(list));
        return list;
    }
    /**
	 * 将JSON格式的字符串转换为List<T>类型的对象
	 * 
	 * @param json
	 *            JSON格式的字符串
	 * @param clz
	 *            指定泛型集合里面的T类型
	 * @return List<T>类型的对象
	 */
	public static <T> List<T> deserializeList(String json, Class<T> clz) {
		String jsonStr = "";
		if (!(null == json || json.trim().equals(""))) {
			if (!json.startsWith("[")) {
				jsonStr = "[" + json + "]";
			} else {
				jsonStr = json;
			}
			return JSON.parseArray(jsonStr, clz);
		} else {
			return new ArrayList<T>();
		}
	}

	/**
	 * 从请求体中读取客户端发送的JSON串
	 * @param stream
	 *            输入流
	 * @return String 类型，接收到的JSON串
	 */
	public static String readStringFromRequestBody(InputStream stream) {
		if(stream == null){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[2048];
		int len = -1;
		try {
			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			while ((len = reader.read(buf)) != -1) {
				sb.append(new String(buf, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}

	public static  Map<String, Object> object2Map(Object object){
		JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
		Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
		Map<String, Object> map=new HashMap<>();
		for (Map.Entry<String, Object> entry : entrySet) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
}
