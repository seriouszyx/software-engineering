package businessLogic.promotionBL.discountCalculation.discountOfPromotions;

import businessLogic.promotionBL.discountCalculation.CalculateDiscount;

public class ThreeAndAboveDiscount implements CalculateDiscount{

	private int standardNum = 3;
	private int roomNum;
	private double discount;
	
	public ThreeAndAboveDiscount(double discount, int roomNum) {
		this.discount = discount;
		this.roomNum = roomNum;
	}

	//比较预订的房间数量是否大于3间，若是，则返回该折扣
	@Override
	public double getDiscount() {
		if(roomNum>=standardNum){
			return discount;
		}
		else
		{
			return 1;
		}
	}

}
