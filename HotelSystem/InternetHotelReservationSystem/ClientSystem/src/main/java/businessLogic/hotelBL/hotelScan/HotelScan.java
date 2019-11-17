package businessLogic.hotelBL.hotelScan;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.hotelBL.hotel.HotelInfoOperation;
import businessLogic.hotelBL.hotelScan.searchCriteria.SearchCriteriaFactory;
import businessLogic.hotelBL.hotelScan.sortComparator.SortComparatorFactory;
import businessLogic.orderBL.Order;
import dataService.hotelDataService.HotelDataService;
import po.HotelPO;
import rmi.ClientRemoteHelper;
import utilities.enums.OrderState;
import utilities.enums.SearchCriteriaType;
import utilities.enums.SortStrategy;
import vo.HotelVO;
import vo.SearchCriteriaVO;

/**
 * @Description:浏览酒店的类，为酒店浏览及搜索提供排序及范围搜索的功能
 * @author:Harvey Gong
 * @time:2016年12月3日 下午9:55:35
 */
public class HotelScan {

	private HotelDataService hotelDataService;
	
	//用以保存处于当前城市商圈的所有酒店概况
	private List<HotelPO> hotelPOList;
	
	//用以保存符合当前搜索条件的所有酒店概况
	private List<HotelPO> currentPOList;
	
	//各个搜索器的工厂
	private SortComparatorFactory sortComparatorFactory; 
	
	private String guestID;
	
	public HotelScan(String guestID) {

		initHotelDataService();
		sortComparatorFactory = new SortComparatorFactory();
		this.guestID = guestID;
	}

	private void initHotelDataService(){
		hotelDataService = ClientRemoteHelper.getInstance().getHotelDataService();
	}
	
	public HotelScan() {
		initHotelDataService();
	}

	/**
	 * @Description:获取所有已经预订过的酒店，不区分城市商圈
	 * @return
	 * Iterator<HotelVO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月24日 下午5:02:05
	 */
	public Iterator<HotelVO> getAllBookedHotels() {
		List<HotelPO> list = new ArrayList<HotelPO>();
		HotelInfoOperation hotel = new Hotel();
		List<String> bookedHotelID = new Order().getBookedHotels(guestID);
		for(int i = 0;i<bookedHotelID.size();i++){
			list.add(hotel.getHotelPO(bookedHotelID.get(i)));
		}
		return convertPOListToVOList(list).iterator();
	}
	
	/**
	 * @Description:根据城市商圈的信息查找所有位于该城市商圈的酒店
	 * @param addressVO
	 * @return
	 * Iterator<HotelVO>
	 * @author: Harvey Gong
	 * @time:2016年11月29日 下午9:01:13
	 */
	public Iterator<HotelVO> getHotels(String city,String circle){
		try {
			hotelPOList = hotelDataService.getHotels(city,circle);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		currentPOList = hotelPOList;
		return convertPOListToVOList(currentPOList).iterator();
	}
	
	/**
	 * @Description:根据传入的SortStrategy的enum值，对当前hotelList排序
	 * @param sortStrategy
	 * @return
	 * Iterator<HotelGeneralVO>
	 * @author: Harvey Gong
	 * @time:2016年11月29日 下午9:24:12
	 */
	public Iterator<HotelVO> sortHotels(SortStrategy sortStrategy){
		List<HotelVO> hotelVOList = convertPOListToVOList(currentPOList);
		Comparator<HotelVO> comparator = sortComparatorFactory.createComparator(sortStrategy);
		hotelVOList.sort(comparator);
		//保存当前的顺序
		currentPOList = convertVOListToPOList(hotelVOList);
		return hotelVOList.iterator();
	}
	
	/**
	 * @Description:根据传入的搜索标准对当前该城市商圈内的hotelPOList进行筛选
	 * @param searchCriteriaTypes
	 * @return
	 * Iterator<HotelGeneralVO>
	 * @author: Harvey Gong
	 * @time:2016年11月29日 下午8:33:25
	 */
	public Iterator<HotelVO> searchHotels(List<SearchCriteriaType> searchCriteriaTypes,SearchCriteriaVO searchCriteriaVO) {
		List<HotelVO> hotelVOList = convertPOListToVOList(hotelPOList);
		SearchCriteriaFactory searchCriteriaFactory = new SearchCriteriaFactory();
		for(int i = 0;i < searchCriteriaTypes.size();i++){
			//由searchCriteria的工厂根据searchCriteriaType创建具体的searchCriteria，再调用meetCriteria
			hotelVOList = searchCriteriaFactory.createSearchCriteria(searchCriteriaTypes.get(i), searchCriteriaVO).meetCriteria(hotelVOList);
		}
		//保存当前的搜索条件
		currentPOList = convertVOListToPOList(hotelVOList);
		return hotelVOList.iterator();
	}
	
	private List<HotelVO> convertPOListToVOList(List<HotelPO> POList){
		List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
		for(HotelPO hotelPO:POList){
			double minPrice = new Hotel().getLowestPrice(hotelPO.getHotelID());
			OrderState orderState = new Order().getOrderState(guestID, hotelPO.getHotelID());
			hotelVOList.add(new HotelVO(hotelPO,minPrice,orderState));
		}
		return hotelVOList;
	}
	
	private List<HotelPO> convertVOListToPOList(List<HotelVO> VOList){
		List<HotelPO> hotelPOList = new ArrayList<HotelPO>();
		for(HotelVO hotelGeneralVO:VOList){
			hotelPOList.add(new HotelPO(hotelGeneralVO));
		}
		return hotelPOList;
	}

}
