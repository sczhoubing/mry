package com.mry.controller;

import java.sql.SQLException;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.sms.SendSms;

@RestController
@RequestMapping("/test")
public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*Class.forName("com.mysql.jdbc.Driver");
		Connection connection = (Connection) DriverManager
				.getConnection("jdbc:mysql://116.62.201.135:3306/mry?characterEncoding=utf8", "hyzx", "hyzx123");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select name, is_active, province_id from city");
		while (rs.next()) {
			System.out.println("insert into city(name, active, province_id) values('" + rs.getString("name") + "', '"
					+ rs.getBoolean("is_active") + "', " + rs.getInt("province_id") + ");");
		}*/
		//System.out.println(CommonUtils.getRandomNumber());
	}

	@Resource
	private SendSms sendSms;

	@GetMapping("/test")
	public void test() {
		for (int i = 0; i <= 100; i++) {
			String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
			Random rand = new Random();
			StringBuffer flag = new StringBuffer();
			for (int j = 0; j < 6; j++) {
				flag.append(sources.charAt(rand.nextInt(9)) + "");
			}
			System.out.println(flag.toString());
		}

	}
	
	@GetMapping("/test2/{account}")
	public Map<String, Object> test2(@PathVariable("account")String account) {
		Map<String, Object> result = sendSms.sendSms(account, "SMS_146290287", "{\"code\":\"" + 666666 + "\"}");
		return result;
	}
	
	@GetMapping("/test3/{account}")
	public Map<String, Object> test3(@PathVariable("account")String account) {
		String message = "{\"userName\":\"" + account + "\",\"password\":\"" + 123456 + "\"}";
		Map<String, Object> result = sendSms.sendSms(account, "SMS_157448717", message);
		return result;
	}
}
