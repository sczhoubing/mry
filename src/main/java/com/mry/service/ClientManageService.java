package com.mry.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ClientClassify;
import com.mry.model.ClientManage;
import com.mry.model.ClientRule;
import com.mry.param.ClientManageParam;
import com.mry.repository.ClientClassifyRepository;
import com.mry.repository.ClientManageRepository;
import com.mry.repository.ClientRuleRepository;

@Service
@Transactional
public class ClientManageService {
	@Resource
	private ClientManageRepository clientManageRepository;
	@Resource
	private ClientRuleRepository clientRuleRepository;
	@Resource
	private ClientClassifyRepository clientClassifyRepository;
	
	// 添加顾客管理信息
	public int addClientManageInfo(ClientManageParam params) {
		// 顾客管理信息
		ClientManage clientManage = params.getClientManage();
		ClientManage originClientManage = clientManageRepository.getClientManageByStoreId(params.getStoreId());
		// 如果重复提交将覆盖原来的顾客管理信息
		if(null != originClientManage) {
			clientManage.setId(originClientManage.getId());
		}
		clientManageRepository.save(clientManage);
		
		// 顾客规则
		ClientRule clientRule = params.getClientRule();
		ClientRule originClientRule = clientRuleRepository.getClientRuleByStoreId(params.getStoreId());
		// 如果重复提交将覆盖原来的顾客规则信息
		if(null != originClientRule) {
			clientRule.setId(originClientRule.getId());
		}
		clientRuleRepository.save(clientRule);
		
		// 顾客分类
		ClientClassify clientClassify = params.getClientClassify();
		ClientClassify originClientClassify = clientClassifyRepository.getClientClassifyByStoreId(params.getStoreId());
		// 如果重复提交将覆盖原来的顾客分类信息
		if(null != originClientClassify) {
			clientClassify.setId(originClientClassify.getId());
		}
		clientClassifyRepository.save(clientClassify);
		
		return params.getStoreId();
	}
	
	// 查询顾客管理信息
	public ClientManageParam getClientManageByStoreId(int storeId) {
		ClientManageParam clientManageInfo = new ClientManageParam();
		clientManageInfo.setStoreId(storeId);
		// 顾客管理信息 
		ClientManage clientManage = clientManageRepository.getClientManageByStoreId(storeId);
		clientManageInfo.setClientManage(clientManage);
		// 顾客规则
		ClientRule clientRule = clientRuleRepository.getClientRuleByStoreId(storeId);
		clientManageInfo.setClientRule(clientRule);
		// 顾客分类
		ClientClassify clientClassify = clientClassifyRepository.getClientClassifyByStoreId(storeId);
		clientManageInfo.setClientClassify(clientClassify);
		return clientManageInfo;
	}
}
