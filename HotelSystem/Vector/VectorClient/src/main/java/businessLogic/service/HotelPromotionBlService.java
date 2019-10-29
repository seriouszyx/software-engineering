package businessLogic.service;

import java.util.Date;
import java.util.List;

import common.ResultMessage;
import vo.ActivityPromotionVo;
import vo.BirthdayProVo;
import vo.CompanyProVo;
import vo.RoomPromotionVo;

/**
 * @version 2017-01-01
 * @author 金灵益
 * @description 酒店促销策略相关接口：增删改活动促销策略，修改合作企业促销策略、客户生日促销策略、
 *              房间预定促销策略，获得上述策略
 */
public interface HotelPromotionBlService {

	/**
	 * 增加一条活动策略
	 * @param hotelId
	 * @param vo
	 * @return ResultMessage.SUCCEED 添加成功； ResultMessage.FAIL 添加失败,活动已存在
	 *         ResultMessage.INVALID 折扣，日期非法；
	 */
	public ResultMessage addActivityStrategy(String hotelId, ActivityPromotionVo vo);
	
	/**
	 * 更新一条活动策略
	 * @param hotelId
	 * @param vo
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败,活动不存在
	 *         ResultMessage.INVALID 折扣，日期非法；
	 */
	public ResultMessage upActivityStrategy(String hotelId, ActivityPromotionVo vo);
	
	/**
	 * 删除一条活动策略
	 * @param hotelId
	 * @param vo
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 */
	public ResultMessage delActivityStrategy(String hotelId, ActivityPromotionVo vo);
	
	/**
	 * @param hotelId
	 * @return 得到当前的有效活动策略列表
	 */
	public List<ActivityPromotionVo> getCurrentActStrategy(String hotelId);
	
	/**
	 * @param hotelId
	 * @return 得到当前活动策略列表对应的折扣
	 */
	public List<Double> getCurrentActDiscount(String hotelId);
	
	/**
	 * 更新合作企业促销策略
	 * @param hotelId
	 * @param vo
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateCooperationStrategy(String hotelId, CompanyProVo vo);
	
	/**
	 * 得到酒店的企业合作策略
	 * @param hotelId
	 * @return
	 */
	public CompanyProVo getCooperationStrategy(String hotelId);
	
	/**
	 * @param hotelId
	 * @param memberId    企业账号
	 * @return 若该企业为酒店合作企业，则得到相应关于合作企业促销策略
	 */
	public double getCooperationStrategy(String hotelId, String memberId);

	/**
	 * 更新房间预订促销策略
	 * @param hotelId
	 * @param vo
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateOrderRoomStrategy(String hotelId, RoomPromotionVo vo);
	
	/**
	 * @param hotelId
	 * @param numOfRoom
	 * @return 得到房间预订折扣
	 */
	public double getOrderRoomDiscount(String hotelId, int numOfRoom);
	
	/**
	 * @param hotelId
	 * @param numOfRoom
	 * @return 当在该酒店一次订单订房间数量满足条件，得到该酒店房间预订促销策略
	 */
	public RoomPromotionVo getOrderRoomStrategy(String hotelId, int numOfRoom);
	
	/**
	 * @param hotelId
	 * @param birthDay
	 * @return 当前为客户生日时，得到生日优惠策略
	 */
	public double getBirthStrategy(String hotelId, Date birthDay);
	
	/**
	 * 得到酒店的客户生日促销策略
	 * @param hotelId
	 * @return
	 */
	public BirthdayProVo getBirthStrategy(String hotelId);
	
	/**
	 * 更新该酒店客户生日促销策略
	 * @param hotelId
	 * @param vo
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage upBirthStrategy(String hotelId, BirthdayProVo vo);
	
}
