package dataService.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import common.ResultMessage;
import dataService.dao.service.CreditDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.CreditDataHelper;
import dataService.dataHelper.service.DataFactory;
import po.CreditRecordPo;
import vo.CreditRecordVo;
import vo.OrderVo;

/**
 * 类CreditDaoImpl的职责是实现接口CreditDao的方法，通过与数据层的交互、处理数据完成请求
 */

public class CreditDaoImpl implements CreditDao {
	/*单件模式*/
	private static CreditDaoImpl creditDataServiceImpl;
	private CreditDataHelper creditDataHelper;
	private DataFactory dataFactory;
	
	public static CreditDaoImpl getInstance(){
		if(creditDataServiceImpl==null)
			creditDataServiceImpl = new CreditDaoImpl();
		
		return creditDataServiceImpl;
	}
	
	private CreditDaoImpl(){
		if(dataFactory==null){
			dataFactory = new DataFactoryImpl();
			creditDataHelper= dataFactory.getCreditDataHelper();
		}
			
	}
	
	/**
	 * 由于订单状态改变导致的信用值变化，调用该方法修改信用值
	 * @param id 用户ID
	 * @param amount 数额变化 可为非正数
	 * @param OrderVo 对应的订单Vo
	 * @return ResultMessage
	 */
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo){
		int ori_credit = MemberDaoImpl.getInstance().getCredit(id);
		CreditRecordPo po;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_str = sdf.format(new Date());
		if(vo==null) //充值信用，与订单无关!
			po = new CreditRecordPo(ori_credit+amount,
					"Charge credit",time_str,amount);
		else
			po = new CreditRecordPo(ori_credit+amount,
				vo.getOrderId()+" && "+vo.getCondition(),time_str,amount);
		MemberDaoImpl.getInstance().chargeCredit(id, amount);
		
		List<CreditRecordPo> list = creditDataHelper.getCreditRecordData(id);
		list.add(po);
		return creditDataHelper.updateCreditRecordData(list, id);
	}
	
	/**
	 * 由于新建账号，需要在数据文件中添加对应的信用记录文本
	 * @param id
	 * @return ResultMessage
	 */
	public ResultMessage newCredit(String id){
		return creditDataHelper.newCredit(id);
	}
	
	/**
	 * 根据ID返回该用户的信用记录
	 * @param id
	 * @return List<CreditRecordVo> 
	 */
	public List<CreditRecordVo> getCreditRecordList(String id){
		List<CreditRecordPo> list_po = creditDataHelper.getCreditRecordData(id);
		List<CreditRecordVo> list_vo = new ArrayList<CreditRecordVo>();
		Iterator<CreditRecordPo> iterator = list_po.iterator();
		while(iterator.hasNext()){
			list_vo.add( new CreditRecordVo(iterator.next()));
		}		
		return list_vo;
	}
}
