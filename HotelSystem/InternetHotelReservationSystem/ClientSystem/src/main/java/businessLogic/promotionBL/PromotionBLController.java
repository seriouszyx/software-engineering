package businessLogic.promotionBL;

import java.util.Iterator;

import businessLogic.promotionBL.promotions.HotelFixedPromotion;
import businessLogic.promotionBL.promotions.SpecialCirclePromotion;
import businessLogic.promotionBL.promotions.SpecialSpanPromotion;
import businessLogicService.promotionBLService.PromotionBLService;
import utilities.enums.ResultMessage;
import vo.AddressVO;
import vo.HotelFixedPromotionVO;
import vo.SpecialSpanPromotionVO;

public class PromotionBLController implements PromotionBLService {

	private static PromotionBLController promotionController = new PromotionBLController();
	
	private HotelFixedPromotion hotelFixedPromotion;
	private SpecialSpanPromotion specialSpanPromotion;
	private SpecialCirclePromotion specialCirclePromotion;
	
	private PromotionBLController() {
		hotelFixedPromotion = new HotelFixedPromotion();
		specialSpanPromotion = new SpecialSpanPromotion();
		specialCirclePromotion = new SpecialCirclePromotion();
	}

	public static PromotionBLController getInstance(){
		return promotionController;
	}

	/**
	 * @Description:固定策略的获取
	 * @param hotelWorkerID 
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:23:46
	 */
	@Override
	public Iterator<HotelFixedPromotionVO> getHotelFixedPromotions(String hotelWorkerID) {
		return hotelFixedPromotion.getHotelFixedPromotions(hotelWorkerID);
	}

	/**
	 * @Description:固定策略的更新
	 * @param hotelFixedPromotionVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:24:22
	 */
	@Override
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionVO hotelFixedPromotionVO) {
		return hotelFixedPromotion.updateHotelFixedPromotion(hotelFixedPromotionVO);
	}
	
	/**
	 * @Description:酒店特定期间策略的获取
	 * @param hotelID
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:24:36
	 */
	@Override
	public Iterator<SpecialSpanPromotionVO> getHotelSpecialSpanPromotions(String hotelID) {
		return specialSpanPromotion.getHotelSpecialSpanPromotions(hotelID);
	}
	
	/**
	 * @Description:网站特定期间策略的获取
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:24:53
	 */
	@Override
	public Iterator<SpecialSpanPromotionVO> getWebSpecialSpanPromotions() {
		return specialSpanPromotion.getWebSpecialSpanPromotions();
	}
	
	/**
	 * @Description:酒店特定期间策略的添加
	 * @param specialSpanPromotionVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:25:14
	 */
	@Override
	public ResultMessage addHotelSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		return specialSpanPromotion.addSpecialSpanPromotion(specialSpanPromotionVO);
	}
	
	/**
	 * @Description:网站特定期间策略添加
	 * @param specialSpanPromotionVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:42:03
	 */
	@Override
	public ResultMessage addWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		return specialSpanPromotion.addWebSpecialSpanPromotion(specialSpanPromotionVO);
	}
	
	/**
	 * @Description:酒店特定期间策略更新
	 * @param specialSpanPromotionVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:42:20
	 */
	@Override
	public ResultMessage updateHotelSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		return specialSpanPromotion.updateSpecialSpanPromotion(specialSpanPromotionVO);
	}
	
	/**
	 * @Description:网站特定期间策略更新
	 * @param specialSpanPromotionVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:42:40
	 */
	@Override
	public ResultMessage updateWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		return specialSpanPromotion.updateWebSpecialSpanPromotion(specialSpanPromotionVO);
	}

	/**
	 * @Description:酒店特定期间策略删除
	 * @param userID
	 * @param promotionName
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:42:51
	 */
	@Override
	public ResultMessage deleteHotelSpecialSpanPromotion(String userID,String promotionName) {
		return specialSpanPromotion.deleteSpecialSpanPromotion(userID,promotionName);
	}
	
	/**
	 * @Description:网站特定期间策略删除
	 * @param promotionName
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:43:05
	 */
	@Override
	public ResultMessage deleteWebSpecialSpanPromotion(String promotionName) {
		return specialSpanPromotion.deleteWebSpecialSpanPromotion(promotionName);
	}
	
	/**
	 * @Description:vip特定商圈策略获取
	 * @param city
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:43:20
	 */
	@Override
	public Iterator<AddressVO> getSpecialCirclePromotions(String city) {
		return specialCirclePromotion.getSpecialCirclePromoitons(city);
	}

	/**
	 * @Description:vip特定商圈策略更新
	 * @param addressVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:43:45
	 */
	@Override
	public ResultMessage updateSpecialCirclePromotions(AddressVO addressVO) {
		return specialCirclePromotion.updateSpecialCirclePromotion(addressVO);
	}

	/**
	 * @Description:根据city和circle获得该城市商圈的折扣
	 * @param city
	 * @param newCircle
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月11日 下午6:44:58
	 */
	@Override
	public double getSpecialCirclePromotion(String city, String circle) {
		return specialCirclePromotion.getSpecialCirclePromoiton(city,circle);
	}

}
