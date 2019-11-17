package businessLogic.hotelBL;


import businessLogic.hotelBL.hotel.Hotel;
import utilities.Address;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import vo.HotelVO;

public class MockHotel extends Hotel {

	public MockHotel(String hotelWorkerID){
		super(hotelWorkerID);
	}

	public MockHotel() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public ResultMessage addHotelInfo(HotelVO hotelVO) {
		return ResultMessage.SUCCESS;
	}
	public ResultMessage scoreUpdate(double score) {
		return ResultMessage.SUCCESS;
	}
	
	public Address getHotelAddress(String hotelID){
		return new Address("南京","仙林");
	}
	
	public ResultMessage checkIn(String hotelID, RoomType roomType, int roomNum) {
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage checkOut(String hotelID, RoomType roomType, int roomNum) {
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage updateRemainRoomNumForUndoOrder(String hotelID, RoomType roomType, int roomNum) {
		return ResultMessage.SUCCESS;
	}
}
