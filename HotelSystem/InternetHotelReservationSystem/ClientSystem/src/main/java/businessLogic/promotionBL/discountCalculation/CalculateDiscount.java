package businessLogic.promotionBL.discountCalculation;

import exception.verificationException.UserInexistException;

public interface CalculateDiscount {
	public double getDiscount() throws UserInexistException;
}
