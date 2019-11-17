package businessLogic.promotionBL.discountCalculation.discountOfPromotions;

import businessLogic.memberBL.Member;
import businessLogic.promotionBL.discountCalculation.CalculateDiscount;
import exception.verificationException.UserInexistException;
import utilities.enums.MemberType;

public class EnterpriseMemberDiscount implements CalculateDiscount{

	private double discount;
	private String guestID;
	
	public EnterpriseMemberDiscount(double discount, String guestID) {
		this.discount = discount;
		this.guestID = guestID;
	}

	@Override
	public double getDiscount() throws UserInexistException {
		if(isEnterpriseMember()){
			return discount;
		}
		return 1;
	}
	
	public boolean isEnterpriseMember() throws UserInexistException{
		Member member = new Member();
		return member.isMember(guestID, MemberType.ENTERPRISE);
	}
}
