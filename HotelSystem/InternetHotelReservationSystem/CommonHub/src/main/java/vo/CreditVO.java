package vo;

import java.time.LocalDateTime;

import po.CreditPO;
import utilities.enums.CreditRecord;

public class CreditVO {
    
	//	用户编号	
	public String guestID;
	
	//	信用值更改时间
	public LocalDateTime time;
	
	//	订单编号
	public String orderID;
	
	//	变化前信用值
	public double previousCredit;
	
	//	变化后信用值
	public double afterCredit;
	
	//	变化原因 即动作
	public CreditRecord reason;
	
	public CreditVO(String guestID, LocalDateTime time, String orderID, double previousCredit, double afterCredit, CreditRecord reason) {
		this.guestID = guestID;
		this.time = time;
		this.orderID = orderID;
		this.previousCredit = previousCredit;
		this.afterCredit = afterCredit;
		this.reason = reason;
	}
	
	public CreditVO(CreditPO creditPO) {
		this.guestID = creditPO.getGuestID();
		this.time = creditPO.getTime();
		this.orderID = creditPO.getOrderID();
		this.previousCredit = creditPO.getPreCredit();
		this.afterCredit = creditPO.getAfterCredit();
		this.reason = creditPO.getCreditRecord();
	}

}
