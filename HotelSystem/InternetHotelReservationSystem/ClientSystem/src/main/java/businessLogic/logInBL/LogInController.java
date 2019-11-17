package businessLogic.logInBL;

import businessLogicService.logInBLService.LogInBLService;
import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.inputException.SpecialCharacterException;
import exception.verificationException.ParameterInvalidException;
import exception.verificationException.UserInexistException;
import exception.verificationException.WrongPasswordException;
import utilities.enums.UserType;
import vo.GuestVO;
import vo.UserVO;

public final class LogInController implements LogInBLService {

	private LogIn logIn;
	
	private static LogInController logInController = new LogInController();


	/**
	 * 构造函数，初始化成员变量
	 */
	private LogInController() {
		logIn = new LogIn();
	}
	
	/**
	 * @return login controller的实例，单例化
	 */
	public static LogInController getInstance() {
		return logInController;
	}
	
	
	/**
	 * @param guestVO
	 *           从注册界面层传下来的guestVO
	 * @return 客户是否成功注册
	 */
	public GuestVO guestSignUp(UserVO guestVO) throws InvalidInputException, PasswordInputException, InvalidLengthInputException, UserInexistException {
		return logIn.guestSignUp(guestVO);
	}

	/**
	 * @param userID
	 *           从登录界面层传下来的userID
	 * @param password
	 *           从登录界面层传下来的password
	 * @return 用户是否成功登录
	 */
	@Override
	public UserType logIn(String userID, String password) throws WrongPasswordException, SpecialCharacterException, InvalidLengthInputException, UserInexistException{
		return logIn.logIn(userID, password);
	}

	/**
	 * @param userID 当前客户端登录的用户编号 
	 * 
	 * TODO 根据后期确定检查重复登录
	 */
	@Override
	public void logOut(String userID) {
		logIn.logOut(userID);		
	}
}
