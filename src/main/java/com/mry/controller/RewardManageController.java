package com.mry.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mry.model.ActivityReward;
import com.mry.model.CumPassRange;
import com.mry.model.PassgerReward;
import com.mry.model.TeamReward;
import com.mry.service.RewardManageService;

@RestController
@RequestMapping("/reward/manage")
public class RewardManageController {
	@Resource
	private RewardManageService rewardManageService;
	
	@PostMapping("/passger/add")
	public Map<String, Object> addPassgerRewardInfo(@RequestBody PassgerReward passgerReward) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.addPassgerRewardInfo(passgerReward));
		return result;
	}
	
	@GetMapping("/passger/store/{storeId}")
	public Map<String, Object> getPassgerRewardInfo(@PathVariable("storeId")int storeId, String rewardType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != rewardType) {
			result.put("passgerRewardInfo", rewardManageService.getPassgerRewardByRewardType(storeId, rewardType));
		} else {
			result.put("passgerRewardInfo", rewardManageService.getPassgerRewardByStoreId(storeId));
		}
		return result;
	}
	
	@GetMapping("/passger/delete/{storeId}")
	public Map<String, Object> deletePassgerRewardInfo(@PathVariable("storeId")int storeId, String rewardType) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != rewardType) {
			result.put("msg", rewardManageService.deletePassgerRewardByRewardType(storeId, rewardType));
		} else {
			result.put("msg", rewardManageService.deletePassgerRewardByStoreId(storeId));
		}
		return result;
	}
	
	@PostMapping("/cumpass/add")
	public Map<String, Object> addCumPassRangeInfo(@RequestBody CumPassRange cumPassRange) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.addCumPassRangeInfo(cumPassRange));
		return result;
	}
	
	@PostMapping("/cumpass/edit")
	public Map<String, Object> editCumPassRangeInfo(@RequestBody CumPassRange cumPassRange) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.editCumPassRangeInfo(cumPassRange));
		return result;
	}
	
	@GetMapping("/cumpass/store/{storeId}")
	public Map<String, Object> getCumPassRangeInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("cumPassRangeInfo", rewardManageService.getCumPassRangeByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/cumpass/delete/{storeId}")
	public Map<String, Object> deleteCumPassRangeInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id) {
			result.put("msg", rewardManageService.deleteCumPassRangeById(storeId, id));
		} else {
			result.put("msg", rewardManageService.deleteCumPassRangeByStoreId(storeId));
		}
		return result;
	}
	
	@PostMapping("/team/add")
	public Map<String, Object> addTeamRewardInfo(@RequestBody TeamReward teamReward) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.addTeamRewardInfo(teamReward));
		return result;
	}
	
	@PostMapping("/team/edit")
	public Map<String, Object> editTeamRewardInfo(@RequestBody TeamReward teamReward) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.editTeamRewardInfo(teamReward));
		return result;
	}
	
	@GetMapping("/team/store/{storeId}")
	public Map<String, Object> getTeamRewardInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("teamRewardInfo", rewardManageService.getTeamRewardByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/team/delete/{storeId}")
	public Map<String, Object> deleteTeamRewardInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id) {
			result.put("msg", rewardManageService.deleteTeamRewardById(storeId, id));
		} else {
			result.put("msg", rewardManageService.deleteTeamRewardByStoreId(storeId));
		}
		return result;
	}
	
	@PostMapping("/activity/add")
	public Map<String, Object> addActivityRewardInfo(@RequestBody ActivityReward activityReward) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.addActivityRewardInfo(activityReward));
		return result;
	}
	
	@PostMapping("/activity/edit")
	public Map<String, Object> editActivityRewardInfo(@RequestBody ActivityReward activityReward) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", rewardManageService.editActivityRewardInfo(activityReward));
		return result;
	}
	
	@GetMapping("/activity/store/{storeId}")
	public Map<String, Object> getActivityRewardInfo(@PathVariable("storeId")int storeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("activityRewardInfo", rewardManageService.getActivityRewardByStoreId(storeId));
		return result;
	}
	
	@GetMapping("/activity/delete/{storeId}")
	public Map<String, Object> deleteActivityRewardInfo(@PathVariable("storeId")int storeId, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(null != id) {
			result.put("msg", rewardManageService.deleteActivityRewardById(storeId, id));
		} else {
			result.put("msg", rewardManageService.deleteActivityRewardByStoreId(storeId));
		}
		return result;
	}
}
