package dataHelper;

import java.util.List;

import po.RoomInfoPO;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/29
 *
 */
public interface RoomDataHelper {
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelID 酒店ID
	 * @return List<RoomInfoPO> 房间信息载体列表
	 */
	public List<RoomInfoPO> getRoomInfo(String hotelID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功更新房间信息
	 */
	public ResultMessage updateRoomInfo(RoomInfoPO roomInfoPO);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public ResultMessage addRoomInfo(RoomInfoPO roomInfoPO);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功删除房间信息
	 */
	public ResultMessage deleteRoomInfo(String hotelID,RoomType roomType);
	
}
