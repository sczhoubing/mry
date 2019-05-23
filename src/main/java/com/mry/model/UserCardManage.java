package com.mry.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user_card_manage")
@Data
public class UserCardManage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="store_id")
	private int storeId;
	@Column(name="user_id")
	private int userId;
	@Column(name="card_type")
	private String cardType;
	@Column(name="card_option")
	private String cardOption;
	@Column(name="card_balance")
	private String cardBalance;
	@Column(name="card_init_date")
	private String cardInitDate;
	@Column(name="card_exce_date")
	private String cardExceDate;
	@Column(name="card_status")
	private String cardStatus;
	@Column(name="card_desc")
	private String cardDesc;
	@Transient
	private Map<String, Object> cardItem = new HashMap<String, Object>();
}
