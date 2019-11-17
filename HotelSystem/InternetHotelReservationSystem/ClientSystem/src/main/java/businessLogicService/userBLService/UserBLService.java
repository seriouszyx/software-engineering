package businessLogicService.userBLService;

import java.util.List;

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
 * @Description：用户模块
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2017年1月2日 下午12:05:14
 */
public interface UserBLService {

	/**
	 * @Description：添加用户
	 * @param newUserVO 用户信息载体
	 * @param userType 用户类型
	 * @return 用户信息载体 
	 * @throws ParameterInvalidException 参数不合法异常
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:25
	 */
	public UserVO add(UserVO newUserVO ,UserType userType) throws ParameterInvalidException, UserInexistException;

	/**
	 * @Description:修改用户信息
	 * @param userVO 用户信息载体
	 * @return ResultMessage 结果信息
	 * @throws InvalidLengthInputException 不合法长度异常
	 * @throws InvalidInputException 输入不合法异常
	 * @throws PasswordInputException 密码错误异常
	 * @throws UpdateFaiedException 更新失败异常
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:27
	 */
	public ResultMessage modify(UserVO userVO) throws InvalidLengthInputException, InvalidInputException, PasswordInputException, UpdateFaiedException, UserInexistException;
	
	/**
	 * @Description:获取单个用户信息
	 * @param userID 用户id
	 * @return 用户信息载体
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:28
	 */
	public UserVO getSingle(String userID) throws UserInexistException;
	
	/**
	 * @Description: 添加酒店
	 * @param newHotelVO 酒店信息载体
	 * @return 酒店信息载体
	 * @throws UserInexistException 用户不存在异常 
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:31
	 */
	public HotelVO addHotel(HotelVO newHotelVO) throws UserInexistException;
	
	/**
	 * @Description:获取选定用户类型的所有用户信息
	 * @param userType 用户类型
	 * @return
	 * List<UserVO> 用户信息载体集合
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:33
	 */
	public List<UserVO> getAll(UserType userType);
	
	/**
	 * @Description:获取登录信息
	 * @param userID 用户id
	 * @param userType 用户类型
	 * @return 指定用户的登录信息
	 * @throws UserInexistException
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:35
	 */
	public String getLogInInfo(String userID, UserType userType) throws UserInexistException;
	
}
