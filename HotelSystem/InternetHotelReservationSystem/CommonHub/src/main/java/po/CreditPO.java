package po;

import java.io.Serializable;
import java.time.LocalDateTime;

import utilities.enums.CreditRecord;
import vo.CreditVO;

public class CreditPO implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3755463324226710790L;

	//	用户编号	
	private String guestID;
	
	//	信用值更改时间
	private LocalDateTime time;
	
	//	订单编号
	private String orderID;
	
	//	变化前信用值
	private double previousCredit;
	
	//	变化后信用值
	private double afterCredit;
	
	//	变化原因 即动作
	private CreditRecord reason;
	
	public CreditPO(){}
	
	public CreditPO(String guestID, LocalDateTime time, String orderID, double previousCredit, double afterCredit, CreditRecord reason) {
		this.guestID = guestID;
		this.time = time;
		this.orderID = orderID;
		this.previousCredit = previousCredit;
		this.afterCredit = afterCredit;
		this.reason = reason;
	}

	public CreditPO(CreditVO creditVO) {
		this.guestID = creditVO.guestID;
		this.orderID = creditVO.orderID;
		this.time = creditVO.time;
		this.previousCredit = creditVO.previousCredit;
		this.afterCredit = creditVO.afterCredit;
		this.reason = creditVO.reason;
	}

	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public double getPreCredit() {
		return previousCredit;
	}

	public void setPreCredit(double previousCredit) {
		this.previousCredit = previousCredit;
	}

	public double getAfterCredit() {
		return afterCredit;
	}

	public void setAfterCredit(double credit) {
		this.afterCredit = credit;
	}

	public CreditRecord getCreditRecord() {
		return reason;
	}

	public void setCreditRecord(CreditRecord reason) {
		this.reason = reason;
	}
	
	
	
	
	
}
