package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dataHelper.MarketDataHelper;
import dataHelperImpl.MarketDataHelperImpl;
import dataService.marketDataService.MarketDataService;
import po.MarketPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉 lastChangedBy 董金玉 updateTime 2016/12/1
 *
 */
public class MarketDataServiceImpl extends UnicastRemoteObject implements MarketDataService {

	private static final long serialVersionUID = 3142599779464574646L;
	
	private MarketDataHelper marketHelper;

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1 构造函数，从工厂中获取marketDataHlper对象
	 */
	public MarketDataServiceImpl() throws RemoteException {
		marketHelper = new MarketDataHelperImpl();
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @return List<MarketPO> 所有MarketInfo载体
	 */
	public List<MarketPO> getMemberFormulation() throws RemoteException {
		List<MarketPO> list = this.marketHelper.getAll();
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param List<MarketPO> 所有MarketInfo载体
	 * @return ResultMessage 是否成功修改marketInfo
	 */
	public ResultMessage setMemberFormulation(List<MarketPO> marketPOList) throws RemoteException {

		if (marketPOList.isEmpty()) {
			return ResultMessage.FAIL; // 若为空则返回失败
		}

		return this.marketHelper.modifyAll(marketPOList);
	}

}
