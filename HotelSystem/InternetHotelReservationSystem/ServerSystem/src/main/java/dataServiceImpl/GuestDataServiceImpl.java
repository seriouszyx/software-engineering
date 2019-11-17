package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dataHelper.GuestDataHelper;
import dataHelperImpl.GuestDataHelperImpl;
import dataService.guestDataService.GuestDataService;
import exception.operationFailedException.AddFaidException;
import exception.verificationException.ParameterInvalidException;
import po.GuestPO;
import po.MemberPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/12/1
 *
 */
public class GuestDataServiceImpl extends UnicastRemoteObject implements GuestDataService{

	private static final long serialVersionUID = 7861888931492257229L;
	
	private GuestDataHelper guestHelper;
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/1 构造函数，从工厂中获取guestDataHelper,creditDataHlper对象
	 */
	public GuestDataServiceImpl() throws RemoteException {
		guestHelper = new GuestDataHelperImpl();
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/1
	 * @param guestID 客户ID
	 * @return GuestPO guestInfo载体
	 */
	public GuestPO getSingleGuest(String guestID) throws RemoteException {
		
		GuestPO guestPO = guestHelper.getSingle(guestID);
		
		return guestPO;//从数据库中得到一个按ID索引的PO，若不存在则为空
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/1
	 * @return List<GuestPO> 所有guestInfo载体
	 */
	public List<GuestPO> getAllGuest() throws RemoteException {
		List<GuestPO> list = this.guestHelper.getAll();
		//从数据库中得到所有guestPO，若不存在则为空
		if(list.isEmpty()){return null;}
		
		return list;
		}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/2
	 * @param newGuestPO 需要添加的guestInfo载体
	 * @return List<CreditPO> 指定客户ID的所有creditInfo载体
	 * @throws ParameterInvalidException 
	 * @throws AddFaidException 
	 */
	public GuestPO add(GuestPO newGuestPO) throws RemoteException {
		
		return this.guestHelper.add(newGuestPO);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/1
	 * @param memberPO 需要修改的memberInfo载体
	 * @return ResultMessage 是否成功修改会员信息
	 */
	public ResultMessage modifyMember(MemberPO memberPO) throws RemoteException {
		
		GuestPO guestPO = this.guestHelper.getSingle(memberPO.getGuestID());
		
		guestPO.setBirthday(memberPO.getBirthday());
		guestPO.setEnterprise(memberPO.getEnterprise());
		
		return this.guestHelper.modify(guestPO);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/2
	 * @param guestPO 需要修改的guestInfo载体
	 * @return ResultMessage 是否成功修改客户信息
	 */
	public ResultMessage modify(GuestPO guestPO) throws RemoteException {
		
		return this.guestHelper.modify(guestPO);
	}
}
