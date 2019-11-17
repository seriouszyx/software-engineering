package dataHelper;

import java.util.List;

import po.GuestPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/29
 *
 */
public interface GuestDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param guestPO guestInfo载体
	 * @return ResultMessage guestPO是否成功添加到数据库中
	 */
	GuestPO add(GuestPO guestPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param guestPO guestInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定guestInfo
	 */
	ResultMessage modify(GuestPO guestPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param guestID  客户ID
	 * @return GuestPO 数据库中的指定guestInfo载体
	 */
	GuestPO getSingle(String guestID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return List<GuestPO> 获取所有guestInfo载体
	 */
	List<GuestPO> getAll();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
}
