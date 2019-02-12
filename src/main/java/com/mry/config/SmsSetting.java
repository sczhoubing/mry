package com.mry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value="classpath:application.properties")
@ConfigurationProperties(prefix="ali")
@Component
public class SmsSetting {
	@Value("${ali.sms.product}")
	private String product;
	@Value("${ali.sms.domain}")
	private String domain;
	@Value("${ali.sms.connTime}")
	private String connTime;
	@Value("${ali.sms.readTime}")
	private String readTime;
	@Value("${ali.sms.accessKeyId}")
	private String accessKeyId;
	@Value("${ali.sms.accessKeySecret}")
	private String accessKeySecret;
	@Value("${ali.sms.templateCode.validCode}")
	private String validCode;
	@Value("${ali.sms.templateCode.remindMsg}")
	private String remindMsg;
	@Value("${ali.sms.templateCode.edPassMsg}")
	private String edPassMsg;
	@Value("${ali.sms.templateCode.registMsg}")
	private String rigstMsg;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getConnTime() {
		return connTime;
	}
	public void setConnTime(String connTime) {
		this.connTime = connTime;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public String getRemindMsg() {
		return remindMsg;
	}
	public void setRemindMsg(String remindMsg) {
		this.remindMsg = remindMsg;
	}
	public String getEdPassMsg() {
		return edPassMsg;
	}
	public void setEdPassMsg(String edPassMsg) {
		this.edPassMsg = edPassMsg;
	}
	public String getRigstMsg() {
		return rigstMsg;
	}
	public void setRigstMsg(String rigstMsg) {
		this.rigstMsg = rigstMsg;
	}
}
