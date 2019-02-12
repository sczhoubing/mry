package com.mry.enums;

public enum DateFormat {
	FORMAT1("yyyy-MM-dd hh:mm:ss"), FORMAT2("yyyy/MM/dd hh:mm:ss");
	private String format;
	private DateFormat(String format) {
		this.format = format;
	}
	public String getFormat() {
		return format;
	}
}
