package com.mry.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
@Data
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
}
