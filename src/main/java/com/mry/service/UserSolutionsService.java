package com.mry.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mry.repository.UserSolutionsRepository;

@Service
@Transactional
public class UserSolutionsService {
	@Resource
	private UserSolutionsRepository userSolutionsRepository;
	
	// 修改用户关联方案的状态
	public int editUserSolutionsStatus(int id, String status) {
		userSolutionsRepository.editUserSolutionsStatus(id, status);
		return id;
	}
}
