package io.damo.sms.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @Description:回调servlet
 */
@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {

	private static final long serialVersionUID = -8529247129492069689L;
	private static final Logger logger = LoggerFactory.getLogger(CallbackServlet.class);
	private static final String CHARSET = "UTF-8";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding(CHARSET);
		resp.setCharacterEncoding(CHARSET);
		PrintWriter pw = null;
		String result = "error";
		try {
			pw = resp.getWriter();
			/** 获取请求参数 **/
			BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream(), CHARSET));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			in.close();
			String postContent = buffer.toString();
			
			// 回调示例: {"code": "0", "error":"", "msgid":"17041010383624511"}
			logger.info("接收到参数：" + postContent);

			result = "OK";

		} catch (Exception e) {
			logger.error("callback接口出现异常:" + e.getMessage(), e);
			e.printStackTrace();
		} finally {
			logger.info("HTTP请求结果：" + result);
			pw.print(result);
			pw.flush();
			pw.close();
		}
	}

}
