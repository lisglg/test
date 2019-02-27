package io.damo.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PicUtil {

    public static String ROOT = "/data/imgupload/";

    /**
     * 判断图片是否是指定类型的图片
     *
     * @param file
     * @return
     */
    public static boolean isPic(MultipartFile file) {

        String fileName = file.getOriginalFilename();

        String[] fileEnd = new String[]{"BMP", "JPG", "JPEG", "PNG", "GIF", "bmp", "jpg", "jpeg", "png", "gif"};
        boolean mark = false;
        for (String str : fileEnd) {
            if (fileName.endsWith(str)) {
                mark = true;
            }
        }
        return mark;
    }

    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    public static StringBuffer updateFileTool(MultipartFile file) throws IOException {
        String fileName = getPicName(file);

        File filePath = Paths.get(ROOT).toFile();
        if(!filePath.exists()){
            filePath.mkdirs();
        }

        Files.copy(file.getInputStream(), Paths.get(ROOT, fileName));
        //图片url

        StringBuffer sb = new StringBuffer("https://web.newpay.mobi").append(Constant.contextPath).append("/image/").append(fileName);
        return sb;
    }

    private static String getPicName(MultipartFile file) {
        StringBuffer sb = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        sb.append(df.format(new Date())).append("_").append(file.getOriginalFilename());
        return sb.toString();
    }
}
