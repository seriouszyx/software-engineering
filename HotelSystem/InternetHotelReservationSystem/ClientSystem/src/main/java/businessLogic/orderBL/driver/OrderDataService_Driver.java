package businessLogic.orderBL.driver;

import dataService.orderDataService.OrderDataService;

/**
 * 
 * @author charles
 * lastChangedBy charles
 * updateTime 2016/11/27
 */
public class OrderDataService_Driver {

	public OrderDataService orderDataService;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/29
	 * @param orderDataService 测试用的orderDataService接口
	 */
	public OrderDataService_Driver(final OrderDataService orderDataService) {
		this.orderDataService = orderDataService;
	}
}
