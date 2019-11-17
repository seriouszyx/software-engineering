package dataHelper;

import java.util.List;

import po.WebMarketerPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/29
 *
 */
public interface WebMarketerDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage webMarketerPO是否成功添加到数据库中
	 */
	WebMarketerPO add(WebMarketerPO webMarketerPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webMarketerPO webMarketerInfo载体
	 * @return ResultMessage 是否成功修改数据库中的指定webMarketerInfo
	 */
	ResultMessage modify(WebMarketerPO webMarketerPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webMarketerID 网站营销人员ID
	 * @return ResultMessage 是否成功删除数据库中的指定webMarketerInfo
	 */
	ResultMessage delete(String webMarketerID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param webMarketerID  网站营销人员ID
	 * @return WebMarketerPO 数据库中的指定webMarketerInfo载体
	 */
	WebMarketerPO getSingle(String webMarketerID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return List<WebMarketerPO> 获取所有WebMarketerInfo载体
	 */
	List<WebMarketerPO> getAll();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
}
