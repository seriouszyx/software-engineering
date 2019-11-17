package businessLogic.memberBL;

import businessLogicService.memberBLService.MemberBLService;
import exception.verificationException.UserInexistException;
import utilities.enums.MemberType;
import utilities.enums.ResultMessage;
import vo.MemberVO;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/27
 *
 */
public class MemberController implements MemberBLService {

	
	private Member member;
	private static MemberController memberController;
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	private MemberController() {
		//new the mock object
		member = new Member();
	}
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param 
	 * @return 唯一memberController对象
	 */
	public static MemberController getInstance(){ //采用单粒模式
		if(memberController == null) memberController = new MemberController();
		return memberController;
	}
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param memberVO 从客户界面层传下来的memberInfo载体
	 * @return 用户是否成功添加会员信息
	 * @throws UserInexistException 
	 */
	public ResultMessage add(MemberVO memberVO) throws UserInexistException {
		return member.add(memberVO);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param memberVO 从客户界面层传下来的memberInfo载体
	 * @return 用户是否成功修改会员信息
	 * @throws UserInexistException 
	 */
	public ResultMessage modify(MemberVO memberVO) throws UserInexistException {
		return member.modify(memberVO);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param userID，memberType 从客户界面层传下来的用户ID和需要获取指定会员类型信息
	 * @return memberVO MemberInfo载体
	 * @throws UserInexistException 
	 */
	public MemberVO getMemberInfo(String userID) throws UserInexistException {
		return member.getMemberInfo(userID);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param userID，memberType 从客户界面层传下来的用户ID和需要获取指定会员类型信息
	 * @return boolean 该用户是否为指定会员类型
	 * @throws UserInexistException 
	 */
	public boolean isMember(String userID, MemberType memberType) throws UserInexistException {
		return member.isMember(userID, memberType);
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param userID 从客户界面层传下来的用户ID
	 * @return MemberType 指定用户的会员类型
	 * @throws UserInexistException 
	 */
	public MemberType getMemberType(String userID) throws UserInexistException {
		return member.getMemberType(userID);
	}
	
}
