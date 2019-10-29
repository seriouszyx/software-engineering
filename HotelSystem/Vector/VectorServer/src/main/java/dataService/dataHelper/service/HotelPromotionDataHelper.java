package dataService.dataHelper.service;

import java.util.List;

import common.ResultMessage;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;

/**
 * @version 2017-01-01
 * @author 金灵益
 *
 */
public interface HotelPromotionDataHelper {

	/**
	 * 增加酒店的活动促销策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 添加成功； ResultMessage.FAIL 添加失败
	 */
	public ResultMessage addActivity(String hotelId, ActivityPromotionPo po);
	
	/**
	 * 更新酒店的活动策略列表
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateActivity(String hotelId, ActivityPromotionPo po);
	
	/**
	 * 删除一个活动策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 */
	public ResultMessage deleteActivity(String hotelId, ActivityPromotionPo po);
	
	/**
	 * @return 得到酒店的活动策略列表
	 */
	public List<String> getActivity(String hotelId);
	
	/**
	 * 更新酒店的客户生日优惠折扣
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateBirthPromotion(String hotelId, BirthdayProPo po);
	
	/**
	 * @return 得到生日优惠折扣
	 */
	public BirthdayProPo getBirthPromotion(String hotelId);
	
	/**
	 * 更新酒店合作企业优惠：折扣，减价，企业账号列表
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateCompanyPro(String hotelId, CompanyProPo po);
	
	/**
	 * @return 得到合作企业优惠策略
	 */
	public CompanyProPo getCompanyPro(String hotelId);
	
	/**
	 * 更新酒店房间数量预定优惠策略
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateRoomPromotion(String hotelId, RoomPromotionPo po);
	
	/**
	 * @return 得到房间预订促销策略
	 */
	public RoomPromotionPo getRoomPromotion(String hotelId);
}
