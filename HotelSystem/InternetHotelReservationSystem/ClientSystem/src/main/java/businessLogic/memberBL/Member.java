package businessLogic.memberBL;

import java.rmi.RemoteException;

import businessLogic.userBL.userService.Guest;
import businessLogicService.memberBLService.MemberBLService;
import dataService.guestDataService.GuestDataService;
import exception.verificationException.UserInexistException;
import po.MemberPO;
import rmi.ClientRemoteHelper;
import utilities.enums.MemberType;
import utilities.enums.ResultMessage;
import vo.GuestVO;
import vo.MemberVO;

/**
 * 
 * @author Byron Dong
 * lastChangedBy Byron Dong
 * updateTime 2016/11/27
 *
 */
public class Member implements MemberBLService{


	private GuestDataService guestDataService;

	private MemberInfo member;

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	public Member() {
		guestDataService = ClientRemoteHelper.getInstance().getGuestDataService();
//		try {
//			guestDataService = new GuestDataService_Stub();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		member = new MemberInfo();
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param memberVO 从客户界面层传下来的MemberInfo载体
	 * @return 客户是否成功添加会员信息
	 * @throws UserInexistException 
	 */
	public ResultMessage add(MemberVO memberVO) throws UserInexistException {
		return this.addMemberInfo(memberVO);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param memberVO 从客户界面层传下来的MemberInfo载体
	 * @return 客户是否成功修改会员信息
	 * @throws UserInexistException 
	 */
	public ResultMessage modify(MemberVO memberVO) throws UserInexistException {
		return addMemberInfo(memberVO);
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param userID，memberType 从客户界面层传下来的用户ID和需要获取指定会员类型信息
	 * @return memberVO MemberInfo载体
	 * @throws UserInexistException 
	 */
	public MemberVO getMemberInfo(String userID) throws UserInexistException {

		if(!this.isQualified(userID)){return null;}

		try {
			GuestVO guestVO = new GuestVO(guestDataService.getSingleGuest(userID));
			return new MemberVO(guestVO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param userID，memberType 从客户界面层传下来的用户ID和需要获取指定会员类型信息
	 * @return boolean 该用户是否为指定会员类型
	 * @throws UserInexistException 
	 */
	public boolean isMember(String userID, MemberType memberType) throws UserInexistException {
		try {
			GuestVO guestVO = new GuestVO(guestDataService.getSingleGuest(userID));
			MemberVO memberVO = new MemberVO(guestVO);
			if (member.isCommonMember(memberVO, memberType)) { // 是普通会员，返回true
				return true;
			}
			if (member.isEnterpriseMember(memberVO, memberType)) { // 是企业会员，返回true
				return true;
			}

			if (member.isBothMember(memberVO, memberType)) { // 判断是两种类型会员，返回true
				return true;
			}

			return false; // 不满足上述三种情况，返回false
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("error"); // 异常返回错误
			return false;
		}
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param userID 从客户界面层传下来的用户ID
	 * @return MemberType 指定用户的会员类型
	 * @throws UserInexistException 
	 */
	public MemberType getMemberType(String userID) throws UserInexistException {

		if(!this.isQualified(userID)){return null;}

		try {
			GuestVO guestVO = new GuestVO(guestDataService.getSingleGuest(userID));
			MemberVO memberVO = new MemberVO(guestVO);

			return member.getMemberType(memberVO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/11/27
	 * @param memberVO 来自本类的member信息载体 
	 * @return ResultMessage 添加会员信息是否成功
	 * @throws UserInexistException 
	 */
	private ResultMessage addMemberInfo(MemberVO memberVO) throws UserInexistException {

//		if(!this.isQualified(memberVO.guestID)){
//			return ResultMessage.FAIL;
//		}

		try {
			MemberPO memberPO = new MemberPO(memberVO);
			return guestDataService.modifyMember(memberPO);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}

	private boolean isQualified(String userID) throws UserInexistException{
		Guest tempGuest = new Guest();
		if(!tempGuest.isGuest(userID.length())){return false;}
		tempGuest.hasGuest(userID);
		return true;
	}
	
}
