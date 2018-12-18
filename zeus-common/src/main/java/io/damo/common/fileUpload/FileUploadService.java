package io.damo.common.fileUpload;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.damo.common.exception.RRException;
import io.damo.common.utils.Constant;

/**
 * Title: FileUploadUtils.java
 * Description:
 * Copyright: Copyright (c) 2018
 * Company: 深圳天晟虹网络科技
 * @author zhangyubing
 * @date 2018年5月6日
 * @version 1.0
 */
@Component
public class FileUploadService {
	
	public static String ROOT = "D://static/";
//	public static String ROOT = "/data/imgupload/";
	
	private static Logger logger = LoggerFactory.getLogger(FileUploadService.class);
	
	public List<String> multifileUpload(List<MultipartFile> files) {
		//保存图片
		List<String> urls = new ArrayList<>();
		if(CollectionUtils.isEmpty(files) || files.size() == 0) {
			return null;
		}
		for(MultipartFile file : files) {
			if (!file.isEmpty()) {  
	            try {  
	            	//文件名
	            	String fileName = getPicName(file);

					File filePath = Paths.get(ROOT).toFile();
	            	if(!filePath.exists()){
						filePath.mkdirs();
					}

	                Files.copy(file.getInputStream(), Paths.get(ROOT, fileName)); 
	                //图片url
	                StringBuffer sb = new StringBuffer("http://").append(Constant.serverIp).append(":")
							.append(Constant.serverPort).append(Constant.contextPath).append("/image/").append(fileName);
					urls.add(sb.toString());
	            } catch (Exception e) {  
	            	logger.error("图片上传异常：{}",e);
					throw new RRException("图片上传异常！");
	            }  
	        }
		}
        return urls;
	}
	
	private String getPicName(MultipartFile file) {
		StringBuffer sb = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		sb.append(df.format(new Date())).append("_").append(file.getOriginalFilename());
		return sb.toString();
	}
}
