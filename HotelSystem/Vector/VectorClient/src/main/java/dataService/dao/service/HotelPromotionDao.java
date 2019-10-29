package dataService.dao.service;

import java.rmi.Remote;
import java.util.List;

import common.ResultMessage;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;
/**
 * @version 2017-01-01
 * @author 金灵益
 */
public interface HotelPromotionDao extends Remote{
	
	/**
	 * 增加活动策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 添加成功； ResultMessage.FAIL 添加失败,活动已存在
	 */
	public ResultMessage addActPromotion(String hotelId, ActivityPromotionPo po);
	
	/**
	 * 更新活动策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败,活动不存在
	 */
	public ResultMessage upActPromotion(String hotelId, ActivityPromotionPo po);
	
	/**
	 * 删除一个活动策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 */
	public ResultMessage delActPromotion(String hotelId, ActivityPromotionPo po);
	
	/**
	 * @param hotelId
	 * @return 所有活动促销列表
	 */
	public List<String> getActProList(String hotelId);

	/**
	 * 更新客户生日优惠策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage upBirthPromotion(String hotelId, BirthdayProPo po);
	
	/**
	 * @param hotelId
	 * @return 客户生日优惠策略
	 */
	public BirthdayProPo getBirthPromotion(String hotelId);
	
	/**
	 * 更新合作企业优惠策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateCooperPro(String hotelId, CompanyProPo po);
	
	/**
	 * @param hotelId
	 * @return 合作企业优惠策略
	 */
	public CompanyProPo getCooperPro(String hotelId);
	
	/**
	 * 更新预订房间数量优惠策略
	 * @param hotelId
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage upRoomPromotion(String hotelId, RoomPromotionPo po);
	
	/**
	 * @param hotelId
	 * @return 预定房间数量优惠策略
	 */
	public RoomPromotionPo getRoomPromotion(String hotelId);
}
