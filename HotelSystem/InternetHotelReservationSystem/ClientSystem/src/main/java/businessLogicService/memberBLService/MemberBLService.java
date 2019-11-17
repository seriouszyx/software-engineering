package businessLogicService.memberBLService;

import exception.verificationException.UserInexistException;
import utilities.enums.MemberType;
import utilities.enums.ResultMessage;
import vo.MemberVO;

public interface MemberBLService {

	/**
	 * @Description:添加会员
	 * @param 会员信息载体
	 * @return 结果信息
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:12
	 */
	public ResultMessage add(MemberVO memberVO) throws UserInexistException;
	
	/**
	 * @Description:修改会员信息
	 * @param memberVO 会员信息载体
	 * @return 结果信息
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:14
	 */
	public ResultMessage modify(MemberVO memberVO) throws UserInexistException;
	
	/**
	 * @Description:获取会员信息
	 * @param userID 用户id
	 * @return 会员信息载体 
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:16
	 */
	public MemberVO getMemberInfo(String userID) throws UserInexistException;
	
	/**
	 * @Description:判断是否是会员
	 * @param userID 用户id
	 * @param memberType 会员类型
	 * @return boolean，用户是否存在
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:18
	 */
	public boolean isMember(String userID, MemberType memberType) throws UserInexistException;
	
	/**
	 * @Description:获取会员类型
	 * @param userID 用户id
	 * @return 用户类型
	 * @throws UserInexistException 用户不存在异常
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:21
	 */
	public MemberType getMemberType(String userID) throws UserInexistException;

}
