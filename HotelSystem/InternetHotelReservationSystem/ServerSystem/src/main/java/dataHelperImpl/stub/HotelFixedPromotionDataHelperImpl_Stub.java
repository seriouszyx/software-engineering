package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.HotelFixedPromotionDataHelper;
import po.HotelFixedPromotionPO;
import utilities.enums.PromotionType;
import utilities.enums.ResultMessage;

public class HotelFixedPromotionDataHelperImpl_Stub implements HotelFixedPromotionDataHelper{

	@Override
	public List<HotelFixedPromotionPO> getHotelFixedPromotion(String hotelWorkerID) {
		List<HotelFixedPromotionPO> list = new ArrayList<HotelFixedPromotionPO>();
		list.add(new HotelFixedPromotionPO("12345678",PromotionType.HOTEL_BIRTHDAY,0.9));
		list.add(new HotelFixedPromotionPO("12345678",PromotionType.HOTEL_ENTERPRISE,0.8));
		list.add(new HotelFixedPromotionPO("12345678",PromotionType.HOTEL_ABOVE_THREE_ROOMS,0.7));
		return list;
	}

	@Override
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionPO hotelFixedPromotionPO) {
		return ResultMessage.SUCCESS;
	}

}
