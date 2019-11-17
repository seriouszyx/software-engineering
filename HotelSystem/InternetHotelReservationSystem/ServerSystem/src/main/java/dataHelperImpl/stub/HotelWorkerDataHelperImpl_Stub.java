package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.HotelWorkerDataHelper;
import po.HotelWorkerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 
 * @lastChangedBy Harvey Gong
 * @ updateTime 2016/12/5
 *
 */
public class HotelWorkerDataHelperImpl_Stub implements HotelWorkerDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param hotelWorkerPO hotelWorkerInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public HotelWorkerPO add(HotelWorkerPO hotelWorkerPO) {
		return new HotelWorkerPO("00001111", "123456","school");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param hotelWorkerPO hotelWorkerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定hotelWorkerInfo
	 */
	public ResultMessage modify(final HotelWorkerPO hotelWorkerPO) {
		return ResultMessage.SUCCESS;

	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param hotelWorkerID 酒店工作人员ID
	 * @return ResultMessage 是否成功删除指定酒店工作人员信息
	 */
	public ResultMessage delete(final String hotelWorkerID) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param hotelWorkerID 酒店工作人员ID
	 * @return HotelWorkerPO 数据库中的指定hotelWorkerInfo载体
	 */
	public HotelWorkerPO getSingle(final String hotelWorkerID) {
		return new HotelWorkerPO("00001111", "123456","school");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param
	 * @return List<HotelWorkerPO> 获取所有hotelWorkerInfo载体
	 */
	public List<HotelWorkerPO> getAll() {
		List<HotelWorkerPO>  list= new ArrayList<HotelWorkerPO>();
		list.add(new HotelWorkerPO("00001111", "123456","school"));
		list.add(new HotelWorkerPO("00001112", "123456","school1"));
		list.add(new HotelWorkerPO("00001113", "123456","school2"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param
	 * @return
	 */
	public void close() { // 当决定抛弃该对象的时候，调用该方法
	}
}
