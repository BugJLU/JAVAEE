package javaee.ole.utils;

import java.util.Random;

public class RandPassword {
	public static String genPass(Integer len){
		String result = "";
		char alpha[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int x = 0; x < len; ++x) {
			sb.append(alpha[r.nextInt(alpha.length)]);
		}
		return result = sb.toString()+result;
	}
	
	public static void main(String[] args) {
		System.out.println(genPass(10));

	}

}
