package io.damo.common.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;

public class AESUtil {

    private static final String IV_STRING = "A-16-Byte-String";
    private static final String charset = "UTF-8";
    private static final String key = "NEW_PAY_JIKE_APP";

    //加密AES-128
    public static String aesEncryptString(String content)  {
        byte[] contentBytes = new byte[0];
        try {
            contentBytes = content.getBytes(charset);
            byte[] keyBytes = key.getBytes(charset);
            byte[] encryptedBytes = new byte[0];
            encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
            Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encryptedBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesDecryptString(String content) {
        Decoder decoder = Base64.getDecoder();
        byte[] encryptedBytes = decoder.decode(content);
        try {
            byte[]  keyBytes = key.getBytes(charset);
            byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
            return new String(decryptedBytes, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
    }

    private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        byte[] initParam = IV_STRING.getBytes(charset);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKey, ivParameterSpec);

        return cipher.doFinal(contentBytes);
    }


    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        String string = aesEncryptString("深圳市");
        System.out.println(string);
        System.out.println(aesDecryptString(string));
    }

}
