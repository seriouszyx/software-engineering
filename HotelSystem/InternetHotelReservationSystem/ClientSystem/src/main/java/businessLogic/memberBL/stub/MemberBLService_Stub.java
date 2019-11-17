package businessLogic.memberBL.stub;

import businessLogicService.memberBLService.MemberBLService;
import utilities.enums.MemberType;
import utilities.enums.ResultMessage;
import vo.MemberVO;

public class MemberBLService_Stub implements MemberBLService{

//	String guestID;
//	MemberType memberType;
//	String birthday;
//	String enterprise;
//	
//	public MemberBLService_Stub(String guestID, String birthday, String enterprise) {
//		this.guestID = guestID;
//		this.birthday = birthday;
//		this.enterprise = enterprise;
//	}
	
	public ResultMessage add(MemberVO memberVO) {
		return ResultMessage.SUCCESS;
	}

	public ResultMessage modify(MemberVO memberVO) {
		return null;
	}

	public MemberVO getMemberInfo(String userID) {
		return null;
	}

	public boolean isMember(String userID, MemberType memberType) {
		return false;
	}

	public MemberType getMemberType(String userID) {
		return null;
	}

}
