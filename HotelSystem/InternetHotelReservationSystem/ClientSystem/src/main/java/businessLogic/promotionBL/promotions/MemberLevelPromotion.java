package businessLogic.promotionBL.promotions;

import businessLogic.marketBL.Market;
import exception.verificationException.MemberInexistException;
import exception.verificationException.UserInexistException;

public class MemberLevelPromotion {

	public double getDiscount(String guestID) throws UserInexistException{
		Market market = new Market();
		try {
			return  market.getMemberDiscout(guestID);
		} catch (MemberInexistException e) {
			return 0;
		}
	}

}
