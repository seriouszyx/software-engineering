package businessLogicService.logInBLService;

import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.inputException.SpecialCharacterException;
import exception.verificationException.UserInexistException;
import exception.verificationException.WrongPasswordException;
import utilities.enums.UserType;
import vo.GuestVO;
import vo.UserVO;

/**
 * @Description:登录模块
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2017年1月2日 上午11:46:31
 */
public interface LogInBLService {

	/**
	 * @Description： 用户登录
	 * @param userID 用户编号
	 * @param password 密码
	 * @return 用户类型
	 * @throws WrongPasswordException 错误密码异常
	 * @throws SpecialCharacterException 特殊符号异常
	 * @throws InvalidLengthInputException 
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:46:27
	 */
	public UserType logIn (String userID, String password) throws WrongPasswordException, SpecialCharacterException, InvalidLengthInputException, UserInexistException;
	
	/**
	 * @Description:客户注册
	 * @param guestVO 客户信息载体
	 * @return 客户信息载体
	 * @throws InvalidInputException 错误输入异常
	 * @throws PasswordInputException 密码输入异常
	 * @throws InvalidLengthInputException 错误长度异常
	 * @throws UserInexistException 用户不存在异常
	 * GuestVO
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:46:35
	 */
	public GuestVO guestSignUp (UserVO guestVO) throws InvalidInputException, PasswordInputException, InvalidLengthInputException, UserInexistException;
	
	
	/**
	 * @Description:登出
	 * @param 用户id
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:46:39
	 */
	public void logOut(String userID);
}
