package po;

import java.io.Serializable;

import vo.HotelVO;

public final class HotelPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8529466667245846060L;

	//	酒店编号
	private String hotelID;

	//	酒店名称
	private String hotelName;

	//	酒店城市
	private String city;

	//	酒店商圈
	private String circle;
	
	//  酒店地址
	private String address;

	//	酒店星级
	private String level;

	//	评分
	private double score;

	//	简介
	private String introduction;	

	//	设施服务
	private String equipment;
	
	//  已评分的人数
	private int commentsNum;

	/**
	 * @Description：为测试而生的辣鸡构造器，请依次添加参数
	 * @param hotelID
	 * @param hotelName
	 * @param city
	 * @param circle
	 * @param hotelAddress
	 * @param level
	 * @param score
	 * @param introduction
	 * @param equipment
	 * @param commentsNum
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月5日 上午12:54:24
	 */
	public HotelPO(String hotelID, String hotelName, String city, String circle, String address, 
			String level, double score, String introduction, String equipment, int commentsNum) {
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.city = city;
		this.circle = circle;
		this.address = address;
		this.level = level;
		this.score = score;
		this.introduction = introduction;
		this.equipment = equipment;
		this.commentsNum = commentsNum;
	}

	public HotelPO(HotelVO hotelVO) {
		this.hotelID = hotelVO.hotelID;
		this.hotelName = hotelVO.hotelName;
		this.city = hotelVO.city;
		this.circle = hotelVO.circle;
		this.level = hotelVO.level;
		this.score = hotelVO.score;
		this.introduction = hotelVO.introduction;
		this.equipment = hotelVO.equipment;
		this.commentsNum = hotelVO.commentsNum;
		this.address = hotelVO.address;
	}

	public HotelPO() {
		hotelID = null;
		hotelName = "";
		city = "";
		circle = "";
		level = "";
		score = 0;
		introduction = "";
		equipment = "";
		commentsNum = 0;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
