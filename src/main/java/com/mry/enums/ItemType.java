package com.mry.enums;

public enum ItemType {
	TYPE_FACE("1", "面部"), TYPE_BODY("2", "身体");
	private String code;
	private String desc;
	private ItemType(String code, String desc) {
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
