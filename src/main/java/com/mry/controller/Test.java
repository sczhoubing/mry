package com.mry.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.enums.DateFormat;
import com.mry.model.CustomerIpAddress;
import com.mry.repository.CustomerIpAddressRepository;
import com.mry.service.CustomerIpAddressService;
import com.mry.sms.SendSms;
import com.mry.utils.CommonUtils;

@RestController
@RequestMapping("/test")
public class Test {
	@Resource
	private CustomerIpAddressService customerIpAddressService;
	@Resource
	private CustomerIpAddressRepository customerIpAddressRepository;

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
			// 加上一些字母，就可以生成pc站的验证码了
			String sources = "0123456789"; 
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
	
	@GetMapping("/test4/{account}")
	public Map<String, Object> test4(@PathVariable("account")String account) {
		String message = "{\"userName\":\"" + "dufu" + "\",\"password\":\"" + "123456" + "\",\"storeName\":\"" + "太太乐" + "\"}";
		Map<String, Object> result = sendSms.sendSms(account, "SMS_157448850", message);
		return result;
	}
	
	@GetMapping("/test5/{account}")
	public Map<String, Object> test5(@PathVariable("account")String account) {
		String message = "{\"userName\":\"" + "dufu" + "\",\"password\":\"" + "123456" + "\"}";
		Map<String, Object> result = sendSms.sendSms(account, "SMS_157448853", message);
		return result;
	}
	
	@GetMapping("/test6")
	public Map<String, Object> test6(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("remoteIpAddress", CommonUtils.getIpAddr(request));
		return result;
	}
	
	@GetMapping("/test7")
	public Map<String, Object> test7() {
		Map<String, Object> result = new HashMap<String, Object>();
		customerIpAddressService.editCustomerIpAddressByCustomerId(1, "123456");
		result.put("msg", 1);
		return result;
	}
	
	@GetMapping("/test8")
	public Map<String, Object> test8(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		CustomerIpAddress customerIpAddress = new CustomerIpAddress();
		customerIpAddress.setCustomerId(3);
		customerIpAddress.setIpAddress(CommonUtils.getIpAddr(request));
		customerIpAddress.setRecordDate(CommonUtils.formatDate(new Date(), DateFormat.FORMAT1.getFormat()));
		customerIpAddressRepository.save(customerIpAddress);
		result.put("customerIpAddress", customerIpAddress);
		return result;
	}
	
}
