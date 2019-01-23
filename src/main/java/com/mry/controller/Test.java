package com.mry.controller;

import java.sql.SQLException;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
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

	//Logger logger = LoggerFactory.getLogger(getClass());
	/*@GetMapping("/test1")
	public void test1() {
		
		SendSmsResponse response = sendSms.sendSms("15208325491", "66666");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());
		//logger.info("test logger ......");
	}*/
	
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> result = sendSms.sendSms("15208325491", "666666");
		return result;
	}
}
