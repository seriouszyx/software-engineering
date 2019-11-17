package businessLogicService.creditBLService;

import java.util.Iterator;

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
public interface CreditBLService {

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/23
	 * @param guestID 从登录界面层传下来的ID
	 * @param creditNum 从登录界面传下来的更改后的信用值额度
	 * @return 充值后的信用值
	 */
	public double charge(String guestID, double creditNum) throws UserInexistException, UpdateFaiedException;
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param guestID 从登录界面层传下来的ID
	 * @return 客户个人所有信用记录
	 */
	public Iterator<CreditVO> getAllCreditDetail(String guestID);
	
	/**
	 * @author 61990
	 * @lastChangedBy charles
	 * @updateTime 2016/12/9
	 * @param creditVO 一条信用记录
	 * @return 是否成功添加此信用记录并修改客户信用值
	 * @throws UserInexistException 
	 */
	public ResultMessage addCreditRecord(CreditVO creditVO) throws UserInexistException;
	
}
