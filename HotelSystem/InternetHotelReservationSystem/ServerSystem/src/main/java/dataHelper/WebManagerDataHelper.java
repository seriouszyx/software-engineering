package dataHelper;

import java.util.List;

import po.WebManagerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/29
 *
 */
public interface WebManagerDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webManagerPO webManagerInfo载体
	 * @return ResultMessage webManagerPO是否成功添加到数据库中
	 */
	WebManagerPO add(WebManagerPO webManagerPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webManagerPO webManagerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定webManagerInfo
	 */
	ResultMessage modify(WebManagerPO webManagerPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webManagerID  网站管理人员ID
	 * @return webManagerPO 数据库中的指定webManagerInfo载体
	 */
	WebManagerPO getSingle(String webManagerID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return List<webManagerPO> 获取所有webManagerInfo载体
	 */
	List<WebManagerPO> getAll();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
}
