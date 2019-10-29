package businessLogic.service;

import java.util.List;

import common.ResultMessage;
import vo.CreditRecordVo;
import vo.OrderVo;
/**
 * @author lienming
 * @version 2016-12-31
 * @description 
 * CreditBlService接口的职责是处理相应的presentation层和businesslogic层中其他的逻辑模块发来的请求,
 * 由类CreditBlServiceImpl来实现这个接口中的方法。
 */
public interface CreditBlService {
	
	/**
	 * 由于订单状态改变导致的信用值变化，调用该方法修改信用值
	 * @param id 用户ID
	 * @param amount 数额变化 可为非正数
	 * @param OrderVo 对应的订单Vo
	 * @return ResultMessage
	 */
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo);
	
	/**
	 * 根据ID返回该用户的信用记录
	 * @param id
	 * @return List<CreditRecordVo> 
	 */
	public List<CreditRecordVo> getCreditRecordList(String id);
	
}
