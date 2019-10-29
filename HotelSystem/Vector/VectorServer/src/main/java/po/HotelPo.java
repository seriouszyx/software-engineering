package po;
/**
 * @version 2016/12/04
 * @author 金灵益
 */

import java.io.Serializable;
import java.util.List;

public class HotelPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hotelName;
	private String hotelID;
	private String province;                                          //酒店所在省份
	private String hotelCity;                                         //市级
	private String hotelPosition;                                     //酒店具体地址
	private String inBusiness;                                        //酒店所属商圈
	private String tel;                                               //酒店联系电话
	private String info;                                              //酒店简介
	private List<String> commentList;                                 //酒店的所有文字评价
	private String commentOfmember;                                   //客户给予的文字评价
	private double points;                                            //酒店评分
	private int numOfpoint;                                           //酒店被评分的次数
	private int stars;                                                //酒店星级
	private List<HotelTypeRoomPo> typeRoomList;                       //酒店每种类型房间信息
	
	public HotelPo(String hotelId){
		this.hotelID = hotelId;
	}
	
	public HotelPo(String hotelId, String hotelName, String province,String hotelCity, String hotelPosition,
			String inBusiness, String tel, int stars, double points,int numOfpoint, String info, 
			List<String> commentList, List<HotelTypeRoomPo> typeRoomList){
		this.hotelID = hotelId;
		this.hotelName = hotelName;
		this.province = province;
		this.hotelCity = hotelCity;
		this.hotelPosition = hotelPosition;
		this.inBusiness = inBusiness;
		this.tel = tel;
		this.info = info;
		this.commentList = commentList;
		this.points = points;
		this.numOfpoint = numOfpoint;
		this.stars = stars;
		this.typeRoomList = typeRoomList;
	}

	public String getCommentOfmember(){
		return commentOfmember;
	}
	
	public List<String> getCommentList(){
		return commentList;
	}

	public double getPoStrings(){
		return points;
	}
	
	public String getInBusiness() {
		return inBusiness;
	}
	
	public String showInfo(){
		return hotelName + " " + hotelID + " " + province + " " + hotelCity + " " + 
		       inBusiness + " " + hotelPosition + " " + tel + " " + info;
	}
	
	public String getHotelName() {
		return hotelName;
	}
	
	public String getId() {
		return hotelID;
	}

	public String getHotelPosition() {
		return hotelPosition;
	}

	public String getProvince(){
		return province;
	}
	
	public String getHotelCity(){
		return hotelCity;
	}

	public String getHotelTel() {
		return tel;
	}
	
	public String getHotelInfo(){
		return info;
	}
	
	public int getNumOfpoint(){
		return numOfpoint;
	}
	
	public int getStars(){
		return stars;
	}

	public List<HotelTypeRoomPo> getTypeRoom(){
		return typeRoomList;
	}
}