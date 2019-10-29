package businessLogic.service;

import java.util.List;

import common.ResultMessage;
import vo.ActivityPromotionVo;
import vo.BusinessProVo;
import vo.LevelVo;

/**
 * @version 2017-01-01
 * @author 金灵益
 * @description 网站营销人员进行网站活动促销策略制定，等级促销策略制定，特定商圈促销策略制定
 *              
 */
public interface MarketPromotionBlService {
	
	/**
	 * 增加一条活动策略
	 * @param vo
	 * @return ResultMessage.SUCCEED 添加成功； ResultMessage.FAIL 添加失败,活动已存在
	 *         ResultMessage.INVALID 折扣，日期非法；
	 */
	public ResultMessage addActivityStrategy(ActivityPromotionVo vo);
	
	/**
	 * 更新一条活动策略
	 * @param vo
	 *  @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败,活动不存在
	 *         ResultMessage.INVALID 折扣，日期非法；
	 */
	public ResultMessage upActivityStrategy(ActivityPromotionVo vo);
	
	/**
	 * 删除一条活动策略
	 * @param vo
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 */
	public ResultMessage delActivityStrategy(ActivityPromotionVo vo);
	
	/**
	 * @return 当前的有效活动策略
	 */
	public List<ActivityPromotionVo> getCurrentActStrategy();
	
	/**
	 * @param hotelId
	 * @return 当前促销策略折扣列表
	 */
	public List<Double> getCurrentActDiscount();
	
	/**
	 * 增加一个特定商圈促销策略
	 * @param vo
	 * @return ResultMessage.SUCCEED 添加成功； ResultMessage.FAIL 添加失败
	 *         ResultMessage.INVALID 折扣非法；
	 */
	public ResultMessage addBusinessStrategy(BusinessProVo vo);
	
	/**
	 * 更新特定商圈促销策略
	 * @param vo
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 *         ResultMessage.INVALID 折扣非法；
	 */
	public ResultMessage updateBusinessStrategy(BusinessProVo vo);
	
	/**
	 * 删除一条商圈策略
	 * @param vo
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 *         ResultMessage.INVALID 不存在该商圈策略
	 */
	public ResultMessage deleteBusinessStrategy(BusinessProVo vo);
	
	/**
	 * @param businessName
	 * @return 有促销策略的特定商圈促销列表
	 */
	public BusinessProVo getBusinessStrategy(String businessName);	
	
	/**
	 * @param level
	 * @return 客户等级对应的促销策略折扣
	 */
	public double getLevelStrategy(int level);
	
	/**
	 * 更新等级促销策略
	 * 本方法会检查输入的有效性：等级、信用值必须递增，List存的等级必须递增
	 * 同时修改所有客户等级
	 * @param list
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 *         ResultMessage.INVALID 折扣、信用值非法；
	 */
	public ResultMessage updateLevelStrategy(List<LevelVo> list);
	
	/**
	 * @param businessName
	 * @return 得到商圈折扣
	 */
	public double getBusinessDiscount(String businessName);
}
