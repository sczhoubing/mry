package com.mry.service;

import com.mry.enums.DateFormat;
import com.mry.model.*;
import com.mry.repository.EmployeeRepository;
import com.mry.repository.ItemTypeManageRepository;
import com.mry.repository.UserManageRepository;
import com.mry.repository.UserServiceListRepository;
import com.mry.utils.CommonUtils;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class StoreReportService {
    @Resource
    private UserServiceListRepository userServiceListRepository;
    @Resource
    private EmployeeRepository employeeRepository;
    @Resource
    private ItemTypeManageRepository itemTypeManageRepository;
    @Resource
    private UserManageRepository userManageRepository;

    /**
     *  美容院月报
     */
    public Map<String, Object> storeMonthReport(Integer storeId, String moth) {
        Map<String, Object> mothReport = new HashMap<>();
        // 1. 确认要查询的月份, 如果传递的月份参数为空，则默认为当前月份
        if(StringUtils.isEmpty(moth)) {
            moth = CommonUtils.currentDate(DateFormat.FORMAT7.getFormat());
        }
        // 2. 确认是否有门店信息指定月份的信息, 此处默认取已经完成订单的记录(status = 1)
        List<UserServiceList> userServiceLists = userServiceListRepository.
                getUserServiceListByStoreIdAndUpdateDate(storeId,moth + "%");
        // 如果没有对应记录，返回一条信息给前端
        if(userServiceLists.isEmpty()) {
            mothReport.put("storeId", storeId);
            mothReport.put("msg", "can't find records");
            return mothReport;
        }
        // 3. 门店有对应记录, 按照每一天分析并生成各个字段
        Map<String, List<UserServiceList>> dateRecords = splitRecordsByUpdateDate(userServiceLists);
        List<StoreMothReport> dailyReports =  generateDateReport(dateRecords);
        mothReport.put("storeId", storeId);
        mothReport.put("moth", moth);
        // 将记录排序，因为前面集合使用了 Map，导致顺序混乱，所以需要按照日期重新排序
        Collections.sort(dailyReports);
        mothReport.put("dailyReport", dailyReports);
        return mothReport;
    }

    // 按照日期将记录分组
    public Map<String, List<UserServiceList>> splitRecordsByUpdateDate(List<UserServiceList> userServiceLists) {
        Map<String, List<UserServiceList>> dateRecords = new HashMap<>();
        for(UserServiceList userServiceList : userServiceLists) {
            // 将日期处理为 yyyy-MM-dd 的格式
            Date date = CommonUtils.parseDate(userServiceList.getUpdateDate());
            String updateDate = CommonUtils.formatDate(date, DateFormat.FORMAT4.getFormat());

            if(dateRecords.containsKey(updateDate)) {
                List<UserServiceList> records = dateRecords.get(updateDate);
                records.add(userServiceList);
            } else {
                List<UserServiceList> records = new ArrayList<>();
                records.add(userServiceList);
                dateRecords.put(updateDate, records);
            }
        }
        return dateRecords;
    }

    // 按照日期遍历 dateRecords 这个 Map，生成对应每天的记录信息
    public List<StoreMothReport> generateDateReport(Map<String, List<UserServiceList>> dateRecords) {
        List<StoreMothReport> dailyReports = new ArrayList<>();
        for(Map.Entry<String, List<UserServiceList>> entry : dateRecords.entrySet()) {
            List<UserServiceList> userServiceLists = entry.getValue();

            StoreMothReport storeMothReport = new StoreMothReport();
            // 门店月报每天的日期
            String date = entry.getKey();
            // 总业绩
            Double achievement = 0.0;
            // 总客流
            Integer passengerFlow = userServiceLists.size();
            // 总实操
            Integer operationCapability = 0;
            // 产品现金
            Double productMoney = 0.0;
            // 产品卡扣
            Double productCardDeduction = 0.0;
            // 卡扣项目
            Integer cardProjects = 0;
            // 售前新客人数
            Integer preSaleNewCustomer = 0;
            // 售前交易人数
            Integer preSaleDealCustomer = 0;
            // 售前业绩
            Double preSaleAchievement = 0.0;
            // 备注信息，记录某些不正确的数据，用于错误排查
            StringBuilder remarks = new StringBuilder();

            for(UserServiceList userServiceList : userServiceLists) {
                // 当前服务状态
                String status = userServiceList.getStatus();
                // 计算总业绩
                String payMoney = userServiceList.getPayMoney();
                if(CommonUtils.validStr(payMoney) && status.equals("1")) {
                    Double money = Double.parseDouble(payMoney);
                    achievement = CommonUtils.doubleCalculation(achievement, money, "+");
                }
                // 计算总实操
                String project = userServiceList.getProject();
                if(!StringUtils.isEmpty(project) && status.equals("1")) {
                    operationCapability += project.split(",").length;
                }
                // 计算产品现金
                String payType = userServiceList.getPayType();
                String payMent = userServiceList.getPayMoney();
                if(CommonUtils.validStr(payType) && status.equals("1")) {
                    // payType = 1 表示现金
                    if(payType.equals("1")) {
                        productMoney = CommonUtils.doubleCalculation(productMoney, Double.parseDouble(payMent), "+");
                    }
                    // payType = 2 表示卡扣
                    if(payType.equals("2")) {
                        productCardDeduction = CommonUtils.doubleCalculation(productCardDeduction, Double.parseDouble(payMent), "+");
                    }
                }
                // 统计卡扣项目，可忽略服务状态
                String productProject = userServiceList.getProject();
                if(CommonUtils.validStr(productProject) && payType.equals("2")) {
                    cardProjects += productProject.split(",").length;
                }
                String isPreSale = userServiceList.getIsPreSale();
                if(CommonUtils.validStr(isPreSale) && isPreSale.equals("1")) {
                    // 售前新客人数包含服务状态为 1 和 -1 的
                    preSaleNewCustomer ++;
                    // 售前交易人数则应包含服务状态为 1 的
                    if(status.equals("1")) {
                        preSaleDealCustomer ++;
                        // 计算售前业绩
                        preSaleAchievement = CommonUtils.doubleCalculation(preSaleAchievement, Double.parseDouble(payMent), "+");
                    }
                }
            }
            storeMothReport.setDate(date);
            storeMothReport.setAchievement(achievement);
            storeMothReport.setPassengerFlow(passengerFlow);
            storeMothReport.setOperationCapability(operationCapability);
            storeMothReport.setProductMoney(productMoney);
            storeMothReport.setProductCardDeduction(productCardDeduction);
            storeMothReport.setCardProjects(cardProjects);
            storeMothReport.setPreSaleNewCustomer(preSaleNewCustomer);
            storeMothReport.setPreSaleDealCustomer(preSaleDealCustomer);
            storeMothReport.setPreSaleAchievement(preSaleAchievement);
            storeMothReport.setRemarks(remarks.toString());
            dailyReports.add(storeMothReport);
        }
        return dailyReports;
    }

    /**
     * 按照技师 + 月份生成报表
     */
    public Map<String, Object>technicianMothReport(Integer storeId, String moth, String name) {
        Map<String, Object> technicianMothReport = new HashMap<>();
        // 1. 确认要查询的月份, 如果传递的月份参数为空，则默认为当前月份
        if(StringUtils.isEmpty(moth)) {
            moth = CommonUtils.currentDate(DateFormat.FORMAT7.getFormat());
        }
        // 2. 根据技师姓名 + 月份查询
        /**
         *  如果是根据员工姓名 + “-” + 手机号格式则只返回一个员工信息
         */
        if(name.contains("-")) {
            String[] empInfo = name.split("-");
            // 获取员工的手机号
            String empPhone = empInfo[1];
            Employee technician = employeeRepository.getEmployeeByPhoneNum(storeId, empPhone);
            if(null == technician) {
                technicianMothReport.put("msg", "can't find employee info - " + name);
            } else {
                Integer technicianId = technician.getId();
                List<UserServiceList> userServiceLists = userServiceListRepository.getUserServiceListByStoreIdAndUpdateDateAndTechnicianId(
                        storeId, moth + "%", technicianId + ""
                );
                if(userServiceLists.isEmpty()) {
                    technicianMothReport.put("msg", "can't find records for emp - " + name);
                } else {
                    // 取出当前门店所有美容服务项目
                    List<ItemTypeManage> itemTypeManages = itemTypeManageRepository.getItemTypeManageByStoreId(storeId);
                    if(itemTypeManages.isEmpty()) {
                        technicianMothReport.put("msg", "can't find store item_type_manage info - " + storeId);
                    } else {
                        // 按照每天的日期将记录分组
                        Map<String, List<UserServiceList>> dateRecords = splitRecordsByUpdateDate(userServiceLists);
                        // 生成每天的记录
                        List<TechnicianMothReport> dailyReports = generateTechnicianReport(dateRecords, itemTypeManages);
                        technicianMothReport.put("storeId", storeId);
                        technicianMothReport.put("technician", technician);
                        // 将记录排序，因为前面集合使用了 Map，导致顺序混乱，所以需要按照日期重新排序
                        Collections.sort(dailyReports);
                        technicianMothReport.put("dailyReport", dailyReports);
                        technicianMothReport.put("moth", moth);
                    }
                }
            }
        } else {
            technicianMothReport.put("msg", "required params should include emp's name and phone number");
        }
        return technicianMothReport;
    }

    // 按照日期生成每天的报表
    public List<TechnicianMothReport> generateTechnicianReport(Map<String, List<UserServiceList>> dateRecords, List<ItemTypeManage> itemTypeManages) {
        List<TechnicianMothReport> dailyReport = new ArrayList<>();

        for(Map.Entry<String, List<UserServiceList>> entry : dateRecords.entrySet()) {
            List<UserServiceList> userServiceLists = entry.getValue();

            TechnicianMothReport technicianMothReport = new TechnicianMothReport();

            // 技师月报每天的日期
            String date = entry.getKey();
            // 技师每日客流
            Integer passengerFlow = userServiceLists.size();
            // 技师每天项目数
            Integer projectNumber = 0;

            // 计算实操(面部，身体，指定和非指定)
            Map<String, Integer> operationCapabilities = calculationOperationCapability(userServiceLists, itemTypeManages);
            // 技师每天面部指定实操
            Integer faceAppoint = operationCapabilities.get("faceAppoint");
            // 技师每天面部非指定实操
            Integer faceNotAppoint = operationCapabilities.get("faceNotAppoint");
            // 技师每天身体实操指定
            Integer bodyAppoint = operationCapabilities.get("bodyAppoint");
            // 技师每天身体实操非指定
            Integer bodyNotAppoint = operationCapabilities.get("bodyNotAppoint");
            // 实操总计
            Integer operationCapability = faceAppoint + faceNotAppoint + bodyAppoint + bodyNotAppoint;
            // 现金业绩
            Double cashAchievement = 0.0;
            // 卡扣业绩
            Double cardDeduction = 0.0;
            // 售前新客人数
            Integer newCustomer = 0;
            // 售前成交人数
            Integer dealCustomer = 0;
            // 售前业绩
            Double preSaleAchievement = 0.0;
            // 卡扣项目
            Integer cardDeductionProjects = 0;
            // 备注信息
            StringBuilder remarks = new StringBuilder();

            for(UserServiceList userServiceList : userServiceLists) {
                // 计算技师每天项目数
                String project = userServiceList.getProject();
                if(!StringUtils.isEmpty(project)) {
                    projectNumber += project.split(",").length;
                }
                // 计算业绩
                String payMoney = userServiceList.getPayMoney();
                if(CommonUtils.validStr(payMoney) && userServiceList.getStatus().equals("1")) {
                    // 现金业绩
                    if(userServiceList.getPayType().equals("1")) {
                        cashAchievement = CommonUtils.doubleCalculation(cashAchievement, Double.parseDouble(payMoney), "+");
                    // 卡扣业绩
                    } else if(userServiceList.getPayType().equals("2")) {
                        cardDeduction = CommonUtils.doubleCalculation(cardDeduction, Double.parseDouble(payMoney), "+");
                    }
                }
                // 计算售前
                if(userServiceList.getIsPreSale().equals("1")) {
                    // 售前新客人数包含 status = 1 和 -1 的
                    newCustomer ++;
                    if(userServiceList.getStatus().equals("1")) {
                        // 售前成交人数，即 status = 1 的
                        dealCustomer ++;
                        // 售前业绩，status = 1 的才是已经做完了的项目
                        preSaleAchievement = CommonUtils.doubleCalculation(preSaleAchievement, Double.parseDouble(payMoney), "+");
                    }
                }
                // 计算卡扣项目, payType = 2 的表示卡扣项目
                String payType = userServiceList.getPayType();
                if(!StringUtils.isEmpty(payType) && payType.equals("2")) {
                    cardDeductionProjects += project.split(",").length;
                }
            }

            technicianMothReport.setDate(date);
            technicianMothReport.setPassengerFlow(passengerFlow);
            technicianMothReport.setProjectNumber(projectNumber);
            technicianMothReport.setFaceAppoint(faceAppoint);
            technicianMothReport.setFaceNotAppoint(faceNotAppoint);
            technicianMothReport.setBodyAppoint(bodyAppoint);
            technicianMothReport.setBodyNotAppoint(bodyNotAppoint);
            technicianMothReport.setOperationCapability(operationCapability);
            technicianMothReport.setCashAchievement(cashAchievement);
            technicianMothReport.setCardDeduction(cardDeduction);
            technicianMothReport.setNewCustomer(newCustomer);
            technicianMothReport.setDealCustomer(dealCustomer);
            technicianMothReport.setPreSaleAchievement(preSaleAchievement);
            technicianMothReport.setCardDeductionProjects(cardDeductionProjects);
            technicianMothReport.setRemarks(remarks.toString());

            dailyReport.add(technicianMothReport);
        }
        return dailyReport;
    }

    // 实操计算
    public Map<String, Integer> calculationOperationCapability(List<UserServiceList> userServiceLists, List<ItemTypeManage> itemTypeManages) {
        Map<String, Integer> operationCapabilities = new HashMap<>();
        // 技师每天面部指定实操
        Integer faceAppoint = 0;
        // 技师每天面部非指定实操
        Integer faceNotAppoint = 0;
        // 技师每天身体实操指定
        Integer bodyAppoint = 0;
        // 技师每天身体实操非指定
        Integer bodyNotAppoint = 0;
        for(UserServiceList userServiceList : userServiceLists) {
            for(ItemTypeManage itemTypeManage : itemTypeManages) {
                String project = userServiceList.getProject();
                String[] projects = project.split(",");
                for(String ps : projects) {
                    // 能从 item_type_manage 匹配到项目
                    if (ps.equals(itemTypeManage.getId() + "")) {
                        // 非指定项目
                        if (userServiceList.getIsAppoint().equals("0")) {
                            // 项目的类别为面部
                            if (itemTypeManage.getItemType().equals("1")) {
                                faceNotAppoint ++;
                                // 项目的类别为身体
                            } else if (itemTypeManage.getItemType().equals("2")) {
                                bodyNotAppoint ++;
                            }
                        // 指定项目
                        } else if (userServiceList.getIsAppoint().equals("1")) {
                            // 项目的类别为面部
                            if (itemTypeManage.getItemType().equals("1")) {
                                faceNotAppoint ++;
                                // 项目的类别为身体
                            } else if (itemTypeManage.getItemType().equals("2")) {
                                bodyNotAppoint ++;
                            }
                        }

                    }
                }
            }
        }
        operationCapabilities.put("faceAppoint", faceAppoint);
        operationCapabilities.put("faceNotAppoint", faceNotAppoint);
        operationCapabilities.put("bodyAppoint", bodyAppoint);
        operationCapabilities.put("bodyNotAppoint", bodyNotAppoint);
        return operationCapabilities;
    }

    /**
     *  门店日报表
     */
    public Map<String, Object> storeDailyReport(Integer storeId, String date) {
        Map<String, Object> dailyReport = new HashMap<>();
        // 1.确定要查询的日期，如果没有传递日期参数，则默认日期为当天
        if(StringUtils.isEmpty(date)) {
            date = CommonUtils.currentDate(DateFormat.FORMAT4.getFormat());
        }
        // 2. 根据门店 Id + 日期查询出该门店下所有的记录
        List<UserServiceList> userServiceLists = userServiceListRepository.getUserServiceListByStoreIdAndUpdateDate(storeId, date + "%");
        if(userServiceLists.isEmpty()) {
            dailyReport.put("msg",  "can't find records from store: " + storeId);
        } else {
            // 3. 查询出该门店下所有服务项目记录，后面会用到
            List<ItemTypeManage> itemTypeManages = itemTypeManageRepository.getItemTypeManageByStoreId(storeId);
            // 4. 查询出该门店下服务过的所有顾客信息，后面会用到
            List<UserManage> userManages = userManageRepository.getUserManageByStoreId(storeId);
            // 5. 查询出该门店下所有员工姓名, 后面会用到
            List<Employee> employees = employeeRepository.getEmployeeByStoreId(storeId);
            // 6. 将记录生成门店当天报表信息
            List<StoreDailyReport> dailyReports = generateStoreDailyReport(userServiceLists, itemTypeManages, userManages, employees);
            dailyReport.put("date", date);
            dailyReport.put("storeId", storeId);
            // 将记录排序，因为前面集合使用了 Map，导致顺序混乱，所以需要按照日期重新排序
            Collections.sort(dailyReports);
            dailyReport.put("dailyReport", dailyReports);
        }
        return dailyReport;
    }

    // 将门店服务信息生成记录
    public List<StoreDailyReport> generateStoreDailyReport(List<UserServiceList> userServiceLists, List<ItemTypeManage> itemTypeManages, List<UserManage> userManages, List<Employee> employees) {
        List<StoreDailyReport> storeDailyReports = new ArrayList<>();
        for(UserServiceList userServiceList : userServiceLists) {
            StoreDailyReport storeDailyReport = new StoreDailyReport();
            // 报表日期
            String time = userServiceList.getUpdateDate();
            // 员工姓名
            String empName = null;
            // 员工电话号码
            String phoneNum = null;
            // 顾客姓名
            String clientName = getClientNameById(userServiceList.getUserId(), userManages);
            // 面部，身体，指定和非指定
            Map<String, Integer> operationCapabilities = calculationOperationCapability(Arrays.asList(userServiceList), itemTypeManages);
            // 产品现金
            Double productCash = 0.0;
            // 卡扣金额
            Double productCardDeduction = 0.0;
            // 服务项目金额
            Double projectMoney = 0.0;
            // 实操总计
            Integer operationCapability = 0;
            // 售前现金金额
            Double preSaleAchievement = 0.0;
            // 售后现金金额
            Double afterSaleAchievement = 0.0;

            String empId = userServiceList.getTechnician();
            if(!StringUtils.isEmpty(empId)) {
                Map<String, String> empInfo = getEmployeeInfo(empId, employees);
                if(!empInfo.isEmpty()) {
                    empName = empInfo.get("empName");
                    phoneNum = empInfo.get("empPhoneNum");
                }
            }

            String payType = userServiceList.getPayType();
            // payType = 1 表示现金支付， payType = 2 表示卡扣
            if(!StringUtils.isEmpty(payType)) {
                String payMoney = userServiceList.getPayMoney();
                if(CommonUtils.validStr(payMoney)) {
                    Double doublePayMoney = Double.parseDouble(payMoney);
                    if(payType.equals("1")) {
                        productCash = doublePayMoney;
                    } else if(payType.equals("2")) {
                        productCardDeduction = doublePayMoney;
                    }
                }
            }
            // 服务项目
            String project = userServiceList.getProject();
            if(!StringUtils.isEmpty(project)) {
                // 实操总计
                operationCapability = project.split(",").length;
                // 具体服务项目
                project = getProjectName(project, itemTypeManages);
            }
            // 服务项目金额
            String payMoney = userServiceList.getPayMoney();
            if(CommonUtils.validStr(payMoney)) {
                projectMoney = Double.parseDouble(payMoney);
                // 确定是现金支付
                if(payType.equals("1")) {
                    String isPreSale = userServiceList.getIsPreSale();
                    // 确定是售前还是售后; isPreSale = 1 售前，isPreSale = 2 售后
                    if(isPreSale.equals("1")) {
                        preSaleAchievement = projectMoney;
                    } else if(isPreSale.equals("2")) {
                        afterSaleAchievement = projectMoney;
                    }
                }
            }

            storeDailyReport.setTime(time);
            storeDailyReport.setEmpName(empName);
            storeDailyReport.setPhoneNum(phoneNum);
            storeDailyReport.setClientName(clientName);
            storeDailyReport.setFaceAppoint(operationCapabilities.get("faceAppoint"));
            storeDailyReport.setFaceNotAppoint(operationCapabilities.get("faceNotAppoint"));
            storeDailyReport.setBodyAppoint(operationCapabilities.get("bodyAppoint"));
            storeDailyReport.setBodyNotAppoint(operationCapabilities.get("bodyNotAppoint"));
            storeDailyReport.setProductCash(productCash);
            storeDailyReport.setProductCardDeduction(productCardDeduction);
            storeDailyReport.setProjects(project);
            storeDailyReport.setProjectsMoney(projectMoney);
            storeDailyReport.setOperationCapability(operationCapability);
            storeDailyReport.setPreSaleAchievement(preSaleAchievement);
            storeDailyReport.setAfterSaleAchievement(afterSaleAchievement);

            storeDailyReports.add(storeDailyReport);
        }
        return storeDailyReports;
    }

    // 根据员工 id 匹配出员工信息，包括员工姓名，电话号码
    public Map<String, String> getEmployeeInfo(String employeeId, List<Employee> employees) {
        Map<String, String> empInfo = new HashMap<>();
        for(Employee employee : employees) {
            if(employeeId.equals(employee.getId() + "")) {
                empInfo.put("empName", employee.getEmpName());
                empInfo.put("empPhoneNum", employee.getPhoneNum());
                break;
            }
        }
        return empInfo;
    }

    // 根据 userId 找出对应的顾客姓名
    public String getClientNameById(int userId, List<UserManage> userManages) {
        for(UserManage userManage : userManages) {
            if(userId == userManage.getId()) {
                return userManage.getUserName();
            }
        }
        return null;
    }

    // 根据 project 字符串，找出服务项目的名称
    public String getProjectName(String project, List<ItemTypeManage> itemTypeManages) {
        StringBuilder projects = new StringBuilder();
        String[] ps = project.split(",");
        for(String p : ps) {
            for(ItemTypeManage itemTypeManage : itemTypeManages) {
                if(CommonUtils.validStr(p) && p.equals(itemTypeManage.getId() + "")) {
                    projects.append(itemTypeManage.getTypeName()).append(";");
                }
            }
        }
        return projects.toString();
    }

    public static void main(String[] args) {
        String a = "100,84";
        System.out.println(a.split(",").length);
    }
}
