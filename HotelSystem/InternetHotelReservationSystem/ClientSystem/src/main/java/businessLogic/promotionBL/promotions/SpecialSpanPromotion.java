package businessLogic.promotionBL.promotions;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.promotionBL.discountCalculation.CalculateDiscount;
import businessLogic.promotionBL.discountCalculation.discountOfPromotions.SpecialSpanDiscount;
import dataService.promotionDataService.PromotionDataService;
import exception.verificationException.UserInexistException;
import po.SpecialSpanPromotionPO;
import rmi.ClientRemoteHelper;
import utilities.enums.ResultMessage;
import vo.SpecialSpanPromotionVO;

/**
 * @Description:对酒店和网站特定期间的促销策略操作的具体实现
 * @author:Harvey Gong
 * @time:2016年12月1日 下午3:22:57
 */
public class SpecialSpanPromotion {

	PromotionDataService promotionDataService;
	List<SpecialSpanPromotionPO> specialSpanPromotions;
	//TODO 该怎么存这个web的特定期间折扣,是和那些存在一个表里面还是怎么，直接把id写出来肯定还是不行
	String webID = "99999999";

	public SpecialSpanPromotion() {
		promotionDataService = ClientRemoteHelper.getInstance().getPromotionDataService();
	}

	/**
	 * @Description:根据酒店查找到所有特定期间的促销策略
	 * @param userID
	 * @return
	 * Iterator<SpecialSpanPromotionVO>
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午3:25:34
	 */
	public Iterator<SpecialSpanPromotionVO> getHotelSpecialSpanPromotions(String hotelID){
		initSpecialSpanPromotions(hotelID);
		return convertPOListToVOListIterator(specialSpanPromotions);
	}

	/**
	 * @Description:查找网站所有特定期间的促销策略
	 * @return
	 * Iterator<SpecialSpanPromotionVO>
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午3:40:53
	 */
	public Iterator<SpecialSpanPromotionVO> getWebSpecialSpanPromotions(){
		initSpecialSpanPromotions(null);
		return convertPOListToVOListIterator(specialSpanPromotions);
	}

	/**
	 * @Description:添加特定期间的促销策略
	 * @param specialSpanPromotionVO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @time:2016年12月2日 下午6:56:05
	 */
	public ResultMessage addSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO){
		if(!isExist(specialSpanPromotionVO)){
			try {
				return promotionDataService.addSpecialSpanPromotion(new SpecialSpanPromotionPO(specialSpanPromotionVO));
			} catch (RemoteException e) {
				return ResultMessage.FAIL;
			}
		}
		else
		{
			return ResultMessage.FAIL;
		}

	}

	/**
	 * @Description:检查是否重复添加
	 * @return
	 * boolean
	 * @author: Harvey Gong
	 * @param vo 
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 下午10:36:48
	 */
	private boolean isExist(SpecialSpanPromotionVO vo) {
		initSpecialSpanPromotions(vo.userID);
		for(int i = 0;i<specialSpanPromotions.size();i++){
			if(specialSpanPromotions.get(i).getPromotionName().equals(vo.promotionName)){
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description:添加网站的特定期间策略
	 * @param specialSpanPromotionVO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:36:20
	 */
	public ResultMessage addWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		specialSpanPromotionVO.userID = webID;
		return addSpecialSpanPromotion(specialSpanPromotionVO);
	}

	/**
	 * @Description:更新特定期间的促销策略
	 * @param list
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午3:26:32
	 */
	public ResultMessage updateSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO){
		try {
			return promotionDataService.updateSpecialSpanPromotion(new SpecialSpanPromotionPO(specialSpanPromotionVO));
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:更新网站的特定期间折扣
	 * @param specialSpanPromotionVO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:36:55
	 */
	public ResultMessage updateWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO) {
		specialSpanPromotionVO.userID = webID;
		return updateSpecialSpanPromotion(specialSpanPromotionVO);
	}



	/**
	 * @Description:根据userID和策略名称，将数据库里对应的一条promotion删除
	 * @param specialSpanPromotionVO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @time:2016年12月2日 下午7:23:31
	 */
	public ResultMessage deleteSpecialSpanPromotion(String userID,String promotionName){
		try {
			return promotionDataService.deleteSpecialSpanPromotion(userID,promotionName);
		} catch (RemoteException e) {
			return ResultMessage.FAIL;
		}	
	}

	/**
	 * @Description:删除网站的特定期间折扣
	 * @param promotionName
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月12日 上午1:38:18
	 */
	public ResultMessage deleteWebSpecialSpanPromotion(String promotionName) {
		return deleteSpecialSpanPromotion(webID, promotionName);
	}


	/**
	 * @Description:根据传入的订单信息和需要计算策略的日期，
	 * 计算酒店和网站特定期间的促销策略的折扣
	 * @param preOrder
	 * @param today
	 * @return
	 * double
	 * @author: Harvey Gong
	 * @throws UserInexistException 
	 * @time:2016年12月1日 下午3:27:17
	 */
	public double getDiscountOneday(String hotelID, LocalDate today) throws UserInexistException{
		double discount = 1;
		initSpecialSpanPromotions(hotelID);
		discount = discount * getSpecialSpanDiscount(today);
		initSpecialSpanPromotions(null);
		discount = discount * getSpecialSpanDiscount(today); 
		return discount;
	}

	private double getSpecialSpanDiscount(LocalDate today) throws UserInexistException{
		double discount = 1;
		List<CalculateDiscount> specialSpanDiscount = initCalculateSpecialSpanDiscount(today);
		for(int i = 0;i<specialSpanDiscount.size();i++){
			discount = discount * specialSpanDiscount.get(i).getDiscount();
		}
		return discount;
	}

	private List<CalculateDiscount> initCalculateSpecialSpanDiscount(LocalDate today){
		double discount;
		LocalDate startDate;
		LocalDate endDate;
		List<CalculateDiscount> specialSpanDiscount = new ArrayList<CalculateDiscount>();
		for(int i = 0;i<specialSpanPromotions.size();i++){
			SpecialSpanPromotionPO tempPO = specialSpanPromotions.get(i);
			discount = tempPO.getDiscount();
			startDate = tempPO.getStartDate();
			endDate = tempPO.getEndDate();
			specialSpanDiscount.add(new SpecialSpanDiscount(discount,startDate,endDate,today));
		}
		return specialSpanDiscount;
	}

	private void initSpecialSpanPromotions(String hotelID){
		try{
			if(hotelID == null){
				specialSpanPromotions = promotionDataService.getWebSpecialSpanPromotion();
			} 
			else
			{
				specialSpanPromotions = promotionDataService.getHotelSpecialSpanPromotion(hotelID);
			}
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private Iterator<SpecialSpanPromotionVO> convertPOListToVOListIterator(List<SpecialSpanPromotionPO> POList){
		List<SpecialSpanPromotionVO> specialSpanPromotions = new ArrayList<SpecialSpanPromotionVO>();
		for(SpecialSpanPromotionPO specialSpanPromotion: POList){
			specialSpanPromotions.add(new SpecialSpanPromotionVO(specialSpanPromotion));
		}
		return specialSpanPromotions.iterator();
	}

}
