package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_manage")
@Data
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
}
