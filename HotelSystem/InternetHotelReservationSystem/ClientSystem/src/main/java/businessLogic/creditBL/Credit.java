package businessLogic.creditBL;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.userBL.UserController;
import businessLogic.userBL.userService.Guest;
import businessLogic.userBL.userService.service.GuestCreditService;
import businessLogicService.userBLService.UserBLService;
import dataService.creditDataService.CreditDataService;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.UserInexistException;
import po.CreditPO;
import rmi.ClientRemoteHelper;
import utilities.enums.CreditRecord;
import utilities.enums.ResultMessage;
import vo.CreditVO;
import vo.GuestVO;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2017/1/1
 *
 */
public class Credit{

	
	private CreditDataService creditDataService;
	
	private GuestCreditService guest;

	private UserBLService userController;

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27 构造函数，初始化成员变量
	 */
	public Credit() {
		guest = new Guest();
		userController = UserController.getInstance();
		creditDataService = ClientRemoteHelper.getInstance().getCreditDataService();
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy Harvey Gong
	 * @updateTime 2016/12/5
	 * @param guestID 从登录界面层传下来的ID
	 * @return 客户个人所有信用记录
	 */
	public Iterator<CreditVO> getAllCreditDetail(String guestID) {
		try {
			return convertPOListToItr(creditDataService.getAllCreditDetail(guestID));
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	/**
//	 * @Description 查看关于某一订单的所有信用变化
//	 * @param guestID
//	 * @param orderID
//	 * @return Iterator<CreditVO>
//	 * @author: Harvey Gong
//	 * @lastChangedBy: Harvey Gong
//	 * @time:2016年12月5日 下午1:37:49
//	 */
//	public Iterator<CreditVO> getCreditOfOneOrder(String orderID){
//		try {
//			return convertPOListToItr(creditDataService.getCreditOfOneOrder(orderID));
//		} catch (RemoteException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	/**
	 * @author Harvey
	 * @lastChangedBy charles
	 * @updateTime 2016/12/9
	 * @param creditVO 一条信用记录
	 * @return 是否成功添加此信用记录并修改客户信用值
	 * @throws UserInexistException 
	 */
	public ResultMessage addCreditRecord(CreditVO creditVO) throws UserInexistException{
		guest = new Guest();
		try {
			//调用guest的方法，改变该客户的总信用值
			guest.modifyCredit(creditVO.guestID, creditVO.afterCredit);
			return creditDataService.addCredit(new CreditPO(creditVO));
		} catch (RemoteException e) {
			return ResultMessage.FAIL;
		}
	}
	
	public double charge(String guestID, double creditNum) throws UserInexistException, UpdateFaiedException {
		GuestVO guestVO = (GuestVO)userController.getSingle(guestID);
		double preCredit = guestVO.credit;
		LocalDateTime time = LocalDateTime.now();
		double afterCredit = preCredit +  creditNum * 100;
		CreditVO creditVO = new CreditVO(guestID, time, "", preCredit, afterCredit, CreditRecord.CHARGE);
		
		ResultMessage msg = this.addCreditRecord(creditVO);
		
		if(msg==ResultMessage.FAIL){
			throw new UpdateFaiedException();
		}
		
		return afterCredit;
	}
	
	private Iterator<CreditVO> convertPOListToItr(List<CreditPO> list){
		List<CreditVO> VOList = new ArrayList<CreditVO>();
		for(CreditPO creditPO : list){
			CreditVO creditVO = new CreditVO(creditPO);
			VOList.add(creditVO);
		}
		return VOList.iterator();
	}
}
