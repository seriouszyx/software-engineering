package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.ResultMessage;
import po.ActivityPromotionPo;
import po.BusinessProPo;
import po.LevelPo;
/**
 * @version 2017-01-01
 * @author 金灵益
 */
public interface MarketPromotionDao extends Remote{
	/**
	 * 增加活动策略
	 * @param po
	 * @return ResultMessage.SUCCEED 添加成功； ResultMessage.FAIL 添加失败,活动已存在
	 */
	public ResultMessage addActivity(ActivityPromotionPo po) throws RemoteException;
	
	/**
	 * 更新网站的活动策略列表
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败,活动不存在
	 */
	public ResultMessage updateActivity(ActivityPromotionPo po) throws RemoteException;
	
	/**
	 * 删除一条活动策略
	 * @param po
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 */
	public ResultMessage deleteActivity(ActivityPromotionPo po) throws RemoteException;
	
	/**
	 * @return 得到网站的活动策略列表
	 */
	public List<String> getActivity() throws RemoteException;
	
	/**
	 * 网站营销人员更新等级促销策略,同时修改所有客户的等级
	 * @param list
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateLevelRule(List<LevelPo> list) throws RemoteException;
	
	/**
	 * @return 得到会员等级促销策略
	 */
	public List<LevelPo> getLevelList() throws RemoteException;
	
	/**
	 * 增加或更新特定商圈促销策略
	 * @param po
	 * @return ResultMessage.SUCCEED 更新成功； ResultMessage.FAIL 更新失败
	 */
	public ResultMessage updateBusiness(BusinessProPo po) throws RemoteException;
	
	/**
	 * 删除一条商圈策略
	 * @param po
	 * @return ResultMessage.SUCCEED 删除成功； ResultMessage.FAIL 删除失败
	 */
	public ResultMessage deleteBusiness(BusinessProPo po) throws RemoteException;
	
	/**
	 * @return 得到有促销策略的特定商圈促销列表
	 */
	public List<BusinessProPo> getBusinessList() throws RemoteException;
}
