package com.mry.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mry.model.BedInfoManage;

public interface BedInfoManageRepository extends JpaRepository<BedInfoManage, Integer> {
	@Query(value="select * from bed_info_manage where store_id=:storeId and bed_num=:bedNum and bed_name=:bedName", nativeQuery=true)
	public BedInfoManage getBedInfoManageByBedNumAndBedName(@Param("storeId")int storeId, @Param("bedNum")String bedNum, @Param("bedName")String bedName);
	
	@Query(value="select * from bed_info_manage where store_id=:storeId and id=:id", nativeQuery=true)
	public BedInfoManage getBedInfoManageById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="select * from bed_info_manage where store_id=:storeId and (start_time not between :startTime and :endTime) and (end_time not between :startTime and :endTime)", nativeQuery=true)
	public List<BedInfoManage> getBedInfoManageByStartTimeAndEndTime(@Param("storeId")int storeId, @Param("startTime")String startTime, @Param("endTime")String endTime);
	
	@Query(value="select * from bed_info_manage where store_id=:storeId and bed_num=:bedNum", nativeQuery=true)
	public List<BedInfoManage> getBedInfoManageByBedNum(@Param("storeId")int storeId, @Param("bedNum")String bedNum);
	
	@Query(value="select * from bed_info_manage where store_id=:storeId and bed_name=:bedName", nativeQuery=true)
	public List<BedInfoManage> getBedInfoManageByBedName(@Param("storeId")int storeId, @Param("bedName")String bedName);
	
	@Query(value="select * from bed_info_manage where store_id=:storeId", nativeQuery=true)
	public List<BedInfoManage> getBedInfoManageByStoreId(@Param("storeId")int storeId);
	
	@Query(value="update bed_info_manage set start_time=:startTime, end_time=:endTime where id=:id", nativeQuery=true)
	@Modifying
	public void markOccupancyTime(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("id")int id);
	
	@Query(value="delete from bed_info_manage where store_id=:storeId and id=:id", nativeQuery=true)
	@Modifying
	public int deleteBedInfoManageById(@Param("storeId")int storeId, @Param("id")int id);
	
	@Query(value="delete from bed_info_manage where store_id=:storeId and bed_num=:bedNum and bed_name=:bedName", nativeQuery=true)
	@Modifying
	public int deleteBedInfoManageByBedNumAndBedName(@Param("storeId")int storeId, @Param("bedNum")String bedNum, @Param("bedName")String bedName);
	
	@Query(value="delete from bed_info_manage where store_id=:storeId and bed_num=:bedNum", nativeQuery=true)
	@Modifying
	public int deleteBedInfoManageByBedNum(@Param("storeId")int storeId, @Param("bedNum")String bedNum);
	
	@Query(value="delete from bed_info_manage where store_id=:storeId and bed_name=:bedName", nativeQuery=true)
	@Modifying
	public int deleteBedInfoManageByBedName(@Param("storeId")int storeId, @Param("bedName")String bedName);
	
	@Query(value="delete from bed_info_manage where store_id=:storeId", nativeQuery=true)
	@Modifying
	public int deleteBedInfoManageByStoreId(@Param("storeId")int storeId);
}
