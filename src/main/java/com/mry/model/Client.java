package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
@Data
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="rc_count")
	private String rcCount;
	@Column(name="tmo_count")
	private String tmoCount;
	@Column(name="omo_count")
	private String omoCount;
	@Column(name="omt_count")
	private String omtCount;
	@Column(name="omth_count")
	private String omthCount;
	@Column(name="omf_count")
	private String omfCount;
	@Column(name="avg_month_performance")
	private String avgMonPerformance;
	@Column(name="month_nclient_performance")
	private String monNcliPerformance;
	@Column(name="month_oclient_performance")
	private String monOcliPerformance;
	@Column(name="store_id")
	private int storeId;
}
