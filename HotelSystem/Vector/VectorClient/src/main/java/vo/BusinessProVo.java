package vo;

import java.io.Serializable;

import po.BusinessProPo;

/**
 * @version 2016-12-10
 * @author 金灵益
 * @description 特定商圈的促销策略，属性：商圈名，折扣
 */
public class BusinessProVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String businessName;
	private double discount;
	
	public BusinessProVo(BusinessProPo po){
		this.businessName = po.getBusinessName();
		this.discount = po.getDiscount();
	}
	
	public BusinessProVo(String businessName, double discount){
		this.businessName = businessName;
		this.discount = discount;
	}
	
	public void setDiscount(double d){
		this.discount = d;
	}
	
	public String getBusinessName(){
		return businessName;
	}
	
	public double getDiscount(){
		return discount;
	}
}