package po;

import java.io.Serializable;

import vo.GuestEvaluationVO;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2016/12/3
 *
 * 客户评价酒店时使用
 */
public class GuestEvaluationPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7207366321921809864L;

	//	订单编号
	private String orderID;
	
	//	评分
	private double score;
	
	//	评价
	private String comment;
	
	/**
	 * 
	 * @author 61990
	 * lastChangedBy 61990
	 * updateTime 2016/11/29
	 * 
	 * 成员变量构造函数
	 */
	public GuestEvaluationPO(String orderID, double score, String comment) {
		this.orderID = orderID;
		this.score = score;
		this.comment = comment;
	}

	/**
	 * 
	 * @author 61990
	 * lastChangedBy 61990
	 * updateTime 2016/11/29
	 * 
	 * 根据PO创建VO
	 */
	public GuestEvaluationPO(GuestEvaluationVO evaluationVO) {
		this.orderID = evaluationVO.orderID;
		this.score = evaluationVO.score;
		this.comment = evaluationVO.comment;
	}

	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
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
