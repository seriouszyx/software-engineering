package businessLogic.promotionBL.discountCalculation;

import java.time.LocalDate;

import businessLogic.promotionBL.discountCalculation.discountOfPromotions.EnterpriseMemberDiscount;
import businessLogic.promotionBL.discountCalculation.discountOfPromotions.ThreeAndAboveDiscount;
import businessLogic.promotionBL.discountCalculation.discountOfPromotions.VIPBirthdayDiscount;
import utilities.enums.PromotionType;
import vo.PreOrderVO;

/**
 * @Description:创建酒店无特定期间的促销策略的工厂
 * @author:Harvey Gong
 * @time:2016年12月1日 下午3:20:15
 */
public class HotelFixedDiscountFactory {

	PreOrderVO preOrder;
	LocalDate today;
	

	public HotelFixedDiscountFactory(PreOrderVO preOrder, LocalDate today) {
		this.preOrder = preOrder;
		this.today = today;
	}

	public CalculateDiscount createCalculateDiscount(PromotionType promotionType,double discount){
		switch(promotionType){
		case HOTEL_BIRTHDAY:
			return new VIPBirthdayDiscount(discount,preOrder.guestID,today);
		case HOTEL_ABOVE_THREE_ROOMS:
			return new ThreeAndAboveDiscount(discount,preOrder.roomNum);
		case HOTEL_ENTERPRISE:
			return new EnterpriseMemberDiscount(discount,preOrder.guestID);
		default:
			return null;
		}
	}
	
}
