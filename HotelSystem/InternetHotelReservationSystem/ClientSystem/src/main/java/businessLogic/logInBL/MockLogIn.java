package businessLogic.logInBL;

import businessLogic.userBL.MockUser;
import businessLogic.userBL.User;
import exception.verificationException.ParameterInvalidException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import utilities.enums.UserType;
import vo.GuestVO;
import vo.UserVO;

/**
 * 
 * @author 61990
 *
 */
public class MockLogIn extends LogIn {

	private User user;

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	public MockLogIn() {
		user = new MockUser();
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param guest
	 *            从登录界面层传下来的ID
	 * @param password
	 *            从登录界面层传下来的密码
	 * @return 客户是否成功登录
	 * @throws UserInexistException 
	 */
	public ResultMessage guestLogIn(final String guest, final String password,UserType userType) throws UserInexistException {
		return ResultMessage.SUCCESS;
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param hotelWorker
	 *            从登录界面层传下来的ID
	 * @param password
	 *            从登录界面层传下来的密码
	 * @return 酒店是否成功登录
	 * @throws UserInexistException 
	 */
	public ResultMessage hotelWorkerLogIn(final String hotelWorker, final String password,UserType userType) throws UserInexistException {
		return ResultMessage.FAIL;
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param webMarketer
	 *            从登录界面层传下来的ID
	 * @param password
	 *            从登录界面层传下来的密码
	 * @return 营销人员是否成功登录
	 * @throws UserInexistException 
	 */
	public ResultMessage webMarketerLogIn(final String webMarketer, final String password,UserType userType) throws UserInexistException {
		return ResultMessage.FAIL;
	}
	
	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param webManager
	 *            从登录界面层传下来的ID
	 * @param password
	 *            从登录界面层传下来的密码
	 * @return 管理人员是否成功登录
	 * @throws UserInexistException 
	 */
	public ResultMessage webManagerLogIn(final String webManager, final String password,UserType userType) throws UserInexistException {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 61990
	 * @lastChangedBy 61990
	 * @updateTime 2016/11/27
	 * @param guestVO
	 *           从注册界面层传下来的guestVO
	 * @return 客户是否成功注册
	 * @throws UserInexistException 
	 * @throws ParameterInvalidException 
	 */
	public GuestVO guestSignUp(UserVO guestVO) throws UserInexistException {
		return (GuestVO)user.add(guestVO,UserType.GUEST);
	}

}
