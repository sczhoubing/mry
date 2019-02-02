package com.mry.enums;

public enum LogFile {
	LOG_ADDIR("/alidata/log/mry"), LOG_GZ(".gz");
	
	private String info;

	private LogFile(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
}
