package dataHelperImpl.stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataHelper.SpecialSpanPromotionDataHelper;
import po.SpecialSpanPromotionPO;
import utilities.enums.PromotionType;
import utilities.enums.ResultMessage;

public class SpecialSpanPromotionImplDataHelper_Stub implements SpecialSpanPromotionDataHelper{

	@Override
	public List<SpecialSpanPromotionPO> getHotelSpecialSpanPromotion(String hotelID) {
		List<SpecialSpanPromotionPO> list = new ArrayList<SpecialSpanPromotionPO>();
		LocalDate startDate = LocalDate.of(2016, 11, 11);
		LocalDate endDate = LocalDate.of(2016, 11, 12);
		list.add(new SpecialSpanPromotionPO("12345678",PromotionType.HOTEL_HOLIDAY,"双十一折扣",0.9,startDate,endDate));
		list.add(new SpecialSpanPromotionPO("12345678",PromotionType.HOTEL_HOLIDAY,"双十二折扣",0.8,startDate.plusMonths(1),endDate.plusMonths(1)));
		list.add(new SpecialSpanPromotionPO("12345678",PromotionType.HOTEL_HOLIDAY,"春节折扣",0.7,startDate.plusYears(1),endDate.plusYears(1)));
		return list;
	}

	@Override
	public List<SpecialSpanPromotionPO> getWebSpecialSpanPromotion() {
		List<SpecialSpanPromotionPO> list = new ArrayList<SpecialSpanPromotionPO>();
		LocalDate startDate = LocalDate.of(2016, 11, 11);
		LocalDate endDate = LocalDate.of(2016, 11, 12);
		list.add(new SpecialSpanPromotionPO("99999999",PromotionType.HOTEL_HOLIDAY,"双十一折扣",0.9,startDate,endDate));
		list.add(new SpecialSpanPromotionPO("99999999",PromotionType.HOTEL_HOLIDAY,"中秋折扣",0.8,startDate.plusMonths(1),endDate.plusMonths(1)));
		list.add(new SpecialSpanPromotionPO("99999999",PromotionType.HOTEL_HOLIDAY,"元旦折扣",0.7,startDate.plusYears(1),endDate.plusYears(1)));
		return list;
	}

	@Override
	public ResultMessage addSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage updateSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteSpecialSpanPromotion(String userID, String promotionName) {
		return ResultMessage.SUCCESS;
	}

}
