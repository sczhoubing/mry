package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mry.utils.CommonUtils;

@Entity
@Table(name="room_type")
public class RoomType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="type")
	private String type;
	@Column(name="only_shower")
	private String onlyShower;
	@Column(name="only_toilet")
	private String onlyToilet;
	@Column(name="only_bubble")
	private String onlyBubble;
	@Column(name="shower_toilet")
	private String showerAndToilet;
	@Column(name="bubble_toilet")
	private String bubbleAndToilet;
	@Column(name="shower_bubble")
	private String showerAndBubble;
	@Column(name="shower_toilet_bubble")
	private String showerAndToiletAndBubble;
	@Column(name="no_facilities")
	private String noFacilities;
	@Column(name="store_id")
	private int storeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOnlyShower() {
		return onlyShower;
	}
	public void setOnlyShower(String onlyShower) {
		this.onlyShower = onlyShower;
	}
	public String getOnlyToilet() {
		return onlyToilet;
	}
	public void setOnlyToilet(String onlyToilet) {
		this.onlyToilet = onlyToilet;
	}
	public String getOnlyBubble() {
		return onlyBubble;
	}
	public void setOnlyBubble(String onlyBubble) {
		this.onlyBubble = onlyBubble;
	}
	public String getShowerAndToilet() {
		return showerAndToilet;
	}
	public void setShowerAndToilet(String showerAndToilet) {
		this.showerAndToilet = showerAndToilet;
	}
	public String getBubbleAndToilet() {
		return bubbleAndToilet;
	}
	public void setBubbleAndToilet(String bubbleAndToilet) {
		this.bubbleAndToilet = bubbleAndToilet;
	}
	public String getShowerAndBubble() {
		return showerAndBubble;
	}
	public void setShowerAndBubble(String showerAndBubble) {
		this.showerAndBubble = showerAndBubble;
	}
	public String getShowerAndToiletAndBubble() {
		return showerAndToiletAndBubble;
	}
	public void setShowerAndToiletAndBubble(String showerAndToiletAndBubble) {
		this.showerAndToiletAndBubble = showerAndToiletAndBubble;
	}
	public String getNoFacilities() {
		return noFacilities;
	}
	public void setNoFacilities(String noFacilities) {
		this.noFacilities = noFacilities;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "RoomType [id=" + id + ", type=" + type + ", onlyShower=" + onlyShower + ", onlyToilet=" + onlyToilet
				+ ", onlyBubble=" + onlyBubble + ", showerAndToilet=" + showerAndToilet + ", bubbleAndToilet="
				+ bubbleAndToilet + ", showerAndBubble=" + showerAndBubble + ", showerAndToiletAndBubble="
				+ showerAndToiletAndBubble + ", noFacilities=" + noFacilities + ", storeId=" + storeId + "]";
	}
	// 计算房间总数
	public int getRoomNumbers() {
		int count = 0;
		String[] types = new String[]{onlyShower, onlyToilet, onlyBubble, showerAndToilet, bubbleAndToilet, showerAndBubble, showerAndToiletAndBubble, noFacilities};
		for(String type : types) {
			if(!CommonUtils.isBlank(type)) {
				count += Integer.parseInt(type);
			}
		}
		return count;
	}
}
