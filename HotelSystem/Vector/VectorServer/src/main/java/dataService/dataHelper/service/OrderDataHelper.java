package dataService.dataHelper.service;

import java.util.Map;

import po.OrderPo;

/**
 * @ author Aobang
 * @ version 2016/12/8
 * @ description
 */
public interface OrderDataHelper {
	
	/**
	 * @return	从数据文件中读取订单数据
	 */
	public Map<String, OrderPo> getOrderData();
	
	/**
	 * 向数据文件中写入订单数据
	 * @param list	
	 */
	public void updateOrderData(Map<String, OrderPo> map);

}

