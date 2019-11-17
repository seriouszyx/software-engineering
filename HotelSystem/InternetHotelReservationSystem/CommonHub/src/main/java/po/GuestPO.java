package po;

import java.io.Serializable;
import java.time.LocalDate;

import vo.GuestVO;

public class GuestPO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5391912504918714940L;

	//	用户编号	
	private String guestID;
	
	//	会员生日
	private LocalDate birthday;
	
	//	会员企业名称
	private String enterprise;
	
	//	姓名
	private String name;	
	
	//	昵称
	private String nickName;
	
	//	密码
	private String password;
	
	//	联系方式
	private String phone;	
	
	//	信用值
	private double credit;
	
	public GuestPO(){};
	
	public GuestPO(String guestID, LocalDate birthday, String enterprise, String name, String nickName,
			String password, String phone, double credit) {
		this.guestID = guestID;
		this.birthday = birthday;
		this.enterprise = enterprise;
		this.name = name;
		this.nickName = nickName;
		this.password = password;
		this.phone = phone;
		this.credit = credit;
	}
	
	public GuestPO(GuestVO guestVO){
		this.guestID = guestVO.userID;
		this.birthday = guestVO.birthday;
		this.enterprise = guestVO.enterprise;
		this.name = guestVO.name;
		this.nickName = guestVO.nickName;
		this.password = guestVO.password;
		this.phone = guestVO.phone;
		this.credit = guestVO.credit;
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
}
