package com.mry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.mry.service.CustomerService;
import com.mry.sms.SendSms;
import com.mry.utils.CommonUtils;

// 手机验证码校验
@RestController
@RequestMapping("/verification")
public class VerificationController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private SendSms sendSms;
	
	@GetMapping("/code/{account}")
	public Map<String, Object> sendSmsCode(@PathVariable("account")String account, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 发送验证码
		String smsCode = CommonUtils.getRandomNumber();
		Map<String, Object> response = sendSms.sendSms(account, smsCode);
		if(response.get("code").equals("OK")) {
			// 保存验证码 5 分钟
			session.setAttribute(account, smsCode);
			session.setMaxInactiveInterval(60 * 5);
			result.put("msg", 0);
			logger.info("send sms code: " + smsCode);
		} else {
			// 发送验证码失败
			result.put("msg", 1);
			result.put("error", response.get("message"));
			logger.info("send sms code fail: " + response.get("message"));
		}
		return result;
	}
	
	@PostMapping("/valid")
	public Map<String, Object> validCode(@RequestBody JSONObject params, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		String account = params.getString("account");
		String code = params.getString("code");
		String smsCode = (String) session.getAttribute(account);
		if(null != smsCode && smsCode.equals(code)) {
			result.put("msg", 0);
		} else {
			result.put("msg", 1);
		}
		return result;
	}
	
}
