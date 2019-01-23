package com.mry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mry.model.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
	@Query(value="select * from instrument where store_id=:storeId", nativeQuery=true)
	public List<Instrument> getInstrumentByStoreId(@Param("storeId")int storeId);

}
