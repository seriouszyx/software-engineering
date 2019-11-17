package dataService.creditDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.CreditPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author byron
 * @lastChangedBy charles
 * @updateTime 2017/1/1
 *
 * 对数据库中信用记录的操作
 */
public interface CreditDataService extends Remote{
	
	/**
	 * @Description:对信用值的操作，获取个人所有的信用值记录，添加一条信用值变化记录
	 * 可以单独提一个dataService，也可以不用
	 * @return 信用记录集合
	 * @throws RemoteException
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月5日 下午1:15:46
	 */
	public List<CreditPO> getAllCreditDetail(String guestID) throws RemoteException;

	/**
	 * @Description:添加一条信用记录
	 * @return 结果信息
	 * @throws RemoteException
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017/1/1
	 */
	public ResultMessage addCredit(CreditPO creditPO) throws RemoteException;
	
}
