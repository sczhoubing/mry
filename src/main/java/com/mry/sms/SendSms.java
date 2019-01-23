package com.mry.sms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.mry.config.SmsSetting;

@Component
public class SendSms {
	@Resource
	private SmsSetting smsSetting;
	
	// 发送短信验证码
    public Map<String, Object> sendSms(String phoneNumber, String code) {
    	Map<String, Object> result = new HashMap<String, Object>();
        System.setProperty("sun.net.client.defaultConnectTimeout", smsSetting.getConnTime());
        System.setProperty("sun.net.client.defaultReadTimeout", smsSetting.getReadTime());
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsSetting.getAccessKeyId(), smsSetting.getAccessKeySecret());
        try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", smsSetting.getProduct(), smsSetting.getDomain());
			IAcsClient acsClient = new DefaultAcsClient(profile);
	        SendSmsRequest request = new SendSmsRequest();
	        request.setPhoneNumbers(phoneNumber);
	        request.setSignName("华杨汇美");
	        request.setTemplateCode(smsSetting.getTemplateCode());
	        request.setTemplateParam("{\"code\":\"" + code + "\"}");
	        
	        SendSmsResponse response = acsClient.getAcsResponse(request);
	        result.put("code", response.getCode());
	        result.put("message", response.getMessage());
		} catch (ClientException e) {
			result.put("error", "发送短信验证码失败!");
		}
        return result;
    }
}
