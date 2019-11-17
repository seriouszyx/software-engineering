package po;

import java.io.Serializable;

import vo.HotelWorkerVO;

public class HotelWorkerPO implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5089581683128327676L;

	//	酒店编号
	private String hotelWorkerID;
	
	//	密码
	private String password;
	
	//	酒店名称
	private String hotelName;
	
	public HotelWorkerPO(){}
	
	public HotelWorkerPO(String hotelWorkerID, String password) {
		this.hotelWorkerID = hotelWorkerID;
		this.password = password;
	}
	
	public HotelWorkerPO(String hotelWorkerID, String password, String hotelName) {
		this.hotelWorkerID = hotelWorkerID;
		this.password = password;
		this.hotelName = hotelName;
	}
	
	public HotelWorkerPO(HotelWorkerVO hotelWorkerVO) {
		this.hotelWorkerID = hotelWorkerVO.userID;
		this.password = hotelWorkerVO.password;
		this.hotelName = hotelWorkerVO.hotelName;
	}
	

	public String getHotelWorkerID() {
		return hotelWorkerID;
	}
	public void setHotelWorkerID(String hotelWorkerID) {
	 this.hotelWorkerID = hotelWorkerID;
	 }
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
}
