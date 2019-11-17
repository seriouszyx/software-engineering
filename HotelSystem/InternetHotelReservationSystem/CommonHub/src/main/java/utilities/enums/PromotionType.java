package utilities.enums;

import java.io.Serializable;

public enum PromotionType implements Serializable{
	
	//	酒店促销策略
	HOTEL_BIRTHDAY("会员生日折扣"), HOTEL_ENTERPRISE("企业会员折扣"), 
	HOTEL_ABOVE_THREE_ROOMS("三间及以上预订折扣"), HOTEL_HOLIDAY("酒店特定时间段折扣"), 
	
	//	网站促销策略
	WEB_HOLIDAY("网站特定时间段折扣"), WEB__VIP_APPOINTED_CYCLE("VIP特定商圈专属折扣");
	
	private String chinesePromotionType;
	
	private PromotionType(String a) {
		this.chinesePromotionType = a;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * enum TO 汉字
	 * 便于界面调用，写入数据库
	 */
	public String getChinesePromotiontype() {
		return chinesePromotionType;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * 
	 * 汉字 TO enum
	 * 便于从数据库读入
	 */
	public static PromotionType getEnum(String a) {
		for (PromotionType promotionType : values()) {
			if (promotionType.chinesePromotionType.equals(a)) {
				return promotionType;
			}
		}
		return null;
	}
	
}
