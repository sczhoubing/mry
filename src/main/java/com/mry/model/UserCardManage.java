package com.mry.model;

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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardOption() {
		return cardOption;
	}
	public void setCardOption(String cardOption) {
		this.cardOption = cardOption;
	}
	public String getCardInitDate() {
		return cardInitDate;
	}
	public void setCardInitDate(String cardInitDate) {
		this.cardInitDate = cardInitDate;
	}
	public String getCardExceDate() {
		return cardExceDate;
	}
	public void setCardExceDate(String cardExceDate) {
		this.cardExceDate = cardExceDate;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardDesc() {
		return cardDesc;
	}
	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
	public Map<String, Object> getCardItem() {
		return cardItem;
	}
	public void setCardItem(Map<String, Object> cardItem) {
		this.cardItem = cardItem;
	}
	@Override
	public String toString() {
		return "UserCardManage [id=" + id + ", storeId=" + storeId + ", userId=" + userId + ", cardType=" + cardType
				+ ", cardOption=" + cardOption + ", cardBalance=" + cardBalance + ", cardInitDate=" + cardInitDate
				+ ", cardExceDate=" + cardExceDate + ", cardStatus=" + cardStatus + ", cardDesc=" + cardDesc + "]";
	}
}
