package javaee.ole.utils;

import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
/**
* @param src  ´ý¼ÓÃÜµÄ×Ö·û´®
* @return ¼ÓÃÜºóµÄ×Ö·û´®
*/
public class MD5Enc {
	public static String encodeMD5(String src){
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			String result = base64en.encode(md5.digest(src.getBytes("utf-8")));
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return src;
		}
	}
	public static void main(String[] args) {
		System.out.println(encodeMD5("abc"));

	}

}
