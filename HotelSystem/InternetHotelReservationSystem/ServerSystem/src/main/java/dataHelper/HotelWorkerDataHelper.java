package dataHelper;

import java.util.List;

import po.HotelWorkerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/29
 *
 */
public interface HotelWorkerDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelWorkerPO hotelWorkerInfo载体
	 * @return ResultMessage hotelWorkerPO是否成功添加到数据库中
	 */
	HotelWorkerPO add(HotelWorkerPO hotelWorkerPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelWorkerPO hotelWorkerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定hotelWorkerInfo
	 */
	ResultMessage modify(HotelWorkerPO hotelWorkerPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelWorkerID 酒店工作人员ID
	 * @return ResultMessage 是否成功删除数据库中的指定hotelWorkerInfo
	 */
	ResultMessage delete(String hotelWorkerID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelWorkerID  酒店工作人员ID
	 * @return HotelWorkerPO 数据库中的指定hotelWorkerInfo载体
	 */
	HotelWorkerPO getSingle(String hotelWorkerID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return List<HotelWorkerPO> 获取所有hotelWorkerInfo载体
	 */
	List<HotelWorkerPO> getAll();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
}
