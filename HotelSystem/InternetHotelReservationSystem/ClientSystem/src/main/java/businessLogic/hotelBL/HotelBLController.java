package businessLogic.hotelBL;
 import java.util.Iterator;
import java.util.List;

import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.hotelBL.hotelScan.HotelScan;
import businessLogicService.hotelBLService.HotelBLService;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import utilities.enums.SearchCriteriaType;
import utilities.enums.SortStrategy;
import vo.HotelVO;
import vo.RoomInfoVO;
import vo.SearchCriteriaVO;


/**
 * @Description:对一个具体酒店的操作
 * @author:Harvey Gong
 * @time:2016年11月29日 下午6:43:08
 */
public class HotelBLController implements HotelBLService {

	private static HotelBLController hotelController = new HotelBLController();
	private Hotel hotel;
	private HotelScan hotelScan;

	
	private HotelBLController() {
		hotel = new Hotel();
		hotelScan = new HotelScan();
	}
	
	/**
	 * @Description:当客户需要查看酒店时调用该方法
	 * @param guestID
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:13:38
	 */
	public void setGuestID(String guestID){
		hotelScan = new HotelScan(guestID);
	}
	
	
	/**
	 * @Description:当酒店工作人员登录酒店时调用该方法
	 * @param hotelID
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:17:10
	 */
	public void setHotelID(String hotelID){
		hotel = new Hotel(hotelID);
	}

	public static HotelBLController getInstance(){
		return hotelController;
	}

	/**
	 *  对单个hotel的操作
	 */
	
	//增
	public ResultMessage addHotel(HotelVO hotelVO) {
		return hotel.addHotelInfo(hotelVO);
	}
	//改
	public ResultMessage updateHotelInfo(HotelVO hotelVO) {
		return hotel.updateHotelInfo(hotelVO);
	}
	//查
	public HotelVO getHotelInfo(String hotelID) {
		return hotel.getHotelInfo(hotelID);
	}
	
	
	/**
	 * 对room的操作
	 */

	//增
	@Override
	public ResultMessage addRoomType(RoomInfoVO roomInfoVO) {
		return hotel.addRoomInfo(roomInfoVO);
	}
	
	//改
	public ResultMessage updateHotelRoomInfo(RoomInfoVO roomInfoVO) {
		return hotel.updateHotelRoomInfo(roomInfoVO);
	}
	
	//查
	public Iterator<RoomInfoVO> getHotelRoomInfo(String hotelID) {
		return hotel.getHotelRoomInfo(hotelID);
	}
	
	//指定房型的剩余房间数量
	public int getRemainRoomNum(String hotelID,RoomType roomType){
		return hotel.getRemainNumOfSpecificType(hotelID,roomType);
	}
	
	//指定房型的原始价格
	@Override
	public int getOriginPrice(String hotelID, RoomType roomType) {
		return hotel.getOriginPrice(hotelID,roomType);
	}

	
	/**
	 * @Description:线下的入住和退房操作，分别调用这两个方法
	 * @param hotelID
	 * @param RoomName
	 * @param roomNum
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月8日 上午12:34:02
	 */
	@Override
	public ResultMessage checkInOffline(String hotelID,RoomType roomType, int roomNum) {
		return hotel.checkIn(hotelID,roomType,roomNum);
	}

	@Override
	public ResultMessage checkOutOffline(String hotelID,RoomType roomType, int roomNum) {
		return hotel.checkOut(hotelID,roomType,roomNum);
	}
	
	/**
	 * @Description:获取所有的已经预订的酒店，不区分城市、商圈
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月24日 下午5:00:47
	 */
	@Override
	public Iterator<HotelVO> getAllBookedHotels() {
		return hotelScan.getAllBookedHotels();
	}
	
	/**
	 *  浏览概况时的操作
	 */
	
	//获得该城市商圈的所有酒店
	public Iterator<HotelVO> getHotels(String city,String circle) {
		return hotelScan.getHotels(city,circle);
	}
	//排序
	public Iterator<HotelVO> sortHotels(SortStrategy sortStrategy) {
		return hotelScan.sortHotels(sortStrategy);
	}
	//筛选
	public Iterator<HotelVO> searchHotels(List<SearchCriteriaType> searchCriteriaTypes,SearchCriteriaVO vo) {
		return hotelScan.searchHotels(searchCriteriaTypes,vo);
	}

}
