package dataService.guestDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.GuestPO;
import po.MemberPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author cuihua
 * @lastChangedBy charles
 * @updateTime 2017/1/1
 * 
 * 对数据库中客户相关的操作
 */
public interface GuestDataService extends Remote {

	/**
	 * @Description:获取单条客户信息
	 * @param guestID 用户id
	 * @return 用户信息持久化对象
	 * @throws RemoteException
	 * GuestPO
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午2:30:29
	 */
	public GuestPO getSingleGuest(String guestID) throws RemoteException;

	/**
	 * @Description:获取所有用户信息
	 * @return 用户信息持久化对象集合
	 * @throws RemoteException
	 * List<GuestPO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午2:30:31
	 */
	public List<GuestPO> getAllGuest() throws RemoteException;

	/**
	 * @Description:添加用户
	 * @param newGuestPO 用户信息持久化对象
	 * @return 用户信息持久化对象
	 * @throws RemoteException
	 * GuestPO
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午2:30:33
	 */
	public GuestPO add(GuestPO newGuestPO) throws RemoteException;

	/**
	 * @Description:更改会员信息
	 * @param memberPO 会员信息持久化对象
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午2:30:35
	 */
	public ResultMessage modifyMember(MemberPO memberPO) throws RemoteException;

	/**
	 * @Description:修改用户信息
	 * @param guestPO 用户信息持久化对象
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午2:30:37
	 */
	public ResultMessage modify(GuestPO guestPO) throws RemoteException;
	
}
