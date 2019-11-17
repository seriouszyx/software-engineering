package businessLogic.promotionBL.stub;

import java.util.Iterator;

import businessLogicService.promotionBLService.PromotionBLService;
import utilities.enums.ResultMessage;
import vo.AddressVO;
import vo.HotelFixedPromotionVO;
import vo.PreOrderVO;
import vo.SpecialSpanPromotionVO;

public class PromotionBLService_Stub implements PromotionBLService{

	
	public PromotionBLService_Stub() {
	}

	public double getDiscout(PreOrderVO preOrder) {
		return 0.8;
	}

	@Override
	public Iterator<HotelFixedPromotionVO> getHotelFixedPromotions(String hotelWorkerID) {
		return null;
	}

	@Override
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionVO hotelFixedPromotionVO) {
		return null;
	}

	@Override
	public Iterator<SpecialSpanPromotionVO> getHotelSpecialSpanPromotions(String userID) {
		return null;
	}
	
	@Override
	public Iterator<SpecialSpanPromotionVO> getWebSpecialSpanPromotions() {
		return null;
	}

	@Override
	public ResultMessage addHotelSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		return null;
	}

	@Override
	public ResultMessage updateHotelSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		return null;
	}

	@Override
	public Iterator<AddressVO> getSpecialCirclePromotions(String city) {
		return null;
	}

	
	@Override
	public ResultMessage updateSpecialCirclePromotions(AddressVO addressVO) {
		return null;
	}

	@Override
	public ResultMessage deleteHotelSpecialSpanPromotion(String userID, String promotionName) {
		return null;
	}

	@Override
	public double getSpecialCirclePromotion(String value, String newCircle) {
		return 0;
	}

	@Override
	public ResultMessage addWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage updateWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage deleteWebSpecialSpanPromotion(String promotionName) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
