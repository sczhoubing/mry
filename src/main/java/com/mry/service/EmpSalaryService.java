package com.mry.service;

import com.mry.exception.CommonException;
import com.mry.model.*;
import com.mry.repository.*;
import com.mry.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmpSalaryService {
    @Resource
    private StoreReportService storeReportService;
    @Resource
    private UserServiceListRepository userServiceListRepository;
    @Resource
    private SalaryManageRepository salaryManageRepository;
    @Resource
    private SpecificRulesRepository specificRulesRepository;
    @Resource
    private PracticalCommissionRepository practicalCommissionRepository;
    @Resource
    private PerformanceCommissionRepository performanceCommissionRepository;
    //@Resource
    //private ItemManageRepository itemManageRepository;
    @Resource
    private PassgerRewardRepository passgerRewardRepository;
    @Resource
    private PerformanceRepository performanceRepository;
    @Resource
    private ConForfeitRepository conForfeitRepository;
    @Resource
    private SpecialForfeitRepository specialForfeitRepository;
    @Resource
    private ItemTypeManageRepository itemTypeManageRepository;
    @Resource
    private ComplaintManageRepository complaintManageRepository;
    @Resource
    private EmployeeRepository employeeRepository;

    // 根据门店 id + 指定月份生成该门店下所有员工工资详情
    public List<Map<String, Object>> getEmpSalaryByStoreId(Integer storeId, String month) {
        List<Map<String, Object>> salaryInfo = new ArrayList<>();
        // 1.获取已分组的员工服务单信息
        Map<String, List<UserServiceList>> group = getEmpUserServiceList(storeId, month);
        if(group.isEmpty()) {
            throw new CommonException(504, "没找到 storeId = " + storeId + " 的门店下 month = " + month + " 的服务单信息");
        }
        // 2. 获取该门店下的薪资规则
        Map<String, Object> rule = getStoreSalaryRuleByStoreId(storeId);
        if(rule.isEmpty()) {
            throw new CommonException(504, "没找到 storeId = " + storeId + " 的门店下薪资规则");
        }

        // 3. 遍历员工的服务单
        for(Map.Entry<String, List<UserServiceList>> entry : group.entrySet()) {
            Double empSalary = 0.0;
            Map<String, Object> empSalaryInfo = new HashMap<>();
            String empId = entry.getKey();
            List<UserServiceList> userServiceLists = entry.getValue();
            // 获取每个员工的业绩等信息
            Map<String, Object> empPerformance = calculateEmpPerformance(storeId, userServiceLists);
            // 总业绩
            Double perMoney = (Double)empPerformance.get("perMoney");
            // 获取基本薪资规则
            List<SpecificRules> specificRules = (List<SpecificRules>)rule.get("bsRule");
            for(SpecificRules specificRule : specificRules) {
                // 看看在哪个区间
                if(isBetween(perMoney, CommonUtils.parseDouble(specificRule.getLowLimit()), CommonUtils.parseDouble(specificRule.getHighLimit()))) {
                    // 基本薪资
                    Double baseSalary = CommonUtils.parseDouble(specificRule.getMoney());
                    empSalaryInfo.put("baseSalary", baseSalary);
                    empSalary = CommonUtils.doubleCalculation(empSalary, baseSalary, "+");
                    break;
                }
            }

            // 实操提成
            List<PracticalCommission> practicalCommissions = (List<PracticalCommission>)rule.get("poRule");
            for(PracticalCommission pc : practicalCommissions) {
                if(isBetween(perMoney, CommonUtils.parseDouble(pc.getLowLimit()), CommonUtils.parseDouble(pc.getHighLimit()))) {
                    // 面部指定
                    Double faceAppointMoney = (Double)empPerformance.get("faceAppointMoney");
                    // 获取面部指定提成百分比
                    Double faceAppointPercent = CommonUtils.doubleCalculation(CommonUtils.parseDouble(pc.getFaceDesignated()), 100.0, "/");
                    // 面部指定最终提成
                    Double faceAppointCommission = CommonUtils.doubleCalculation(faceAppointMoney, faceAppointPercent, "*");
                    empSalaryInfo.put("faceAppointCommission", faceAppointCommission);
                    empSalary = CommonUtils.doubleCalculation(empSalary, faceAppointCommission, "+");

                    // 面部非指定
                    Double faceNotAppointMoney = (Double)empPerformance.get("faceNotAppointMoney");
                    // 获取面部非指定提成百分比
                    Double faceNotAppointPercent = CommonUtils.doubleCalculation(CommonUtils.parseDouble(pc.getNFaceDesignated()), 100.0, "/");
                    // 面部非指定最终提成
                    Double faceNotAppointCommission = CommonUtils.doubleCalculation(faceNotAppointMoney, faceNotAppointPercent, "*");
                    empSalaryInfo.put("faceNotAppointCommission", faceNotAppointCommission);
                    empSalary = CommonUtils.doubleCalculation(empSalary, faceNotAppointCommission, "+");

                    // 身体指定
                    Double bodyAppointMoney = (Double)empPerformance.get("bodyAppointMoney");
                    // 身体指定提成百分比
                    Double bodyAppointPercent = CommonUtils.doubleCalculation(CommonUtils.parseDouble(pc.getBodyDesignated()), 100.0, "/");
                    // 身体指定最终提成
                    Double bodyAppointCommission = CommonUtils.doubleCalculation(bodyAppointMoney, bodyAppointPercent, "*");
                    empSalaryInfo.put("bodyAppointCommission", bodyAppointCommission);
                    empSalary = CommonUtils.doubleCalculation(empSalary, bodyAppointCommission, "+");

                    // 身体非指定
                    Double bodyNotAppointMoney = (Double)empPerformance.get("bodyNotAppointMoney");
                    // 身体非指定提成百分比
                    Double bodyNotAppointPercent = CommonUtils.doubleCalculation(CommonUtils.parseDouble(pc.getNBodyDesignated()), 100.0, "/");
                    // 身体非指定最终提成
                    Double bodyNotAppointCommission = CommonUtils.doubleCalculation(bodyNotAppointMoney, bodyNotAppointPercent, "*");
                    empSalaryInfo.put("bodyNotAppointCommission", bodyNotAppointCommission);
                    empSalary = CommonUtils.doubleCalculation(empSalary, bodyNotAppointCommission, "+");
                    break;
                }
            }

            // 奖金
            // 奖金规则
            List<PassgerReward> passgerRewards = (List<PassgerReward>)rule.get("reRule");
            // 单日达标
            PassgerReward datePassgerReward = getPassgerRewardByType(passgerRewards, "1");
            // 累计达标
            PassgerReward countPassgerReward = getPassgerRewardByType(passgerRewards, "2");
            // 计算单日达标奖金
            // 先按照日期分组
            Map<String, Integer> groupPassager = groupPassager(userServiceLists);
            // 获取最终的奖金
            Double dateRewardMoney = dateRewardMoney(groupPassager, datePassgerReward);
            empSalaryInfo.put("dateRewardMoney", dateRewardMoney);
            empSalary = CommonUtils.doubleCalculation(empSalary, dateRewardMoney, "+");
            // 获取累计客流
            Integer passFlow = (Integer)empPerformance.get("passFlow");
            if(passFlow >= Integer.parseInt(countPassgerReward.getPassgerFlow())) {
                Double countRewardMoney = CommonUtils.parseDouble(countPassgerReward.getRewardMoney());
                empSalaryInfo.put("countRewardMoney", countRewardMoney);
                empSalary = CommonUtils.doubleCalculation(empSalary, countRewardMoney, "+");
            }

            // 绩效
            Performance peRule = (Performance)rule.get("peRule");
            // 获取员工缴纳的绩效金(计算工资的时候要减去)
            Double empMoney = CommonUtils.parseDouble(peRule.getEmpPay());
            empSalaryInfo.put("epMoney", empMoney);
            empSalary = CommonUtils.doubleCalculation(empSalary, empMoney, "-");
            // 获取绩效规定的金额
            Double achMoney = CommonUtils.parseDouble(peRule.getAchMoney());
            // 达标
            if(perMoney >= achMoney) {
                // 最终应得的钱 = 达标后奖励的钱
                Double peMoney = CommonUtils.parseDouble(peRule.getRetMoney());
                empSalaryInfo.put("peMoney", peMoney);
                empSalary = CommonUtils.doubleCalculation(empSalary, peMoney, "+");
            // 不达标
            } else if(perMoney < CommonUtils.parseDouble(peRule.getNAchMoney())) {
                // 最终要扣除的钱(计算工资的时候要减去)
                Double deMoney = CommonUtils.parseDouble(peRule.getDedMoney());
                empSalaryInfo.put("deMoney", CommonUtils.parseDouble(peRule.getDedMoney()));
                empSalary = CommonUtils.doubleCalculation(empSalary, deMoney, "-");
            }

            // 罚金
            // 获取该门店所有投诉记录
            List<ComplaintManage> complaintManages = complaintManageRepository.getComplaintManageByStoreId(storeId);
            // 计算该员工投诉记录总数
            Integer complaintCount = 0;
            if(!complaintManages.isEmpty()) {
                for(ComplaintManage complaintManage : complaintManages) {
                    for(UserServiceList userServiceList : userServiceLists) {
                        if(complaintManage.getServiceId().equals(userServiceList.getId())) {
                            complaintCount ++;
                        }
                    }
                }
            }
            // 被投诉了才计算处罚
            if(complaintCount > 0) {
                // 获取投诉处罚规则
                ConForfeit conForfeit = (ConForfeit)rule.get("fcRule");
                Double conForfeitMoney = CommonUtils.parseDouble(conForfeit.getForfeitMoney());
                // 最终要扣除的钱(计算工资的时候要减去)
                conForfeitMoney = CommonUtils.doubleCalculation(conForfeitMoney, complaintCount, "*");
                empSalaryInfo.put("cfMoney", conForfeitMoney);
                empSalary = CommonUtils.doubleCalculation(empSalary, conForfeitMoney, "-");
            }

            // 现金业绩不达标处罚
            List<SpecialForfeit> forfeitArchives = (List<SpecialForfeit>)rule.get("faRule");
            for(SpecialForfeit forfeitArchive : forfeitArchives) {
                if(isBetween(perMoney, CommonUtils.parseDouble(forfeitArchive.getLowLimit()), CommonUtils.parseDouble(forfeitArchive.getHighLimit()))) {
                    // 获取罚金金额(计算工资的时候要减去)
                    Double sfMoney = CommonUtils.parseDouble(forfeitArchive.getForfeitMoney());
                    empSalaryInfo.put("sfMoney", sfMoney);
                    empSalary = CommonUtils.doubleCalculation(empSalary, sfMoney, "-");
                    break;
                }
            }

            // 客流罚金
            List<SpecialForfeit> forfeitPassageFlows = (List<SpecialForfeit>)rule.get("fpRule");
            for(SpecialForfeit forfeitPassageFlow : forfeitPassageFlows) {
                if(isBetween(perMoney, CommonUtils.parseDouble(forfeitPassageFlow.getLowLimit()), CommonUtils.parseDouble(forfeitPassageFlow.getHighLimit()))) {
                    // 获取罚金金额(计算工资的时候要减去)
                    Double fpMoney = CommonUtils.parseDouble(forfeitPassageFlow.getForfeitMoney());
                    empSalaryInfo.put("fpMoney", fpMoney);
                    empSalary = CommonUtils.doubleCalculation(empSalary, fpMoney, "-");
                    break;
                }
            }

            // 获取员工信息
            Employee employee = employeeRepository.getEmployeeById(storeId, Integer.parseInt(empId));

            // 门店id
            empSalaryInfo.put("storeId", storeId);
            // 员工信息
            empSalaryInfo.put("empInfo", employee);
            // 最终员工的工资
            empSalaryInfo.put("empSalary", empSalary);
            // 计算工资的时间
            empSalaryInfo.put("month", month);
            // 该员工的总业绩
            empSalaryInfo.put("perMoney", perMoney);

            salaryInfo.add(empSalaryInfo);
        }

        return salaryInfo;
    }

    // 工资字段说明
    public List<String> salaryDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("storeId : 门店 id");
        desc.add("month: 计算工资指定的月份");
        desc.add("perMoney : 员工当月总业绩");
        desc.add("baseSalary : 基本薪资");
        desc.add("empSalary : 员工最终的工资");
        desc.add("faceAppointCommission : 面部指定最终提成");
        desc.add("faceNotAppointCommission : 面部非指定最终提成");
        desc.add("bodyAppointCommission : 身体指定最终提成");
        desc.add("bodyNotAppointCommission : 身体非指定最终提成");
        desc.add("dateRewardMoney : 单日客流达标最终奖金");
        desc.add("countRewardMoney : 累计客流达标最终奖金");
        desc.add("epMoney : 员工缴纳的绩效金");
        desc.add("peMoney : 达到绩效后的奖金");
        desc.add("deMoney : 达不到绩效后的罚金");
        desc.add("cfMoney : 被投诉后的总罚金");
        desc.add("sfMoney : 现金业绩不达标的罚金");
        desc.add("fpMoney : 客流业绩不达标的罚金");
        return desc;
    }

    // 获取奖金规则中，不同类别的规则(当日和累计)
    public PassgerReward getPassgerRewardByType(List<PassgerReward> passgerRewards, String type) {
        for(PassgerReward passgerReward : passgerRewards) {
            if(passgerReward.getRewardType().equals(type)) {
                return passgerReward;
            }
        }
        return null;
    }

    // 将用户服务单按照日期分组
    public Map<String, Integer> groupPassager(List<UserServiceList> userServiceLists) {
        Map<String, Integer> groupPassager = new HashMap<>();
        for(UserServiceList userServiceList : userServiceLists) {
            String updateDate = userServiceList.getUpdateDate();
            // 只取出年月日部分
            updateDate = updateDate.split(" ")[0];
            Integer count = groupPassager.get(updateDate);
            if(null != groupPassager.get(updateDate)) {
                count ++;
            } else {
                groupPassager.put(updateDate, 1);
            }
        }
        return groupPassager;
    }

    // 计算单日达标的奖金
    public Double dateRewardMoney(Map<String, Integer> groupPassager, PassgerReward datePassgerReward) {
        // 获取单日达标设定人数
        Integer passagers = Integer.parseInt(datePassgerReward.getRewardMoney());
        // 获取单日达标奖金金额
        Double rewardMoney = CommonUtils.parseDouble(datePassgerReward.getRewardMoney());
        // 最终的奖金金额
        Double reward = 0.0;

        for(Map.Entry<String, Integer> entry : groupPassager.entrySet()) {
            if(entry.getValue() >= passagers) {
                reward += rewardMoney;
            }
        }
        return reward;
    }

    // 计算一个员工的总业绩等信息
    public Map<String, Object> calculateEmpPerformance(Integer storeId, List<UserServiceList> userServiceLists) {
        Map<String, Object> empPerformance = new HashMap<>();
        // 总业绩
        Double perMoney = 0.0;
        for(UserServiceList userServiceList : userServiceLists) {
            perMoney = CommonUtils.doubleCalculation(perMoney, CommonUtils.parseDouble(userServiceList.getPayMoney()), "+");
        }
        // 取出当前门店所有美容服务项目
        List<ItemTypeManage> itemTypeManages = itemTypeManageRepository.getItemTypeManageByStoreId(storeId);
        if(itemTypeManages.isEmpty()) {
            throw new CommonException(504, "找不到门店 " + storeId + " 下的服务项目信息");
        }
        // 计算出面部，身体指定、非指定信息
        Map<String, Object> appoints = storeReportService.calculationOperationCapability(userServiceLists, itemTypeManages);
        empPerformance.putAll(appoints);
        empPerformance.put("perMoney", perMoney);
        // 总客流
        empPerformance.put("passFlow", userServiceLists.size());

        return empPerformance;
    }

    // 获取门店指定月份所有已经完成的服务单
    // 并按照员工 id 分组
    public Map<String, List<UserServiceList>> getEmpUserServiceList(Integer storeId, String month) {
        List<UserServiceList> userServiceLists = userServiceListRepository.getCompletedUserServiceListByStoreIdAndUpdateDate(storeId, month + "%");
        if(userServiceLists.isEmpty()) {
            return null;
        }
        // 按员工 id 分组
        Map<String, List<UserServiceList>> group = new HashMap<>();
        for(UserServiceList userServiceList : userServiceLists) {
            String empId = userServiceList.getTechnician();
            if(!StringUtils.isEmpty(empId) && null != group.get(empId)) {
                List<UserServiceList> gUserServiceLists = group.get(empId);
                gUserServiceLists.add(userServiceList);
            } else {
                List<UserServiceList> gUserServiceLists = new ArrayList<>();
                gUserServiceLists.add(userServiceList);
                group.put(empId, gUserServiceLists);
            }
        }
        return group;
    }

    // 根据门店 id 查询出该门店的薪资规则
    public Map<String, Object> getStoreSalaryRuleByStoreId(Integer soreId) {
        Map<String, Object> salaryRule = new HashMap<>();
        // 获取该门店基本薪资规则
        SalaryManage salaryManage = salaryManageRepository.getSalaryManageByStoreId(soreId);
        if(null == salaryManage) {
            return null;
        }
        String baseSalary = salaryManage.getBaseSalary();
        // 启用底薪规则
        if(!StringUtils.isEmpty(baseSalary) && baseSalary.equals("1")) {
            // 找出底薪类别
            String salaryOption = salaryManage.getBaseSalaryOption();
            if(!StringUtils.isEmpty(salaryOption)) {
                // 底薪规则
                List<SpecificRules> specificRules = specificRulesRepository.getSpecificRulesByBaseSalaryOption(soreId, getSalaryOption(salaryOption));
                salaryRule.put("bsRule", specificRules);
            }
        }

        String poCommission = salaryManage.getPoCommission();
        // 启用实操提成
        if(!StringUtils.isEmpty(poCommission) && poCommission.equals("1")) {
            // 找出底薪类别
            String salaryOption = salaryManage.getBaseSalaryOption();
            if(!StringUtils.isEmpty(salaryOption)) {
                // 实操提成规则
                List<PracticalCommission> practicalCommissions = practicalCommissionRepository.getPracticalCommissionByStoreIdAndPracticalRange(soreId, getSalaryOption(salaryOption));
                salaryRule.put("poRule", practicalCommissions);
            }
        }

        String acCommission = salaryManage.getAcCommission();
        // 启用业绩提成
        if(!StringUtils.isEmpty(acCommission) && acCommission.equals("1")) {
            // 业绩规则
            List<PerformanceCommission> performanceCommissions = performanceCommissionRepository.getPerformanceCommissionByStoreId(soreId);
            salaryRule.put("pcRule", performanceCommissions);
        }

       /**
        * String handWorkPay = salaryManage.getHandworkPay();
        * // 启用手工费; 此处有问题按照项目手工费计算；
        * if(!StringUtils.isEmpty(handWorkPay) && handWorkPay.equals("1")) {
        *   // 取出项目以及对应的手工费信息
        *    List<ItemManage> itemManages = itemManageRepository.getItemManagesByStoreId(soreId);
        *    salaryRule.put("hwRule", itemManages);
        * }
        */

        // 奖金
        String rewardRule = salaryManage.getRewardRule();
        if(!StringUtils.isEmpty(rewardRule)) {
            // 按客流奖励
            if(rewardRule.equals("1")) {
                List<PassgerReward> passgerRewards = passgerRewardRepository.getPassgerRewardByStoreId(soreId);
                // 客流规则，1 单日达标奖励，2 累计达标奖励
                salaryRule.put("reRule", passgerRewards);
                // 累计客流又分区间达标奖励；设计感觉有问题，暂时跳过

                // 团队奖金，太复杂了，还要分组，暂时跳过

                // 活动奖金，也是要分组， 暂时跳过
            }
        }

        // 绩效
        String achAble = salaryManage.getAchEnable();
        if(!StringUtils.isEmpty(achAble) && achAble.equals("1")) {
            // 获取绩效设置
            Performance performance = performanceRepository.getPerformanceByStoreId(soreId);
            salaryRule.put("peRule", performance);
        }

        /** 罚金，按照当前项目设定，暂时无法计算迟到早退，事假和旷工罚金，消耗罚金
         *  只能处理投诉罚金，现金罚金和客流罚金
         */
        // 投诉罚金
        String forfeitComplaint = salaryManage.getForfeitComplaint();
        if(!StringUtils.isEmpty(forfeitComplaint) && forfeitComplaint.equals("1")) {
            ConForfeit conForfeit = conForfeitRepository.getConForfeitByForfeitType(soreId, "1");
            salaryRule.put("fcRule", conForfeit);
        }

        // 现金业绩罚金
        String forfeitArchive = salaryManage.getForfeitAchievement();
        if(!StringUtils.isEmpty(forfeitArchive) && forfeitArchive.equals("1")) {
            List<SpecialForfeit> forfeitArchives = specialForfeitRepository.getSpecialForfeitByForfeitType(soreId, "1");
            salaryRule.put("faRule", forfeitArchives);
        }

        // 客流业绩罚金
        String forfeitPassageFlow = salaryManage.getForfeitPagerFlow();
        if(!StringUtils.isEmpty(forfeitPassageFlow) && forfeitPassageFlow.equals("1")) {
            List<SpecialForfeit> forfeitPassageFlows = specialForfeitRepository.getSpecialForfeitByForfeitType(soreId, "2");
            salaryRule.put("fpRule", forfeitPassageFlows);
        }

        return salaryRule;
    }

    // 转换基本工资类别
    public String getSalaryOption(String salaryOption) {
        switch(salaryOption) {
            case "个人": salaryOption = "1"; break;
            case "小组": salaryOption = "2"; break;
            case "全店": salaryOption = "3"; break;
            default: salaryOption = "0"; break;
        }
        return salaryOption;
    }

    // 判断数字 num 是否在 numA 和 numB 之间
    public static boolean isBetween(Double num, Double numA, Double numB) {
        return numA <= num && num < numB;
    }

}
