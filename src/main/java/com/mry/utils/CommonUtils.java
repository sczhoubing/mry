package com.mry.utils;

import java.math.BigDecimal;
import java.util.Random;

public class CommonUtils {
	
	// 返回 6 位随机数字
	public static String getRandomNumber() {
		StringBuffer flag = new StringBuffer();
		String sources = "0123456789";
		Random rand = new Random();
		for (int j = 0; j < 6; j++) {
			flag.append(sources.charAt(rand.nextInt(9)) + "");
		}
		return flag.toString();
	}
	
	// 判断字符串不为空
	public static boolean isBlank(String str) {
		if(null != str && !"".equals(str)) {
			return false;
		} 
		return true;
	}
	
	// 双精度四则运算
	public static double doubleCalculation(double numA, double numB, String operate){
		double res = 0;
		BigDecimal bigA = new BigDecimal(Double.toString(numA));
		BigDecimal bigB = new BigDecimal(Double.toString(numB));
		switch (operate) {
			case "+":
				res = bigA.add(bigB).doubleValue();
				break;
			case "-":
				res = bigA.subtract(bigB).doubleValue();
				break;
			case "*":
				res = bigA.multiply(bigB).doubleValue();
				break;
			case "/":
				res = bigA.divide(bigB).doubleValue();
				break;
			default :
				break;
		}
		return res;
	}
	
	public static void main(String[] args) {
		double s = doubleCalculation(6.5, 5.23,"+");
		System.out.println(s);
	}
}
