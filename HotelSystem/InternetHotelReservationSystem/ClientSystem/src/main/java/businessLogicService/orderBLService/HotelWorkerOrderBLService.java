package businessLogicService.orderBLService;

import java.util.Iterator;

import exception.verificationException.CheckInException;
import exception.verificationException.CheckOutException;
import utilities.enums.ResultMessage;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.OrderGeneralVO;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/7
 *
 */
public interface HotelWorkerOrderBLService {

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 * @throws CheckInException 
	 */
	ResultMessage updateCheckIn(CheckInVO checkInVO) throws CheckInException;

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkOutVO 酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 * @throws CheckOutException 
	 */
	ResultMessage updateCheckOut(CheckOutVO checkOutVO) throws CheckOutException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/15
	 * @param hotelID 酒店编号
	 * @param hasCheckOut 状态：已退房／未退房
	 * @return 客户<已退房／未退房>订单
	 */
	Iterator<OrderGeneralVO> getAllHotelCheckOutOrderGeneral(String hotelID, boolean hasCheckOut);
}
