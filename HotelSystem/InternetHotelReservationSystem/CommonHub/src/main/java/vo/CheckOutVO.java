package vo;

import java.time.LocalDateTime;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2016/12/4
 *
 */
public class CheckOutVO {

	//	订单编号
	public String orderID;
	
	//	退房时间
	public LocalDateTime checkOutTime;
	
	/**
	 * 
	 * @author 61990
	 * lastChangedBy charles
	 * updateTime 2016/12/4
	 */
	public CheckOutVO(String orderID, LocalDateTime checkOutTime) {
		this.orderID = orderID;
		this.checkOutTime = checkOutTime;
	}
}
