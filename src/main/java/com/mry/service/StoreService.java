package com.mry.service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.mry.config.SmsSetting;
import com.mry.data.StoreData;
import com.mry.enums.DateFormat;
import com.mry.model.Brand;
import com.mry.model.Card;
import com.mry.model.Client;
import com.mry.model.Customer;
import com.mry.model.CustomerIpAddress;
import com.mry.model.Extension;
import com.mry.model.Instrument;
import com.mry.model.Item;
import com.mry.model.Member;
import com.mry.model.Problem;
import com.mry.model.RegistStatus;
import com.mry.model.Room;
import com.mry.model.RoomType;
import com.mry.model.Salary;
import com.mry.model.Store;
import com.mry.model.WaterInfo;
import com.mry.param.BrandParam;
import com.mry.param.ClientParam;
import com.mry.param.ItemParam;
import com.mry.param.ProblemParam;
import com.mry.param.RoomParam;
import com.mry.param.SalaryParam;
import com.mry.param.StoreParam;
import com.mry.param.WaterParam;
import com.mry.repository.BrandRepository;
import com.mry.repository.CardRepository;
import com.mry.repository.ClientRepository;
import com.mry.repository.CustomerIpAddressRepository;
import com.mry.repository.CustomerRepository;
import com.mry.repository.ExtensionRepository;
import com.mry.repository.InstrumentRepository;
import com.mry.repository.ItemRepository;
import com.mry.repository.MemberRepository;
import com.mry.repository.ProblemRepository;
import com.mry.repository.RegistStatusRepository;
import com.mry.repository.RoomRepository;
import com.mry.repository.RoomTypeRepository;
import com.mry.repository.SalaryRepository;
import com.mry.repository.StoreRepository;
import com.mry.repository.WaterInfoRepository;
import com.mry.sms.SendSms;
import com.mry.utils.CommonUtils;

@Service
@Transactional
public class StoreService {
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private CustomerRepository customerRepository;
	@Resource
	private RoomRepository roomRepository;
	@Resource
	private RoomTypeRepository roomTypeRepository;
	@Resource
	private MemberRepository memberRepository;
	@Resource
	private WaterInfoRepository waterInfoRepository;
	@Resource
	private BrandRepository brandRepository;
	@Resource
	private InstrumentRepository instrumentRepository;
	@Resource
	private SalaryRepository salaryRepository;
	@Resource
	private ClientRepository clientRepository;
	@Resource
	private ProblemRepository problemRepository;
	@Resource
	private ItemRepository itemRepository;
	@Resource
	private CardRepository cardRepository;
	@Resource
	private ExtensionRepository extensionRepository;
	@Resource
	private RegistStatusRepository registStatusRepository;
	@Resource
	private CustomerIpAddressRepository customerIpAddressRepository; 
	@Resource
	private SmsSetting smsSetting;
	@Resource
	private SendSms sendSms;
	
	// 根据 storeId 获取门店联系人的信息
	public Customer getCustomerByStoreId(int storeId) {
		Store store = storeRepository.getStoreById(storeId);
		Customer customer = null;
		if(null != store) {
			customer = customerRepository.getCustomerById(store.getCustomerId());
		} 
		return customer;
	}
	
	// 根据门店联系人的 id 获取关联门店的 storeId
	public Integer getStoreIdByCustomerId(int customerId) {
		return storeRepository.getStoreIdByCustomerId(customerId);
	}
	
	// 获取门店与管理员信息
	public StoreParam getStoreInfoByStoreId(int storeId) {
		StoreParam storeParam = new StoreParam();
		// 门店信息
		Store store = storeRepository.getStoreById(storeId);
		storeParam.setStore(store);
		// 门店管理员人信息
		Customer customer = customerRepository.getCustomerById(store.getCustomerId());
		storeParam.setCustomer(customer);
		return storeParam;
	}
	
	// 根据 storeId 返回 一条 store 信息
	public StoreData getStoreDataByStoreId(int storeId) {
		return storeRepository.getStoreDataByStoreId(storeId);
	}
	
	public List<StoreData> getStoreDataByCustomerId(int customerId) {
		return storeRepository.getStoreDataByCustomerId(customerId);
	}
	
	// 根据 customerId + status 返回  store 信息
	public List<StoreData> getStoreDataByCustomerIdAndStoreStatus(int customerId, String status) {
		return storeRepository.getStoreDataByCustomerIdAndStoreStatus(customerId, status);
	}
	
	// 根据审核状态分页获取 store 信息
	public Page<StoreData> getStoreByPage(Pageable pageRequest, String status) {
		return storeRepository.getStoreByPageAndCondition(pageRequest, status);
	}
	
	// 根据审核状态 + 门店名模糊查询 分页获取 store 信息
	public Page<StoreData> getStoreByPageAndStoreName(Pageable pageRequest, String status, String storeName) {
		return storeRepository.getStoreByPageAndStoreName(pageRequest, status, storeName);
	}
	
	// 查出所有还在审核中的门店信息(status=0)
	public List<StoreData> getStoreByReview() {
		return storeRepository.getStoreByReview();
	}
	
	// 根据门店名模糊查询出所有符合需求的门店信息
	public List<Store> getStoreByName(String name) {
		return storeRepository.getStoreByName(name);
	}
	
	// 注册店铺信息
	public int registerStore(StoreParam params, HttpServletRequest request) {
		// 注册用户
		Customer customer = params.getCustomer();
		String account = customer.getAccount();
		// 判断用户是否已经存在
		Customer originCustomer = customerRepository.getCustomerByAccount(account);
		if(null != originCustomer) {
			// 覆盖已存在的用户信息
			customer.setId(originCustomer.getId());
			// 如果用户已存在，那么其 IP 地址很可能已被记录，需要清空
			customerIpAddressRepository.deleteCustomerIpAddressByCustomerId(customer.getId());
		}
		customerRepository.save(customer);
		// 重新记录当前用户的 IP 地址
		CustomerIpAddress customerIpAddress = new CustomerIpAddress();
		customerIpAddress.setCustomerId(customer.getId());
		customerIpAddress.setIpAddress(CommonUtils.getIpAddr(request));
		customerIpAddress.setRecordDate(CommonUtils.formatDate(new Date(), DateFormat.FORMAT1.getFormat()));
		customerIpAddressRepository.save(customerIpAddress);
		// 注册门店
		Store store = params.getStore();
		// 判断门店是否已经存在
		Store originStore = storeRepository.getStoreByCustomerId(customer.getId());
		if(null != originStore) {
			// 覆盖原来的门店信息
			store.setId(originStore.getId());
		}
		// 关联门店联系人
		store.setCustomerId(customer.getId());
		storeRepository.save(store);
		// 发短信提醒用户门店注册成功
		String message = "{\"userName\":\"" + customer.getUserName() + "\",\"password\":\"" 
					   + customer.getPassword() + "\",\"storeName\":\"" + store.getStoreName() 
					   + "\"}";
		sendSms.sendSms(account, smsSetting.getRigstMsg(), message);
		return store.getId();
	}
	
	// 修改门店信息
	public int updateStore(Store store) {
		storeRepository.save(store);
		return store.getId();
	}
	
	// 添加与门店关联的房间信息
	public int extendRoomInformation(RoomParam params) {
		// 添加房间基本信息
		Room room = params.getRoom();
		Room originRoom = roomRepository.getRoomByStoreId(params.getStoreId());
		// 如果重复提交，将会覆盖原来的房间信息
		if(null != originRoom) {
			room.setId(originRoom.getId());
		}
		roomRepository.save(room);
		
		// 添加具体房型信息
		List<RoomType> roomTypes = params.getRoomTypes();
		List<RoomType> originRoomTypes = roomTypeRepository.getRoomTypesByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的房型信息
		for(RoomType originRoomType : originRoomTypes) {
			for(RoomType roomType : roomTypes) {
				if(originRoomType.getType().equals(roomType.getType())) {
					roomType.setId(originRoomType.getId());
				}
			}
		}
		roomTypeRepository.saveAll(roomTypes);
		
		// 添加员工信息
		Member member = params.getMember();
		Member originMember = memberRepository.getMemerByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的员工信息
		if(null != originMember) {
			member.setId(originMember.getId());
		}
		memberRepository.save(member);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 2));
		return params.getStoreId();
	}
	
	// 添加与门店关联的流水信息
	public int extendWaterInformation(WaterParam params) {
		List<WaterInfo> waterInfos = params.getWaterInfos();
		List<WaterInfo> originWaterInfos = waterInfoRepository.getWaterInfoByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的流水信息
		for(WaterInfo originWaterInfo : originWaterInfos) {
			for(WaterInfo waterInfo : waterInfos) { 
				if(originWaterInfo.getType().equals(waterInfo.getType())) {
					waterInfo.setId(originWaterInfo.getId());
				}
			}
		}
		waterInfoRepository.saveAll(waterInfos);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 3));
		return params.getStoreId();
	}
	
	// 添加与门店关联的直营品牌 和 美容仪器信息
	public int extendBrandInformation(BrandParam params) {
		// 添加直营品牌信息
		List<Brand> brands = params.getBrands();
		List<Brand> originBrands = brandRepository.getBrandByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的品牌信息
		for(Brand originBrand : originBrands) {
			for(Brand brand : brands) {
				// 如果品牌类型和品牌名一样才覆盖
				if(originBrand.getType().equals(brand.getType()) && originBrand.getName().equals(brand.getName())) {
					brand.setId(originBrand.getId());
				}
			}
		}
		brandRepository.saveAll(brands);
		
		// 添加美容仪器信息
		List<Instrument> instruments = params.getInstruments();
		List<Instrument> originInstruments = instrumentRepository.getInstrumentByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的美容仪器信息
		for(Instrument originInstrument : originInstruments) {
			for(Instrument instrument : instruments) {
				// 如果功效和仪器名一样才覆盖
				if(originInstrument.getEffect().equals(instrument.getEffect()) && originInstrument.getName().equals(instrument.getName())) {
					instrument.setId(originInstrument.getId());
				}
			}
		}
		instrumentRepository.saveAll(instruments);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 4));
		return params.getStoreId();
	}
	
	// 添加与门店关联的薪资制度信息
	public int extendSalaryInformation(SalaryParam params) {
		Salary salary = params.getSalary();
		Salary originSalary = salaryRepository.getSalaryByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的薪资制度信息
		if(null != originSalary) {
			salary.setId(originSalary.getId());
		}
		salaryRepository.save(salary);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 5));
		return params.getStoreId();
	}
	
	// 添加与门店关联的项目表，卡项，拓客方式
	public int extendItemExtensionInformation(ItemParam params) {
		List<Item> items = params.getItems();
		List<Item> originItems = itemRepository.getItemByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的项目表信息 
		for(Item originItem : originItems) {
			for(Item item : items) {
				if(originItem.getName().equals(item.getName())) {
					item.setId(originItem.getId());
				}
			}
		}
		itemRepository.saveAll(items);
		
		Card card = params.getCard();
		Card originCard = cardRepository.getCardByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的卡项信息 
		if(null != originCard) {
			card.setId(originCard.getId());
		}
		cardRepository.save(card);
		
		List<Extension> extensions = params.getExtensions();
		List<Extension> originExtensions = extensionRepository.getExtensionByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的拓客方式信息 
		for(Extension originExtension : originExtensions) {
			for(Extension extension : extensions) {
				if(originExtension.getType().equals(extension.getType())) {
					extension.setId(originExtension.getId());
				}
			}
		}
		extensionRepository.saveAll(extensions);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 6));
		return params.getStoreId();
	}
	
	// 添加与门店关联的客户信息
	public int extendClientInformation(ClientParam params) {
		Client client = params.getClient();
		Client originClient = clientRepository.getClientByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的客户信息
		if(null != originClient) {
			client.setId(originClient.getId());
		}
		clientRepository.save(client);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 7));
		return params.getStoreId();
	}
	
	// 添加与门店关联的经营问题信息
	public int extendProblemInformation(ProblemParam params) {
		Problem problem = params.getProblem();
		Problem originProblem = problemRepository.getProblemByStoreId(params.getStoreId());
		// 重复提交将覆盖原来的经营问题信息
		if(null != originProblem) {
			problem.setId(originProblem.getId());
		}
		problemRepository.save(problem);
		
		// 更新注册信息
		updateRegistStatus(new RegistStatus(params.getStoreId(), 8));
		return params.getStoreId();
	}
	
	// 获取与门店关联的房间信息
	public RoomParam getRoomInfoByStoreId(int storeId) {
		RoomParam roomInfo = new RoomParam();
		Room room = roomRepository.getRoomByStoreId(storeId);
		List<RoomType> roomTypes = roomTypeRepository.getRoomTypesByStoreId(storeId);
		Member member = memberRepository.getMemerByStoreId(storeId);
		
		roomInfo.setStoreId(storeId);
		roomInfo.setRoom(room);
		roomInfo.setRoomTypes(roomTypes);
		roomInfo.setMember(member);
		return roomInfo;
	}
	
	// 获取与门店关联的流水信息
	public WaterParam getWaterInfoByStoreId(int storeId) {
		WaterParam waterInfo = new WaterParam();
		List<WaterInfo> waterInfos = waterInfoRepository.getWaterInfoByStoreId(storeId);
		
		waterInfo.setStoreId(storeId);
		waterInfo.setWaterInfos(waterInfos);
		return waterInfo;
	}
	
	// 获取与门店关联的品牌信息
	public BrandParam getBrandInfoByStoreId(int storeId) {
		BrandParam brandInfo = new BrandParam();
		List<Brand> brands = brandRepository.getBrandByStoreId(storeId);
		List<Instrument> instruments = instrumentRepository.getInstrumentByStoreId(storeId);
		
		brandInfo.setStoreId(storeId);
		brandInfo.setBrands(brands);
		brandInfo.setInstruments(instruments);
		return brandInfo;
	}
	
	// 获取与门店相关联的薪资制度信息
	public SalaryParam getSalaryInfoByStoreId(int storeId) {
		SalaryParam salaryInfo = new SalaryParam();
		Salary salary = salaryRepository.getSalaryByStoreId(storeId);
		
		salaryInfo.setStoreId(storeId);
		salaryInfo.setSalary(salary);
		return salaryInfo;
	}
	
	// 获取与门店关联的客户信息
	public ClientParam getClientInfoByStoreId(int storeId) {
		ClientParam clientInfo = new ClientParam();
		Client client = clientRepository.getClientByStoreId(storeId);
		
		clientInfo.setStoreId(storeId);
		clientInfo.setClient(client);
		return clientInfo;
	}
	
	// 获取与门店关联的经营问题
	public ProblemParam getProblemInfoByStoreId(int storeId) {
		ProblemParam problemInfo = new ProblemParam();
		Problem problem = problemRepository.getProblemByStoreId(storeId);
		
		problemInfo.setStoreId(storeId);
		problemInfo.setProblem(problem);
		return problemInfo;
	}
	
	// 获取与门店关联的项目表，卡项，拓客方式
	public ItemParam getItemInfoByStoreId(int storeId) {
		ItemParam itemInfo = new ItemParam();
		List<Item> items = itemRepository.getItemByStoreId(storeId);
		Card card = cardRepository.getCardByStoreId(storeId);
		List<Extension> extensions = extensionRepository.getExtensionByStoreId(storeId);
		
		itemInfo.setStoreId(storeId);
		itemInfo.setItems(items);
		itemInfo.setCard(card);
		itemInfo.setExtensions(extensions);
		return itemInfo;
	}
	
	// 审核门店信息
	public int auditStore(JSONObject params) {
		int storeId = params.getIntValue("storeId");
		String status = params.getString("status");
		String desc = params.getString("desc");
		return storeRepository.auditStore(storeId, status, desc);
	}
	
	// 修改注册状态
	public void updateRegistStatus(RegistStatus registStatus) {
		RegistStatus originRegistStatus = getRegistStatusByItem(registStatus.getStoreId(), registStatus.getRegistItem());
		if(null == originRegistStatus) {
			registStatusRepository.save(registStatus);
		}
	}
	
	// 根据 storeId 获取门店的注册状态
	public RegistStatus getRegistStatusByStoreId(int storeId) {
		return registStatusRepository.getRegistStatusByStoreId(storeId);
	}
	
	// 根据 storeId + regist_item 获取门店注册状态
	public RegistStatus getRegistStatusByItem(int storeId, int item) {
		return registStatusRepository.getRegistStatusByItem(storeId, item);
	}
	
	// 根据 storeId 获取门店注册状态
	public RegistStatus getRegistStatusByOrder(int storeId) {
		return registStatusRepository.getRegistStatusByOrder(storeId);
	}
}
