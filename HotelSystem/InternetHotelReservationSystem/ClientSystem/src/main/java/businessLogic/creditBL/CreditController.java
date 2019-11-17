package businessLogic.creditBL;

import java.util.Iterator;

import businessLogicService.creditBLService.CreditBLService;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import vo.CreditVO;

/**
 * 
 * @author 61990
 * @lastChangedBy charles
 * @updateTime 2017/1/1
 */
public final class CreditController implements CreditBLService {

	
	private Credit credit;
	
	private static CreditController creditController = new CreditController();;

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27 
	 * 构造函数，初始化成员变量
	 */
	private CreditController() {
		//new the mock object
		credit = new Credit();
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @return creditController的实例，单例化
	 */
	public static CreditController getInstance() {
		return creditController;
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param guestID 从登录界面层传下来的ID
	 * @return 客户个人所有信用记录
	 */
	public Iterator<CreditVO> getAllCreditDetail(final String guestID) {
		return credit.getAllCreditDetail(guestID);
	}
	
//	/**
//	 * @author 61990
//	 * @lastChangedBy charles
//	 * @updateTime 2017/1/1
//	 * @param orderID 从登录界面层传下来的订单编号
//	 * @return 客户个人此订单的信用记录
//	 */
//	@Override
//	public Iterator<CreditVO> getCreditOfOneOrder(String orderID) {
//		return credit.getCreditOfOneOrder(orderID);
//	}

	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/9
	 * @param creditVO 一条信用记录
	 * @return 是否成功添加此信用记录
	 * @throws UserInexistException 
	 */
	@Override
	public ResultMessage addCreditRecord(CreditVO creditVO) throws UserInexistException {
		return credit.addCreditRecord(creditVO);
	}

	@Override
	public double charge(String guestID, double creditNum) throws UserInexistException, UpdateFaiedException {
		return credit.charge(guestID, creditNum);
	}

}
