package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="cosme_tologist")
	private String cosmeTologist;
	@Column(name="therapist")
	private String therapist;
	@Column(name="nurse")
	private String nurse;
	@Column(name="physical_therapist")
	private String physicalTherapist;
	@Column(name="pedicure")
	private String pedicure;
	@Column(name="counselor")
	private String counselor;
	@Column(name="manager")
	private String manager;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getCosmeTologist() {
		return cosmeTologist;
	}
	public void setCosmeTologist(String cosmeTologist) {
		this.cosmeTologist = cosmeTologist;
	}
	public String getTherapist() {
		return therapist;
	}
	public void setTherapist(String therapist) {
		this.therapist = therapist;
	}
	public String getNurse() {
		return nurse;
	}
	public void setNurse(String nurse) {
		this.nurse = nurse;
	}
	public String getPhysicalTherapist() {
		return physicalTherapist;
	}
	public void setPhysicalTherapist(String physicalTherapist) {
		this.physicalTherapist = physicalTherapist;
	}
	public String getPedicure() {
		return pedicure;
	}
	public void setPedicure(String pedicure) {
		this.pedicure = pedicure;
	}
	public String getCounselor() {
		return counselor;
	}
	public void setCounselor(String counselor) {
		this.counselor = counselor;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", storeId=" + storeId + ", cosmeTologist=" + cosmeTologist + ", therapist="
				+ therapist + ", nurse=" + nurse + ", physicalTherapist=" + physicalTherapist + ", pedicure=" + pedicure
				+ ", counselor=" + counselor + ", manager=" + manager + "]";
	}
}
