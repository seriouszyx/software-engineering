package vo;

public class BasicInfoVO {

	//一些个人基本信息
	public GuestVO guestVO;
	
	//会员等级
	public String memberDegree;
	
	public BasicInfoVO(GuestVO guestVO, String memberDegree) {
		this.guestVO = guestVO;
		this.memberDegree = memberDegree;
	}
	
}
