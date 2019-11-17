package businessLogic.promotionBL.promotions;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.promotionBL.discountCalculation.CalculateDiscount;
import businessLogic.promotionBL.discountCalculation.HotelFixedDiscountFactory;
import businessLogic.sourceBL.SourceBLController;
import dataService.promotionDataService.PromotionDataService;
import exception.verificationException.UserInexistException;
import po.HotelFixedPromotionPO;
import rmi.ClientRemoteHelper;
import utilities.enums.PromotionType;
import utilities.enums.ResultMessage;
import vo.HotelFixedPromotionVO;
import vo.PreOrderVO;

/**
 * @Description:对于酒店会员生日折扣，企业会员折扣以及三间及以上预订的促销策略操作的具体实现
 * 只有get和update，没有添加、删除的功能
 * @author:Harvey Gong
 * @time:2016年12月1日 下午2:08:44
 */
public class HotelFixedPromotion {

	private PromotionDataService promotionDataService;
	private List<HotelFixedPromotionPO> hotelFixedPromotions;

	public HotelFixedPromotion() {
		promotionDataService = ClientRemoteHelper.getInstance().getPromotionDataService();
	}

	/**
	 * @Description:获取酒店没有特定期间的促销策略，会员生日、三间及以上和合作企业会员的策略
	 * @param hotelWorkerID
	 * @return
	 * Iterator<HotelFixedPromotionVO>
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午2:07:11
	 */
	public Iterator<HotelFixedPromotionVO> getHotelFixedPromotions(String hotelWorkerID){
		initHotelFixedPromotions(hotelWorkerID);
		return convertPOListToVOIterator(hotelFixedPromotions);
	}


	/**
	 * @Description:更新酒店没有特定期间的促销策略，会员生日、三间及以上和合作企业会员的策略
	 * 只进行单条更新
	 * @param hotelFixedPromotionVO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午2:08:49
	 */
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionVO hotelFixedPromotionVO){
		try {
			return promotionDataService.updateHotelFixedPromotion(new HotelFixedPromotionPO(hotelFixedPromotionVO));
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:根据传入的订单信息和需要计算策略的日期，
	 * 计算酒店没有特定期间的促销策略的折扣，会员生日、三间及以上和合作企业会员的策略
	 * @param preOrder
	 * @return
	 * double
	 * @author: Harvey Gong
	 * @param today 
	 * @throws UserInexistException 
	 * @time:2016年12月1日 下午2:09:29
	 */
	public double getDiscountOneday(PreOrderVO preOrder, LocalDate today) throws UserInexistException{
		List<CalculateDiscount> calculateFixedPromotions = initCalculateFixedPromotions(preOrder,today);
		double discount = 1;
		for(int i = 0;i<calculateFixedPromotions.size();i++){
			discount = discount * calculateFixedPromotions.get(i).getDiscount();
		}
		return discount;
	}

	private List<CalculateDiscount> initCalculateFixedPromotions(PreOrderVO preOrder, LocalDate today){
		List<CalculateDiscount> calculateFixedPromotions = new ArrayList<CalculateDiscount>();
		initHotelFixedPromotions(preOrder.hotelID);
		HotelFixedDiscountFactory factory = new HotelFixedDiscountFactory(preOrder,today);
		for(int i = 0;i<hotelFixedPromotions.size();i++){
			HotelFixedPromotionPO po = hotelFixedPromotions.get(i);
			calculateFixedPromotions.add(factory.createCalculateDiscount(po.getPromotionType(),po.getDiscount()));
		}
		return calculateFixedPromotions;
	}

	/**
	 * @Description:根据酒店id从数据层获取该酒店固定策略
	 * @param hotelID
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:46:53
	 */
	private void initHotelFixedPromotions(String hotelID) {
		try {
			hotelFixedPromotions = promotionDataService.getHotelFixedPromotion(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:将poList转化为vo的iterator
	 * @param POList
	 * @return
	 * Iterator<HotelFixedPromotionVO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:47:24
	 */
	private Iterator<HotelFixedPromotionVO> convertPOListToVOIterator(List<HotelFixedPromotionPO> POList){
		List<HotelFixedPromotionVO> hotelFixedPromotionVOList = new ArrayList<HotelFixedPromotionVO>();
		for(HotelFixedPromotionPO hotelFixedPromotion: POList){
			hotelFixedPromotionVOList.add(new HotelFixedPromotionVO(hotelFixedPromotion));
		}
		return hotelFixedPromotionVOList.iterator();
	}

}
