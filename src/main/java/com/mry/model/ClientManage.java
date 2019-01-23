package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_manage")
public class ClientManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="exclusiveness")
	private String exclusiveness;
	@Column(name="technician_serve")
	private String technicianServe;
	@Column(name="technician_serve_times")
	private String technicianServeTimes;
	@Column(name="designated_cli_intro")
	private String designatedCliIntro;
	@Column(name="technician_dir_exten")
	private String technicianDirExten;
	@Column(name="client_complaint")
	private String clientComplaint;
	@Column(name="continuous_serve")
	private String continuousServe;
	@Column(name="continuous_serve_times")
	private String continuousServeTimes;
	@Column(name="client_con_nostore")
	private String clientConNostore;
	@Column(name="client_con_nostore_moth")
	private String clientConNostoreMoth;
	@Column(name="client_con_nocash")
	private String clientConNocash;
	@Column(name="client_con_nocash_moth")
	private String clientConNocashMoth;
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
	public String getExclusiveness() {
		return exclusiveness;
	}
	public void setExclusiveness(String exclusiveness) {
		this.exclusiveness = exclusiveness;
	}
	public String getTechnicianServe() {
		return technicianServe;
	}
	public void setTechnicianServe(String technicianServe) {
		this.technicianServe = technicianServe;
	}
	public String getTechnicianServeTimes() {
		return technicianServeTimes;
	}
	public void setTechnicianServeTimes(String technicianServeTimes) {
		this.technicianServeTimes = technicianServeTimes;
	}
	public String getDesignatedCliIntro() {
		return designatedCliIntro;
	}
	public void setDesignatedCliIntro(String designatedCliIntro) {
		this.designatedCliIntro = designatedCliIntro;
	}
	public String getTechnicianDirExten() {
		return technicianDirExten;
	}
	public void setTechnicianDirExten(String technicianDirExten) {
		this.technicianDirExten = technicianDirExten;
	}
	public String getClientComplaint() {
		return clientComplaint;
	}
	public void setClientComplaint(String clientComplaint) {
		this.clientComplaint = clientComplaint;
	}
	public String getContinuousServe() {
		return continuousServe;
	}
	public void setContinuousServe(String continuousServe) {
		this.continuousServe = continuousServe;
	}
	public String getContinuousServeTimes() {
		return continuousServeTimes;
	}
	public void setContinuousServeTimes(String continuousServeTimes) {
		this.continuousServeTimes = continuousServeTimes;
	}
	public String getClientConNostore() {
		return clientConNostore;
	}
	public void setClientConNostore(String clientConNostore) {
		this.clientConNostore = clientConNostore;
	}
	public String getClientConNostoreMoth() {
		return clientConNostoreMoth;
	}
	public void setClientConNostoreMoth(String clientConNostoreMoth) {
		this.clientConNostoreMoth = clientConNostoreMoth;
	}
	public String getClientConNocash() {
		return clientConNocash;
	}
	public void setClientConNocash(String clientConNocash) {
		this.clientConNocash = clientConNocash;
	}
	public String getClientConNocashMoth() {
		return clientConNocashMoth;
	}
	public void setClientConNocashMoth(String clientConNocashMoth) {
		this.clientConNocashMoth = clientConNocashMoth;
	}
}
