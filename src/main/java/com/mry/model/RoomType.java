package com.mry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mry.utils.CommonUtils;
import lombok.Data;

@Entity
@Table(name="room_type")
@Data
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

	// 计算房间总数
	public int getRoomNumbers() {
		int count = 0;
		String[] types = new String[]{onlyShower, onlyToilet, onlyBubble, showerAndToilet, bubbleAndToilet,
				showerAndBubble, showerAndToiletAndBubble, noFacilities};
		for(String type : types) {
			if(!CommonUtils.isBlank(type)) {
				count += Integer.parseInt(type);
			}
		}
		return count;
	}
}
