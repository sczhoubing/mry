package com.mry.param;

import java.util.List;

import com.mry.model.Member;
import com.mry.model.Room;
import com.mry.model.RoomType;
import lombok.Data;

@Data
public class RoomParam {
	private int storeId;
	private Room room;
	private List<RoomType> roomTypes;
	private Member member;
}
