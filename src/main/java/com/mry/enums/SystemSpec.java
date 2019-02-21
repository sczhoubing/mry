package com.mry.enums;

public enum SystemSpec {
	SEPARATOR2("@", "用户项目类别参数分割符");
	private String code;
	private String desc;
	private SystemSpec(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
