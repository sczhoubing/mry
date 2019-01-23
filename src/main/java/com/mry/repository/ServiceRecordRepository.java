package com.mry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mry.model.ServiceRecord;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Integer> {

}
