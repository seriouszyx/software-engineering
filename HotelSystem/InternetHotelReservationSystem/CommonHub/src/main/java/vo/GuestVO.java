package vo;

import java.time.LocalDate;

import po.GuestPO;

public class GuestVO extends UserVO{
	
	//	会员生日
	public LocalDate birthday;
	
	//	会员企业名称
	public String enterprise;
	
	//	姓名
	public String name;	
	
	//	昵称
	public String nickName;
	
	//	联系方式
	public String phone;	
	
	//	信用值
	public double credit;
	
	public GuestVO(String guestID, LocalDate birthday, String enterprise, String name, String nickName,
			String password, String phone, double credit) {
		super(guestID,password);
		this.birthday = birthday;
		this.enterprise = enterprise;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.credit = credit;
	}
	
	public GuestVO(GuestPO guestPO) {
		super(guestPO.getGuestID(),guestPO.getPassword());
		this.birthday = guestPO.getBirthday();
		this.enterprise =  guestPO.getEnterprise();
		this.name =  guestPO.getName();
		this.nickName =  guestPO.getNickName();
		this.phone =  guestPO.getPhone();
		this.credit =  guestPO.getCredit();
	}
}