package dataHelperImpl.stub;

import java.util.ArrayList;
import java.util.List;

import dataHelper.MarketDataHelper;
import po.MarketPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 
 * @lastChangedBy Harvey
 * @updateTime 2016/12/5
 *
 */
public class MarketDataHelperImpl_Stub implements MarketDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy Harvey Gong
	 * @updateTime 2016/12/5
	 * @return List<MarketPO> 所有marketInfo载体
	 */
	public List<MarketPO> getAll() {
		List<MarketPO> a= new ArrayList<MarketPO>();
		a.add(new MarketPO("Lv1",100,0.9));
		a.add(new MarketPO("Lv2",200,0.8));
		a.add(new MarketPO("Lv3",300,0.7));
		return a;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param list 所有需要修改的marketInfo载体
	 * @return ResultMessage 是否成功修改所有marketInfo
	 */
	public ResultMessage modifyAll(final List<MarketPO> list) {
		
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param
	 * @return
	 */
	public void close() {
	}
}
