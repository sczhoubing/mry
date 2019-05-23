package com.mry.model;

import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="city")
@Data
public class City {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String cityName;
	@Column(name="active")
	private String active;
	@Column(name="province_id")
	private int provinceId;
}
