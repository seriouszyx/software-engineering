package vo;

import po.AddressPO;

public class AddressVO {

	//	酒店所属城市
	public String city;
	
	//	酒店商圈
	public String circle;
	
	// 折扣
	public double discout;
	
	public AddressVO(String city, String circle,double discout) {
		this.city = city;
		this.circle = circle;
		this.discout = discout;
	}
	
	public AddressVO(AddressPO addressPO) {
		this.city = addressPO.getCity();
		this.circle = addressPO.getCircle();
		this.discout = addressPO.getDiscout();
	}
}
