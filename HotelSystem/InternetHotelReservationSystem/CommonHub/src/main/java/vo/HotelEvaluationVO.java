package vo;

import java.time.LocalDate;

import po.HotelEvaluationPO;

/**
 * 
 * @author charles
 * lastChangedBy charles
 * updateTime 2016/12/3
 *
 * 客户／酒店工作人员查看酒店评论时使用
 */
public class HotelEvaluationVO {

	//	客户编号ID
	public String guestID;
	
	//	订单创建时间
	public LocalDate checkInDate;
	
	//	评分
	public double score;
	
	//	评价
	public String comment;
	
	/**
	 * 
	 * @author charles
	 * lastChangedBy charles
	 * updateTime 2016/12/3
	 *
	 * 成员变量构造函数
	 */
	public HotelEvaluationVO(String guestID, LocalDate checkInTime, double score, String comment) {
		this.guestID = guestID;
		this.checkInDate = checkInTime;
		this.score = score;
		this.comment = comment;
	}
	
	/**
	 * 
	 * @author charles
	 * lastChangedBy charles
	 * updateTime 2016/12/3
	 *
	 * 根据PO创建VO
	 */
	public HotelEvaluationVO(HotelEvaluationPO hotelEvaluationPO) {
		this.guestID = hotelEvaluationPO.getGuestID();
		this.checkInDate = hotelEvaluationPO.getCheckInDate();
		this.score = hotelEvaluationPO.getScore();
		this.comment = hotelEvaluationPO.getComment();
	}
}
