package dataHelper;

import java.util.List;

import po.MarketPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author Byron Dong
 * lastChangedBy Byron Dong
 * updateTime 2016/11/29
 *
 */
public interface MarketDataHelper {

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/29
	 * @return List<MarketPO> 获取所有MarketInfo载体
	 */
	List<MarketPO> getAll();
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/29
	 * @param list  所有Market信息载体
	 * @return ResultMessage 是否成功修改marketInfo到数据库中
	 */
	ResultMessage modifyAll(List<MarketPO> list);
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
	
}
