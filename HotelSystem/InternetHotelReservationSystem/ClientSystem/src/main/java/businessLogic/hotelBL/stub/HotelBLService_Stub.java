package businessLogic.hotelBL.stub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogicService.hotelBLService.HotelBLService;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import utilities.enums.SearchCriteriaType;
import utilities.enums.SortStrategy;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.HotelVO;
import vo.RoomInfoVO;
import vo.SearchCriteriaVO;

public class HotelBLService_Stub implements HotelBLService{
	List<RoomInfoVO> roomList;
	List<HotelVO> hotelList;
	
	public HotelBLService_Stub() {
		roomList = new ArrayList<RoomInfoVO>();
		roomList.add(new RoomInfoVO("12345678",RoomType.DOUBLE_BED,20,20,100));
		roomList.add(new RoomInfoVO("12345678",RoomType.SINGLE_BED,20,20,50));
		
		hotelList = new ArrayList<HotelVO>();
		hotelList.add(new HotelVO("12345678","1天", "南京", "center", "address", "4" ,
				5,123,"good","allEquipment"));
		hotelList.add(new HotelVO("12345679","2天", "南京", "center", "address", "4" ,
				5,123,"good","allEquipment"));
	}

	public HotelVO getHotelInfo(String userID) {
		return hotelList.get(0);
	}


	public ResultMessage updateHotelInfo(HotelVO hotelVO) {
		return ResultMessage.SUCCESS;
	}


	public Iterator<RoomInfoVO> getHotelRoomInfo(String userID) {
		return roomList.iterator();
	}

	public ResultMessage updateHotelRoomInfo(RoomInfoVO roomInfoVO) {
		return ResultMessage.SUCCESS;
	}


	public ResultMessage updateCheckIn(CheckInVO checkInVO) {
		return ResultMessage.SUCCESS;
	}


	public ResultMessage updateCheckOut(CheckOutVO checkOutVO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public Iterator<HotelVO> getHotels(String city,String circle) {
		return hotelList.iterator();
	}

	@Override
	public Iterator<HotelVO> sortHotels(SortStrategy sortStrategy) {
		return hotelList.iterator();
	}
	
	@Override
	public Iterator<HotelVO> searchHotels(List<SearchCriteriaType> searchCriteriaTypes, SearchCriteriaVO vo) {
		return hotelList.iterator();
	}

	@Override
	public ResultMessage addHotel(HotelVO hotelVO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage checkInOffline(String hotelID, RoomType RoomName, int roomNum) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage checkOutOffline(String hotelID, RoomType RoomName, int roomNum) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public int getRemainRoomNum(String hotelID,RoomType roomType) {
		return 10;
	}

	@Override
	public int getOriginPrice(String hotelID, RoomType roomType) {
		return 100;
	}

	@Override
	public void setGuestID(String guestID) {
	}

	@Override
	public void setHotelID(String hotelID) {
	}

	@Override
	public ResultMessage addRoomType(RoomInfoVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public Iterator<HotelVO> getAllBookedHotels() {
		return hotelList.iterator();
	}

}
