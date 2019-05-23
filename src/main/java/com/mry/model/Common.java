package com.mry.model;

import lombok.Data;

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
@Data
public class Common {
	@Id
	@Column(name="kiy")
	private long key;
	@Column(name="value")
	private String value;
	@Column(name="update_date")
	private String updateDate;
}
