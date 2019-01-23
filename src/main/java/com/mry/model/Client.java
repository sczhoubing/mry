package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRcCount() {
		return rcCount;
	}
	public void setRcCount(String rcCount) {
		this.rcCount = rcCount;
	}
	public String getTmoCount() {
		return tmoCount;
	}
	public void setTmoCount(String tmoCount) {
		this.tmoCount = tmoCount;
	}
	public String getOmoCount() {
		return omoCount;
	}
	public void setOmoCount(String omoCount) {
		this.omoCount = omoCount;
	}
	public String getOmtCount() {
		return omtCount;
	}
	public void setOmtCount(String omtCount) {
		this.omtCount = omtCount;
	}
	public String getOmthCount() {
		return omthCount;
	}
	public void setOmthCount(String omthCount) {
		this.omthCount = omthCount;
	}
	public String getOmfCount() {
		return omfCount;
	}
	public void setOmfCount(String omfCount) {
		this.omfCount = omfCount;
	}
	public String getAvgMonPerformance() {
		return avgMonPerformance;
	}
	public void setAvgMonPerformance(String avgMonPerformance) {
		this.avgMonPerformance = avgMonPerformance;
	}
	public String getMonNcliPerformance() {
		return monNcliPerformance;
	}
	public void setMonNcliPerformance(String monNcliPerformance) {
		this.monNcliPerformance = monNcliPerformance;
	}
	public String getMonOcliPerformance() {
		return monOcliPerformance;
	}
	public void setMonOcliPerformance(String monOcliPerformance) {
		this.monOcliPerformance = monOcliPerformance;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", rcCount=" + rcCount + ", tmoCount=" + tmoCount + ", omoCount=" + omoCount
				+ ", omtCount=" + omtCount + ", omthCount=" + omthCount + ", omfCount=" + omfCount
				+ ", avgMonPerformance=" + avgMonPerformance + ", monNcliPerformance=" + monNcliPerformance
				+ ", monOcliPerformance=" + monOcliPerformance + ", storeId=" + storeId + "]";
	}
}
