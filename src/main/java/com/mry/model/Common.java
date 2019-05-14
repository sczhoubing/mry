package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author FuDu
 * @date 2019-05-14
 * @desc 系统公共资源存储
 */
@Entity
@Table(name="common")
public class Common {
	@Id
	@Column(name="kiy")
	private long key;
	@Column(name="value")
	private String value;
	@Column(name="update_date")
	private String updateDate;
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Common [key=" + key + ", value=" + value + ", updateDate=" + updateDate + "]";
	}
}
