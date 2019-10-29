package dataService.dataHelper.service;

import java.util.List;

import common.ResultMessage;
import po.CreditRecordPo;

/**
 * @ author lienming
 * @ version 2016-12-31
 * @ description CreditDataHelper接口的职责是负责处理与Credit数据相关的读写请求
 *  由类CreditDataTxtHelper来实现这个接口中的方法。
 */
public interface CreditDataHelper {
	/**
	 * 由于订单状态改变导致的信用值变化，调用该方法修改信用值
	 * @param id 用户ID
	 * @param amount 数额变化 可为非正数
	 * @param OrderVo 对应的订单Vo
	 * @return ResultMessage
	 */
	public List<CreditRecordPo> getCreditRecordData(String id);
	
	/**
	 * 由于新建账号，需要在数据文件中添加对应的信用记录文本
	 * @param id
	 * @return ResultMessage
	 */
	public ResultMessage updateCreditRecordData(List<CreditRecordPo> list,String id);
	
	/**
	 * 根据ID返回该用户的信用记录
	 * @param id
	 * @return List<CreditRecordVo> 
	 */
	public ResultMessage newCredit(String id);
}
