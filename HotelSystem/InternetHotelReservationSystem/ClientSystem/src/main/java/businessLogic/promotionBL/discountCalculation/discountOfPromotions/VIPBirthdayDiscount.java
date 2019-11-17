package businessLogic.promotionBL.discountCalculation.discountOfPromotions;

import java.time.LocalDate;

import businessLogic.memberBL.Member;
import businessLogic.memberBL.MockMember;
import businessLogic.promotionBL.discountCalculation.CalculateDiscount;
import exception.verificationException.UserInexistException;
import utilities.enums.MemberType;

public class VIPBirthdayDiscount implements CalculateDiscount{

	private LocalDate date;
	private LocalDate birthDay;
	private String guestID;
	private double discount;

	public VIPBirthdayDiscount(double discount, String guestID, LocalDate today) {
		this.discount = discount;
		this.guestID = guestID;
		this.date = today;
	}

	@Override
	public double getDiscount() throws UserInexistException {
		Member member = new Member();
//		Member member = new MockMember();
		if(member.isMember(guestID, MemberType.COMMON)){
			birthDay = member.getMemberInfo(guestID).birthday;
			if(date.isEqual(birthDay)){
				return discount; 
			}
		}
		return 1;
	}
}
