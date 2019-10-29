package businessLogic.impl;

import java.util.List;

import businessLogic.service.CreditBlService;
import common.ResultMessage;
import dataService.dao.service.CreditDao;
import rmi.RemoteHelper;
import vo.CreditRecordVo;
import vo.OrderVo;
/**
 * 类CreditBlServiceImpl的职责是实现接口CreditBlService的方法，通过远程调用服务端的方法来完成请求
 */
public class CreditBlServiceImpl implements CreditBlService {
	/* 单件模式  */
	private CreditDao creditDao;
	
	private static CreditBlServiceImpl creditBlServiceImpl;
	
	public static CreditBlServiceImpl getInstance(){
		if(creditBlServiceImpl==null)
			creditBlServiceImpl = new CreditBlServiceImpl();
		return creditBlServiceImpl;
	}
	
	private CreditBlServiceImpl(){
		creditDao = RemoteHelper.getInstance().getCreditDao();
	}
	
	/**
	 * 由于订单状态改变导致的信用值变化，调用该方法修改信用值
	 * @param id 用户ID
	 * @param amount 数额变化 可为非正数
	 * @param OrderVo 对应的订单Vo
	 * @return ResultMessage
	 */
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo){
		if(vo==null)
			return ResultMessage.FAIL;
		return creditDao.addCreditByOrder(id, amount, vo);
	}

	/**
	 * 根据ID返回该用户的信用记录
	 * @param id
	 * @return List<CreditRecordVo> 
	 */
	public List<CreditRecordVo> getCreditRecordList(String id){
		return creditDao.getCreditRecordList(id);
	}
}
