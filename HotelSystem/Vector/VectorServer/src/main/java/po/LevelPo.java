package po;

import java.io.Serializable;

/**
 * @version 2016-12-07
 * @author 金灵益
 * @description 会员等级。该po包含一个等级的属性：等级名称，升到该等级所需的信用值，该等级对应的折扣
 *              由网站营销人员制定，普通客户调用
 */
public class
LevelPo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int level;                  //等级
	private int credit;                 //升到该等级所需信用值
	private double discount;            //折扣
	
	public LevelPo(int level, int credit, double discount){
		this.level = level;
		this.credit = credit;
		this.discount = discount;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getCredit(){
		return credit;
	}
	
	public double getDiscount(){
		return discount;
	}
}
