package po;

import java.io.Serializable;
import java.time.LocalDateTime;

import vo.CheckOutVO;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2016/12/4
 *
 */
public class CheckOutPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8650256015779165482L;

	//	订单编号
	private String orderID;
	
	//	退房时间
	private LocalDateTime checkOutTime;
	
	public CheckOutPO(String orderID,LocalDateTime checkOutTime) {
		this.orderID = orderID;
		this.checkOutTime = checkOutTime;
	}

	public CheckOutPO(CheckOutVO checkOutVO) {
		this.orderID = checkOutVO.orderID;
		this.checkOutTime = checkOutVO.checkOutTime;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

}
