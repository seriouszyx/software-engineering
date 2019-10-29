package vo;
/**
 * @author 金灵益
 * @version 2016/12/03
 * @description
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import po.HotelPo;
import po.HotelTypeRoomPo;


public class HotelVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hotelName;
	private String hotelID;	
	private String province;                                          //酒店所在省份
	private String hotelCity;                                         //市级
	private String hotelPosition;                                     //酒店具体地址
	private String inBusiness;                                        //酒店所属商圈
	private String tel;                                               //酒店联系电话
	private String info;                                              //酒店简介
	private List<String> commentList = new ArrayList<String>();       //酒店的所有文字评价
	private double points;                                            //酒店评分
	private int numOfpoint;                                           //酒店被评分的次数
	private int stars;                                                //酒店星级
	private List<HotelTypeRoomVo> typeRoomList;                       //酒店每种类型房间信息

	
	//po --> vo
	public HotelVo(HotelPo po){
		this.hotelName = po.getHotelName();
		this.hotelID = po.getId();
		this.province = po.getProvince();
		this.hotelCity = po.getHotelCity();
		this.hotelPosition = po.getHotelPosition();
		this.inBusiness = po.getInBusiness();
		this.tel = po.getHotelTel();
		this.info = po.getHotelInfo();
		this.commentList = po.getCommentList();
		this.points = po.getPoStrings();
		this.numOfpoint = po.getNumOfpoint();
		this.stars = po.getStars();

		//typeRoomPo -- > typeRoomVo
		typeRoomList = new ArrayList<HotelTypeRoomVo>();
		Iterator<HotelTypeRoomPo> it = po.getTypeRoom().iterator();
		while(it.hasNext()){
			typeRoomList.add(new HotelTypeRoomVo(it.next()));
		}
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
	           hotelPosition + " " + inBusiness + " " + tel + " " + info;
	}
	
	public String getHotelName() {
		return hotelName;
	}

	public String getId() {
		return hotelID;
	}
	
	public String getProvince(){
		return province;
	}
	
	public String getHotelCity(){
		return hotelCity;
	}
	
	public String getHotelPosition() {
		return hotelPosition;
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

	public List<HotelTypeRoomVo> getTypeRoom(){
		return typeRoomList;
	}
}