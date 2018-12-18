package io.damo.sms.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author pinshiern
 * @Description:国际短信发送接口
 */
public class SmsApp {

	private static final Logger logger = LoggerFactory.getLogger(SmsApp.class);

	public static void send(String url,String params){
		logger.info("请求参数为:" + params);
		try {
			String result=HttpUtil.post(url, params);

			logger.info("返回参数为:" + result);

			JSONObject jsonObject =  JSON.parseObject(result);
			String code = jsonObject.get("code").toString();
			String msgid = jsonObject.get("msgid").toString();
			String error = jsonObject.get("error").toString();

			logger.info("状态码:" + code + ",状态码说明:" + error + ",消息id:" + msgid);
		} catch (Exception e) {
			logger.error("请求异常：" + e);
		}
	}
	
}
