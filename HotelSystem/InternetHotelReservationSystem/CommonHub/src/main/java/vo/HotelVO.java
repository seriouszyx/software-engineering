package vo;

import po.HotelPO;
import utilities.enums.OrderState;

/**
 * @Description:hotelVO中存有酒店的基本信息：
 * 编号、名称、城市、商圈、地址、星级、评分、最低价格、简介、设施服务
 * 订单状态（对于当前查看酒店）
 * @author:Harvey Gong
 * lastChangedBy:Harvey Gong
 * @time:2016年12月4日 下午9:44:56
 */
public final class HotelVO {

	//	酒店编号
	public String hotelID;

	//	酒店名称
	public String hotelName;

	//	酒店城市
	public String city;

	//	酒店商圈
	public String circle;

	//  酒店地址
	public String address;

	//	酒店星级
	public String level;

	//	评分
	public double score;

	//  最低价格
	public double minPrice;

	//	简介
	public String introduction;	

	//	设施服务
	public String equipment;

	//  订单的状态
	public OrderState orderState;

	//  酒店被评论次数
	public int commentsNum;


	public HotelVO(String hotelID, String hotelName, String city, String circle, String hotelAddress, 
			String level, double score, double minPrice, String introduction, String equipment) {
		this.hotelID=hotelID;
		this.hotelName=hotelName;
		this.city=city;
		this.circle=circle;
		this.address=hotelAddress;
		this.level=level;
		this.score=score;
		this.minPrice=minPrice;
		this.introduction=introduction;
		this.equipment=equipment;
	}

	/**
	 * @Description:酒店工作人员浏览时，调用此构造方法
	 * @param hotelPO
	 * @author: Harvey Gong
	 * lastChangedBy: Harvey Gong
	 * @time:2016年12月4日 下午9:48:28
	 */
	public HotelVO(HotelPO hotelPO) {
		this.hotelID = hotelPO.getHotelID();
		this.hotelName = hotelPO.getHotelName();
		this.city = hotelPO.getCity();
		this.circle = hotelPO.getCircle();
		this.address = hotelPO.getAddress();
		this.level = hotelPO.getLevel();
		this.score = hotelPO.getScore();
		this.introduction = hotelPO.getIntroduction();
		this.equipment = hotelPO.getEquipment();
	}

	/**
	 * @Description:酒店浏览时，调用此构造方法
	 * @param hotelPO
	 * @param minPrice
	 * @param orderState
	 * @author: Harvey Gong
	 * lastChangedBy: Harvey Gong
	 * @time:2016年12月4日 下午9:48:04
	 */
	public HotelVO(HotelPO hotelPO, double minPrice,OrderState orderState) {
		this(hotelPO);
		this.minPrice = minPrice;
		this.orderState = orderState;
	}

	public HotelVO() {
	}

}
