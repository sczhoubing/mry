package com.mry.param;

import java.util.List;

import com.mry.model.Member;
import com.mry.model.Room;
import com.mry.model.RoomType;

public class RoomParam {
	private int storeId;
	private Room room;
	private List<RoomType> roomTypes;
	private Member member;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<RoomType> getRoomTypes() {
		return roomTypes;
	}
	public void setRoomTypes(List<RoomType> roomTypes) {
		this.roomTypes = roomTypes;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
