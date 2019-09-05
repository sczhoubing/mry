package com.mry.utils;

import com.mry.enums.DateFormat;
import com.mry.exception.CommonException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

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
	
	// 根据传递的日期返回格式化的日期字符串
	public static String formatDate(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	// 将传递的日期字符串转化为日期格式
	public static Date parseDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.FORMAT1.getFormat());
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new CommonException(500, e.getMessage());
		}
	}

	// 将传递的日期字符串转化为日期格式
	public static Date parseDate(String date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new CommonException(500, e.getMessage());
		}
	}

	public static void main(String[] args) {
		String date1 = "2019-09-04";
		String date2 = "2019-09-04";
		System.out.println(afterDate(date1, date2));
	}

	// 返回指定格式的当前时间
	public static String currentDate(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}

	// 校验传递的时间是否在当前时间之后(判断是否过期)
	public static boolean validExpireDate(String date) {
		String dataFormat = getDateFormat(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
		Date date1 = null, date2 = null;
		try {
			date1 = simpleDateFormat.parse(date);
			date2 = simpleDateFormat.parse(currentDate(dataFormat));
		} catch (ParseException e) {
			throw new CommonException(500, e.getMessage());
		}
		return date1.after(date2);
	}

	// 判断 DateA 是否在 DateB 之后
	public static boolean afterDate(String DateA, String DateB) {
		String dataFormat = getDateFormat(DateA);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
		Date date1 = null, date2 = null;
		try {
			date1 = simpleDateFormat.parse(DateA);
			date2 = simpleDateFormat.parse(DateB);
		} catch (ParseException e) {
			throw new CommonException(500, e.getMessage());
		}
		return date1.after(date2);
	}

	// 根据传递的日期，判断其日期格式
	public static String getDateFormat(String date) {
		if(date.contains("-") && date.contains(":")) {
			return DateFormat.FORMAT1.getFormat();
		} else if(date.contains("-") && !date.contains(":")) {
			return DateFormat.FORMAT4.getFormat();
		} else if(date.contains("/") && date.contains(":")) {
			return DateFormat.FORMAT2.getFormat();
		} else if(date.contains("/") && !date.contains(":")) {
			return DateFormat.FORMAT5.getFormat();
		}
		return DateFormat.FORMAT1.getFormat();
	}

	// 日期计算
	public static String calculateDate(Date date, Integer flag, Integer bounds) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(flag, bounds);
		return CommonUtils.formatDate(calendar.getTime(), DateFormat.FORMAT1.getFormat());
	}

	// 校验字符串是否为空且不为 undefined
	public static boolean validStr(String str) {
		return !StringUtils.isEmpty(str) && !"undefined".equals(str) ? true : false;
	}

	/** 
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址, 
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); 
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.indexOf(",") != -1){
                ip = ip.split(",")[0];
            }
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Real-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        } 
        return ip;  
    }
}
