package vo;

import java.time.LocalDate;

public class MemberVO {
	
	//	用户编号	
	public String guestID;
	
	//	会员生日
	public LocalDate birthday;
	
	//	会员企业名称
	public String enterprise;
	
	public MemberVO(String guestID, LocalDate birthday, String enterprise) {
		this.guestID = guestID;
		this.birthday = birthday;
		this.enterprise = enterprise;
	}
	
	public MemberVO(GuestVO guestVO) {
		this.guestID = guestVO.userID;
		this.birthday = guestVO.birthday;
		this.enterprise = guestVO.enterprise;
	}
}
