package com.mry.service;

import com.mry.enums.DateFormat;
import com.mry.model.ComplaintManage;
import com.mry.repository.ComplaintManageRepository;
import com.mry.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ComplaintManageService {
    @Resource
    private ComplaintManageRepository complaintManageRepository;

    // 添加/编辑 一条投诉记录
    public int addComplaintManage(ComplaintManage complaintManage) {
        // 设置当前时间为创建时间或更改时间
        if(StringUtils.isEmpty(complaintManage.getUpdateDate())) {
            complaintManage.setUpdateDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
        }
        complaintManageRepository.save(complaintManage);
        return complaintManage.getStoreId();
    }

    // 根据 storeId + id 查询一条投诉记录
    public ComplaintManage getComplaintManageById(int storeId, int id) {
        return complaintManageRepository.getComplaintManageById(storeId, id);
    }

    // 根据 storeId + userId 查询一组投诉记录
    public List<ComplaintManage> getComplaintManageByUserId(int storeId, int userId) {
        return complaintManageRepository.getComplaintManageByUserId(storeId, userId);
    }

    // 根据 storeId 查询一组投诉记录
    public List<ComplaintManage> getComplaintManageByStoreId(int storeId) {
        return complaintManageRepository.getComplaintManageByStoreId(storeId);
    }

    // 根据 storeId + id 删除一条投诉记录
    public int deleteComplaintManageById(int storeId, int id) {
        return complaintManageRepository.deleteComplaintManageById(storeId, id);
    }

    // 根据 storeId + userId 删除一组投诉记录
    public int deleteComplaintManageByUserId(int storeId, int userId) {
        return complaintManageRepository.deleteComplaintManageByUserId(storeId, userId);
    }

    // 根据 storeId 删除一组投诉记录
    public int deleteComplaintManageByStoreId(int storeId) {
        return complaintManageRepository.deleteComplaintManageByStoreId(storeId);
    }
}
