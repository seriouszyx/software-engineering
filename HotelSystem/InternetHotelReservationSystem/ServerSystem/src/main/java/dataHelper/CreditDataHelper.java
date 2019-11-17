package dataHelper;

import java.util.List;

import po.CreditPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * lastChangedBy charles
 * updateTime 2017/1/1
 *
 */
public interface CreditDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param guestID 客户ID
	 * @return List<CreditPO> 获取所有信用信息载体
	 */
	public List<CreditPO> getAllCreditDetail(String guestID);

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param creditPO 信用信息载体
	 * @return ResultMessage 是否成功添加信用
	 */
	public ResultMessage addCredit(CreditPO creditPO);

//	public List<CreditPO> getCreditOfOneOrder(String guestID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
	
}
