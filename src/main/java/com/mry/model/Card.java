package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card")
public class Card {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="extension_card")
	private String extensionCard;
	@Column(name="guest_card")
	private String guestCard;
	@Column(name="store_id")
	private int storeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExtensionCard() {
		return extensionCard;
	}
	public void setExtensionCard(String extensionCard) {
		this.extensionCard = extensionCard;
	}
	public String getGuestCard() {
		return guestCard;
	}
	public void setGuestCard(String guestCard) {
		this.guestCard = guestCard;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", extensionCard=" + extensionCard + ", guestCard=" + guestCard + ", storeId="
				+ storeId + "]";
	}
}
