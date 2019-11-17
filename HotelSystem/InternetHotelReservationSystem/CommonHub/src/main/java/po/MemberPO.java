package po;

import java.io.Serializable;
import java.time.LocalDate;

import vo.MemberVO;

public class MemberPO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2878400778715065115L;

	//	用户编号	
	private String guestID;
	
	//	会员生日
	private LocalDate birthday;
	
	//	会员企业名称
	private String enterprise;
	
	public MemberPO(String guestID, LocalDate birthday, String enterprise) {
		super();
		this.guestID = guestID;
		this.birthday = birthday;
		this.enterprise = enterprise;
	}
	public MemberPO(MemberVO memberVO) {
		super();
		this.guestID = memberVO.guestID;
		this.birthday =memberVO.birthday;
		this.enterprise = memberVO.enterprise;
	}
	
	
	public String getGuestID() {
		return guestID;
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}	

}
