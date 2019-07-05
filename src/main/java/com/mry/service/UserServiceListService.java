package com.mry.service;

import com.mry.enums.DateFormat;
import com.mry.model.Common;
import com.mry.model.UserManage;
import com.mry.model.UserServiceList;
import com.mry.repository.UserManageRepository;
import com.mry.repository.UserServiceListRepository;
import com.mry.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
@Service
@Transactional
public class UserServiceListService {
    @Resource
    private UserServiceListRepository userServiceListRepository;
    @Resource
    private UserManageRepository userManageRepository;

    // 添加一条用户服务单记录
    public String addUserServiceList(UserServiceList userServiceList) {
        // 设置当前时间
        try {
            userServiceList.setUpdateDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
            UserServiceList resultUserServiceList = userServiceListRepository.save(userServiceList);
            return resultUserServiceList.getId();
        } catch (RuntimeException e) {
            // 有可能生成的主键重复导致插入失败，
            // 发现异常包含 "Duplicate entry xxxx" 表示是主键的问题
            // 那么就重新插入一次，否则将异常继续抛出
            if(e.getMessage().contains("Duplicate entry")) {
                addUserServiceList(userServiceList);
            } else {
                throw e;
            }
        }
        return null;
    }

    // 编辑一条用户服务单
    public int editUserServiceList(UserServiceList userServiceList) {
        // 设置修改时间
        userServiceList.setUpdateDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
        UserServiceList resultUserServiceList = userServiceListRepository.save(userServiceList);
        return resultUserServiceList.getStoreId();
    }

    // 根据 id 修改一条用户服务单的状态
    public String editUserServiceListStatus(String id, String status) {
         Optional<UserServiceList> optional = userServiceListRepository.findById(id);
         if(!optional.isPresent()) {
             return null;
         }
         UserServiceList userServiceList = optional.get();
         userServiceList.setStatus(status);
         userServiceList.setUpdateDate(CommonUtils.currentDate(DateFormat.FORMAT1.getFormat()));
         userServiceListRepository.save(userServiceList);
         return id;
    }

    // 以单号，顾客，技师 任意条件查询
    public List<UserServiceList> getUserServiceList(int storeId, String param) {
        // 根据单号或技师查询
        List<UserServiceList> list1 = userServiceListRepository.getUserServiceListByLike(storeId, param);
        // 根据用户姓名查询所有用户
        List<UserManage> userManages = userManageRepository.getUserManageByUserName(storeId, param);
        if(!userManages.isEmpty()) {
            // 取出所有用户的 id 属性值
            List<Integer> userIds = userManages.stream().map(u -> u.getId()).collect(Collectors.toList());
            // 根据用户 id 获取所有服务单记录
            List<UserServiceList> list2 = userServiceListRepository.getUserServiceListByUserId(storeId, userIds);
            // 将两个结果集合并
            list1.addAll(list2);
        }
        return list1;
    }

    // 以 storeId + 服务单状态查询
    public List<UserServiceList> getUserServiceListByStatus(int storeId, String status) {
        return userServiceListRepository.getUserServiceListByStatus(storeId, status);
    }

    // 以服务单号删除一条用户服务单
    public int deleteUserServiceListById(int storeId, String id) {
        return userServiceListRepository.deleteUserServiceListById(storeId, id);
    }

    // 以 storeId 和 userId 删除一组用户服务单
    public int deleteUserServiceListsByUserId(int storeId, int userId) {
        return userServiceListRepository.deleteUserServiceListsByUserId(storeId, userId);
    }

    // 以 storeId 删除一组用户服务单
    public int deleteUserServiceListsByStoreId(int storeId) {
        return userServiceListRepository.deleteUserServiceListsByStoreId(storeId);
    }
}
