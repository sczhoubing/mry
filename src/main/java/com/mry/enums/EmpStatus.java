package com.mry.enums;

public enum EmpStatus {
	LEAVE("5", "离职状态");
	private String code;
	private String desc;
	private EmpStatus(String code, String desc) {
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
