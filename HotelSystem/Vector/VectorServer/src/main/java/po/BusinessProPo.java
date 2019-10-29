package po;

import java.io.Serializable;

/**
 * @version 2016-12-10
 * @author 金灵益
 * @description 特定商圈的促销策略，属性：商圈名，折扣
 */
public class BusinessProPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String businessName;
	private double discount;
	
	public BusinessProPo(String businessName, double discount){
		this.businessName = businessName;
		this.discount = discount;
	}
	
	public String getBusinessName(){
		return businessName;
	}
	
	public double getDiscount(){
		return discount;
	}
}
