package com.mry.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.model.ActivityReward;
import com.mry.model.CumPassRange;
import com.mry.model.PassgerReward;
import com.mry.model.TeamReward;
import com.mry.repository.ActivityRewardRepository;
import com.mry.repository.CumPassRangeRepository;
import com.mry.repository.PassgerRewardRepository;
import com.mry.repository.TeamRewardRepository;

@Service
@Transactional
public class RewardManageService {
	@Resource
	private PassgerRewardRepository passgerRewardRepository;
	@Resource
	private CumPassRangeRepository cumPassRangeRepository;
	@Resource
	private TeamRewardRepository teamRewardRepository;
	@Resource
	private ActivityRewardRepository activityRewardRepository;
	
	// 保存或覆盖一条客流奖金信息
	public int addPassgerRewardInfo(PassgerReward passgerReward) {
		PassgerReward originPassgerReward = passgerRewardRepository.getPassgerRewardByRewardType(passgerReward.getStoreId(), passgerReward.getRewardType());
		// 如果发现有重复记录就将其覆盖
		if(null != originPassgerReward) {
			passgerReward.setId(originPassgerReward.getId());
		}
		passgerRewardRepository.save(passgerReward);
		return passgerReward.getStoreId();
	}
	
	// 根据 storeId + rewardType 返回一条客流奖金信息
	public PassgerReward getPassgerRewardByRewardType(int storeId, String rewardType) {
		return passgerRewardRepository.getPassgerRewardByRewardType(storeId, rewardType);
	}
	
	// 根据 storeId 返回该门店下所有的客流奖金信息
	public List<PassgerReward> getPassgerRewardByStoreId(int storeId) {
		return passgerRewardRepository.getPassgerRewardByStoreId(storeId);
	}
	
	// 根据 storeId + rewardType 删除一条客流奖金信息
	public int deletePassgerRewardByRewardType(int storeId, String rewardType) {
		return passgerRewardRepository.deletePassgerRewardByRewardType(storeId, rewardType);
	}
	
	// 根据 storeId 删除该门店下所有的客流奖金信息
	public int deletePassgerRewardByStoreId(int storeId) {
		return passgerRewardRepository.deletePassgerRewardByStoreId(storeId);
	}
	
	// 添加一条累计客流区间信息
	public int addCumPassRangeInfo(CumPassRange cumPassRange) {
		List<CumPassRange> cumPassRanges = cumPassRangeRepository.getCumPassRangeByStoreId(cumPassRange.getStoreId());
		CumPassRange duplicateCumPassRange = CumPassRange.validDuplicateCumPassRange(cumPassRange, cumPassRanges);
		// 如果有重复记录就将其覆盖
		if(null != duplicateCumPassRange) {
			cumPassRange.setId(duplicateCumPassRange.getId());
		}
		cumPassRangeRepository.save(cumPassRange);
		return cumPassRange.getStoreId();
	}
	
	// 修改一条累计客流区间信息
	public int editCumPassRangeInfo(CumPassRange cumPassRange) {
		cumPassRangeRepository.save(cumPassRange);
		return cumPassRange.getId();
	}
	
	// 根据 storeId 返回该门店下所有的累计客流区间信息
	public List<CumPassRange> getCumPassRangeByStoreId(int storeId) {
		return cumPassRangeRepository.getCumPassRangeByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除一条累计客流区间信息
	public int deleteCumPassRangeById(int storeId, int id) {
		return cumPassRangeRepository.deleteCumPassRangeById(storeId, id);
	}
	
	// 根据 storeId 删除该门店下所有累计客流区间信息
	public int deleteCumPassRangeByStoreId(int storeId) {
		return cumPassRangeRepository.deleteCumPassRangeByStoreId(storeId);
	}
	
	// 添加一条团队奖金信息
	public int addTeamRewardInfo(TeamReward teamReward) {
		List<TeamReward> teamRewards = teamRewardRepository.getTeamRewardByStoreId(teamReward.getStoreId());
		TeamReward duplicateTeamReward = TeamReward.validDuplicateTeamReward(teamReward, teamRewards);
		// 如果有重复记录就将其覆盖
		if(null != duplicateTeamReward) {
			teamReward.setId(duplicateTeamReward.getId());
		}
		teamRewardRepository.save(teamReward);
		return teamReward.getStoreId();
	}
	
	// 修改一条团队奖金信息
	public int editTeamRewardInfo(TeamReward teamReward) {
		teamRewardRepository.save(teamReward);
		return teamReward.getId();
	}
	
	// 根据 storeId 返回该门店下所有团队奖金信息
	public List<TeamReward> getTeamRewardByStoreId(int storeId) {
		return teamRewardRepository.getTeamRewardByStoreId(storeId);
	}
	
	// 根据 storeId + id 删除一条团队奖金信息
	public int deleteTeamRewardById(int storeId, int id) {
		return teamRewardRepository.deleteTeamRewardById(storeId, id);
	}
	
	// 根据 storeId 删除该门店下所有团队奖金信息
	public int deleteTeamRewardByStoreId(int storeId) {
		return teamRewardRepository.deleteTeamRewardByStoreId(storeId);
	}
	
	// 添加一条活动奖金信息
	public int addActivityRewardInfo(ActivityReward activityReward) {
		List<ActivityReward> activityRewards = activityRewardRepository.getActivityRewardByStoreId(activityReward.getStoreId());
		ActivityReward duplicateActivityReward = ActivityReward.validDuplicateActivityReward(activityReward, activityRewards);
		// 如果有重复记录就将其覆盖
		if(null != duplicateActivityReward) {
			activityReward.setId(duplicateActivityReward.getId());
		}
		activityRewardRepository.save(activityReward);
		return activityReward.getStoreId();
	}
	
	// 根据 storeId 返回该门店下所有活动奖金信息
	public List<ActivityReward> getActivityRewardByStoreId(int storeId) {
		return activityRewardRepository.getActivityRewardByStoreId(storeId);
	}
	
	// 修改一条活动奖金信息
	public int editActivityRewardInfo(ActivityReward activityReward) {
		activityRewardRepository.save(activityReward);
		return activityReward.getId();
	}
	
	// 根据 storeId + id 删除一条活动奖金信息
	public int deleteActivityRewardById(int storeId, int id) {
		return activityRewardRepository.deleteActivityRewardById(storeId, id);
	}
	
	// 根据 storeId 删除该门店下所有活动奖金信息
	public int deleteActivityRewardByStoreId(int storeId) {
		return activityRewardRepository.deleteActivityRewardByStoreId(storeId);
	}
}
