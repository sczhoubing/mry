package com.mry.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ProgramItem;
import com.mry.model.ProgramManage;
import com.mry.param.ProgramManageParam;
import com.mry.repository.ProgramItemRepository;
import com.mry.repository.ProgramManageRepository;

@Service
@Transactional
public class ProgramManageService {
	@Resource
	private ProgramManageRepository programManageRepository;
	@Resource
	private ProgramItemRepository programItemRepository;

	// 添加方案管理信息
	public int addProgramManageInfo(ProgramManageParam params) {
		// 方案基本信息
		ProgramManage programManage = params.getProgramManage();
		ProgramManage originProgramManage = programManageRepository.getProgramManageInfoByProgramName(
				params.getStoreId(), programManage.getProgramType(), programManage.getProgramName());
		// 如果发现有方案名相同的记录则覆盖
		if (null != originProgramManage) {
			programManage.setId(originProgramManage.getId());
			// 将之前方案关联的项目信息删除
			programItemRepository.deleteProgramItemsByProgramId(params.getStoreId(), programManage.getProgramType(),
					programManage.getId());
		}
		programManageRepository.save(programManage);

		// 将项目与方案关联
		List<ProgramItem> programItems = programManage.setProgramIdAndType();
		programItemRepository.saveAll(programItems);
		return params.getStoreId();
	}

	// 根据 storeId 获取所有的方案以及关联的项目信息
	public List<ProgramManage> getProgramManageInfoByStoreId(int storeId, int programType) {
		List<ProgramManage> programManages = programManageRepository.getProgramManageInfoByStoreId(storeId, programType);
		if(programManages.size() > 0) {
			List<ProgramItem> programItems = programItemRepository.getProgramItemsByStoreId(storeId, programType);
			List<ProgramManage> programManageInfo = ProgramManage.bindProgramItem(programManages, programItems);
			return programManageInfo;
		}
		return null;
	}

	// 根据 storeId + programType + programName 获取方案的以及关联的项目信息
	public ProgramManage getProgramManageInfoByProgramName(int storeId, int programType, String programName) {
		ProgramManage programManage = programManageRepository.getProgramManageInfoByProgramName(storeId, programType, programName);
		if (null != programManage) {
			List<ProgramItem> programItems = programItemRepository.getProgramItemsByProgramId(storeId, programType, programManage.getId());
			programManage.setProgramItems(programItems);
		}
		return programManage;
	}

	// 根据 storeId + programType 删除所有的方案以及所有关联的项目
	public int deleteProgramManageInfoByStoreId(int storeId, int programType) {
		int delProgNum = programManageRepository.deleteProgramManageInfoByStoreId(storeId, programType);
		int delItemNum = programItemRepository.deleteProgramItemsByStoreId(storeId, programType);
		return delProgNum + delItemNum;
	}

	// 根据 storeId + programName 删除方案以及关联的项目
	public int deleteProgramManageInfoByProgramName(int storeId, int programType, String programName) {
		ProgramManage programManage = programManageRepository.getProgramManageInfoByProgramName(storeId, programType, programName);
		if (null != programManage) {
			int delProgNum = programManageRepository.deleteProgramManageInfoByProgramName(storeId, programType, programName);
			int delItemNum = programItemRepository.deleteProgramItemsByProgramId(storeId, programType, programManage.getId());
			return delProgNum + delItemNum;
		}
		return 0;
	}
}
