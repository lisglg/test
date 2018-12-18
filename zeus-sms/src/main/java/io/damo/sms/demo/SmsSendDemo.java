package io.damo.sms.demo;

import com.alibaba.fastjson.JSON;
import io.damo.sms.request.SmsSendRequest;
import io.damo.sms.response.SmsSendResponse;
import io.damo.sms.utils.ChuangLanSmsUtil;

/**
 * 
 * @author tianyh 
 * @Description:普通短信发送
 */
public class SmsSendDemo {

	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "CN7153776";
	// 用户平台API密码(非登录密码)
	public static String pswd = "eu9CwUPpVSd0e7";

	public static void main(String[] args) {

		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
		// 短信内容
	    String msg = "【253云通讯】你好,你的验证码是123456";
		//手机号码
		String phone = "18688771765";
		//状态报告
		String report= "true";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		System.out.println("before request string is: " + requestJson);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		
		System.out.println("response after request result is :" + response);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		System.out.println("response  toString is :" + smsSingleResponse);
		
	
	}


}
