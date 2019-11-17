package po;

import java.io.Serializable;
import java.time.LocalDate;

import vo.HotelEvaluationVO;

public class HotelEvaluationPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7274690382893551934L;

	//	客户编号ID
	private String guestID;
	
	//	订单创建时间
	private LocalDate checkInDate;
	
	//	评分
	private double score;
	
	//	评价
	private String comment;
	
	/**
	 * 
	 * @author charles
	 * lastChangedBy charles
	 * updateTime 2016/12/3
	 *
	 * 成员变量构造函数
	 */
	public HotelEvaluationPO(String guestID, LocalDate checkInTime, double score, String comment) {
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
	 * 根据VO创建PO
	 */
	public HotelEvaluationPO(HotelEvaluationVO hotelEvaluationVO) {
		this.guestID = hotelEvaluationVO.guestID;
		this.checkInDate = hotelEvaluationVO.checkInDate;
		this.score = hotelEvaluationVO.score;
		this.comment = hotelEvaluationVO.comment;
	}

	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
