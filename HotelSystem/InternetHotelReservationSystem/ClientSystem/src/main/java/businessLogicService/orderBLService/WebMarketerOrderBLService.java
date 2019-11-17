package businessLogicService.orderBLService;

import java.time.LocalDate;
import java.util.List;

import utilities.enums.ResultMessage;
import vo.OrderGeneralVO;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/7
 *
 */
public interface WebMarketerOrderBLService {

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @param percent 撤销后需要恢复的信用值比例
	 * @return 网站营销人员是否成功按比例撤销此异常订单
	 */
	ResultMessage undoAbnormalOrder(String orderID, double percent);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 */
	List<OrderGeneralVO> getAllAbnormalOrderGeneral(LocalDate date);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/29
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 */
	List<OrderGeneralVO> getAllUnexecutedOrderGeneral(LocalDate date);
}
