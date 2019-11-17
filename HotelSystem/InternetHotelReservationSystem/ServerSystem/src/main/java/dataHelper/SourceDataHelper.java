package dataHelper;

import java.util.List;

/**
 * @Description:提供给dataService获取所有城市，所有星级，所有房间类型的方法
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月7日 下午5:02:01
 */
public interface SourceDataHelper {
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return list<String>  等级信息
	 */
	public List<String> getLevels();

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return list<String> 类型信息
	 */
	public List<String> getRoomTypes();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return int 最大房间数量
	 */
	public int getMaxGuestNumEachOrder();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return int 最大房间数量
	 */
	public int getMaxRoomNumEachOrder();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param guestID ID
	 * @return 
	 */
	public void guestLogInRecord(String guestID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public void guestLogOut(String guestID);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	boolean guestHasLogged(String guestID);
}
