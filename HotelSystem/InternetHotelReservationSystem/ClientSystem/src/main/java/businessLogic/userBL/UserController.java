package businessLogic.userBL;

import java.util.List;

import businessLogic.userBL.userService.Guest;
import businessLogic.userBL.userService.service.GuestCreditService;
import businessLogicService.userBLService.UserBLService;
import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.operationFailedException.UpdateFaiedException;
import exception.verificationException.ParameterInvalidException;
import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;
import utilities.enums.UserType;
import vo.HotelVO;
import vo.UserVO;

/**
 * 
 * @author Byron Dong
 * lastChangedBy Byron Dong
 * updateTime 2016/11/27
 *
 */
public class UserController implements UserBLService{

	
	private User user;
	private static UserController userController;
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	private UserController() {
		//new the mock object
		user = new User();
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param 
	 * @return 唯一userController对象
	 */
	public static UserController getInstance(){ //采用单例模式
		if(userController == null) userController = new UserController();
		return userController;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param userVO 从客户界面层传下来的userInfo载体
	 * @return ResultMessage 用户是否成功添加用户信息
	 * @throws UserInexistException 
	 * @throws ParameterInvalidException 
	 */
	public UserVO add(UserVO newUserVO , UserType userType) throws UserInexistException {
		return user.add(newUserVO , userType);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param userVO 从客户界面层传下来的userInfo载体
	 * @return ResultMessage 用户是否成功修改用户信息
	 * @throws UpdateFaiedException 
	 * @throws PasswordInputException 
	 * @throws InvalidInputException 
	 * @throws InvalidLengthInputException 
	 * @throws UserInexistException 
	 */
	public ResultMessage modify(UserVO userVO) throws InvalidLengthInputException, InvalidInputException, PasswordInputException, UpdateFaiedException, UserInexistException {
		return user.modify(userVO);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param userVO，userType 从客户界面层传下来的userInfo载体和指定用户类型
	 * @return UserVO 单一userInfo载体
	 * @throws UserInexistException 
	 */
	public UserVO getSingle(String userID) throws UserInexistException {
		return user.getSingle(userID);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/9
	 * @param newHotelVO 从客户界面层传下来的hotelInfo载体
	 * @return HotelVO 酒店info载体
	 * @throws UserInexistException 
	 */
	public HotelVO addHotel(HotelVO newHotelVO) throws UserInexistException {
		return user.addHotel(newHotelVO);
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/9
	 * @param guestID，creditNum 客户ID和需要修改的信用值
	 * @return ResultMessage 信用值是否添加成功
	 * @throws UserInexistException 
	 */
	public ResultMessage modifyCredit(String guestID, double creditNum) throws UserInexistException {
		GuestCreditService guest = new Guest(); 
		return guest.modifyCredit(guestID, creditNum);
	}


	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param  userType 从客户界面层传下来的指定用户类型
	 * @return List<UserVO> 指定用户类型的所有userInfo载体
	 */
	public List<UserVO> getAll(UserType userType) {
		return user.getAll(userType);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param  guestID, userType 从客户界面层传下来的指定用户ID和指定用户类型
	 * @return String 指定用户 的登录信息
	 * @throws UserInexistException 
	 */
	public String getLogInInfo(String userID,UserType userType) throws UserInexistException {
		return user.getLogInInfo(userID,userType);
	}
}
