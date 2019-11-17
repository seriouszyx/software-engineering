package dataService.marketDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.MarketPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author cuihua
 * @lastChangedBy charles
 * @updateTime 2017/1/1
 *
 */
public interface MarketDataService extends Remote{
	
	/**
	 * @Description： 获取会员等级制度
	 * @return 系统现存的所有会员制度信息
	 * @throws RemoteException
	 */
	public List<MarketPO> getMemberFormulation() throws RemoteException;
	
	/**
	 * @Description 设置会员等级制度
	 * @param marketPOList 修正的会员制度信息
	 * @return 是否修改成功
	 * @throws RemoteException
	 */
	public ResultMessage setMemberFormulation (List<MarketPO> marketPOList) throws RemoteException;
}
