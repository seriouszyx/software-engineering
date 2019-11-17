package vo;

import po.GuestEvaluationPO;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2016/12/3
 *
 * 客户评价酒店时使用
 */
public class GuestEvaluationVO {
	
	//	订单编号
	public String orderID;
	
	//	评分
	public double score;
	
	//	评价
	public String comment;
	
	/**
	 * 
	 * @author 61990
	 * lastChangedBy 61990
	 * updateTime 2016/11/29
	 * 
	 * 成员变量构造函数
	 */
	public GuestEvaluationVO(String orderID, double score, String comment) {
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
	public GuestEvaluationVO(GuestEvaluationPO evaluationPO) {
		this.orderID = evaluationPO.getOrderID();
		this.score = evaluationPO.getScore();
		this.comment = evaluationPO.getComment();
	}

}
