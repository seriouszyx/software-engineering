package businessLogic.logInBL;

import java.rmi.RemoteException;

import businessLogic.userBL.User;
import dataService.sourceDataService.SourceDataService;
import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.inputException.SpecialCharacterException;
import exception.verificationException.ParameterInvalidException;
import exception.verificationException.UserInexistException;
import exception.verificationException.WrongPasswordException;
import rmi.ClientRemoteHelper;
import utilities.Detector;
import utilities.IDReserve;
import utilities.enums.UserType;
import vo.GuestVO;
import vo.UserVO;

/**
 * 
 * @author 61990
 * @lastChangedBy charles
 *
 */
public class LogIn{

	private User user;
	private LogInFactory factory;
	private SourceDataService sourceDataService;

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/7 构造函数，初始化成员变量
	 */
	public LogIn() {
		user = new User();
		factory = new LogInFactory();
		sourceDataService = ClientRemoteHelper.getInstance().getSourceDataService();
	}

	/**
	 * @Description:根据用户id获取用户的密码，并验证登录
	 * @param userID
	 * @param password
	 * @return ResultMessage
	 * @author: Harvey Gong
	 * @throws SpecialCharacterException
	 * @throws InvalidLengthInputException
	 * @throws UserInexistException
	 * @lastChangedBy: Byron Dong
	 * @time:2016年12月7日
	 */
	public UserType logIn(String userID, String password) throws WrongPasswordException, SpecialCharacterException,
			InvalidLengthInputException, UserInexistException {

		UserType userType = null;
		try {
			userType = this.factory.getUserType(userID);
		} catch (SpecialCharacterException e) {
			e.printStackTrace();
			throw new SpecialCharacterException();
		} catch (InvalidLengthInputException e) {
			e.printStackTrace();
			throw new InvalidLengthInputException();
		}

		if (!this.hasUserType(userType)) {
			return null;
		} //

		String tempPassword = user.getLogInInfo(userID, userType);

		if (!tempPassword.equals(password)) {
			throw new WrongPasswordException();
		}

		return userType;
	}

	/**
	 * @author 61990
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/10
	 * @param guestVO
	 *            从注册界面层传下来的guestVO
	 * @return 客户是否成功注册
	 * @throws InvalidInputException,PasswordInputException
	 * @throws UserInexistException 
	 * @throws ParameterInvalidException
	 */
	public GuestVO guestSignUp(UserVO guestVO)throws InvalidInputException, PasswordInputException, InvalidLengthInputException, UserInexistException {
//		this.infoDetector(guestVO);
		return (GuestVO) user.add(guestVO, UserType.GUEST);
	}

	private boolean hasUserType(UserType userType) {
		if (userType == null) {
			return false;
		} else {
			return true;
		}
	}

	private boolean infoDetector(UserVO userVO)throws InvalidInputException, PasswordInputException, InvalidLengthInputException {
		GuestVO guestVO = (GuestVO) userVO;
		Detector detector = new Detector();
		boolean name = detector.infoDetector(guestVO.name);
		if (!name) {
			throw new InvalidInputException();
		}

		boolean password = detector.passwordDetector(guestVO.password);
		if (!password) {
			throw new PasswordInputException();
		}

		boolean phone = detector.phoneDetector(guestVO.phone);
		if (!phone) {
			throw new InvalidLengthInputException();
		}

		return true;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/11
	 * @param userID
	 *            当前客户端登录的用户编号
	 * 
	 *            TODO 根据后期确定检查重复登录
	 */
	public void logOut(String userID) {
		IDReserve.getInstance().setUserID("");
	}
	
	
	//TODO 查重,还未做
	private void guestLogInRecord(String guestID) {
		try {
			sourceDataService.guestLogInRecord(guestID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void guestLogOut(String guestID) {
		try {
			sourceDataService.guestLogOut(guestID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private boolean guestHasLogged(String guestID) {
		try {
			return sourceDataService.guestHasLogged(guestID);
		} catch (RemoteException e) {
			return false;
		}
	}
}
