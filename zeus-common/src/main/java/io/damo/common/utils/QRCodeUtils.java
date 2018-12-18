package io.damo.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import sun.misc.BASE64Encoder;

/**
 * 生成二维码
 * @author qq
 *
 */

public class QRCodeUtils {

	@SuppressWarnings("unchecked")
	public static String QRCode(int width,int height,String content){
        String format = "png";  
        @SuppressWarnings("rawtypes")
		Hashtable hints = new Hashtable();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = null;  
        try {  
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);  
        ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。  
        try {
			ImageIO.write(image, format, os);
		} catch (IOException e) {
			e.printStackTrace();
		}//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。  
        byte b[] = os.toByteArray();//从流中获取数据数组。  
        String str = new BASE64Encoder().encode(b);  
		return str;
	}
}
