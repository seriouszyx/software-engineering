package vo;

import po.HotelWorkerPO;

public class HotelWorkerVO extends UserVO{
	
	//	酒店名称
	public String hotelName;
	
	public HotelWorkerVO(String hotelWorkerID, String password, String hotelName) {
		super(hotelWorkerID,password);
		this.hotelName = hotelName;
	}
	
	public HotelWorkerVO(String hotelName) {
		super(null,null);
		this.hotelName = hotelName;
	}
	
	public HotelWorkerVO(HotelWorkerPO hotelWorkerPO) {
		super(hotelWorkerPO.getHotelWorkerID(),hotelWorkerPO.getPassword());
		this.hotelName = hotelWorkerPO.getHotelName();
	}
}