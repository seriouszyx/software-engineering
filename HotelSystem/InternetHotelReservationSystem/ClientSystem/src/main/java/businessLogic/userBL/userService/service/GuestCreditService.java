package businessLogic.userBL.userService.service;

import exception.verificationException.UserInexistException;
import utilities.enums.ResultMessage;

public interface GuestCreditService {
	
	public ResultMessage modifyCredit(String guestID, double creditNum) throws UserInexistException;
	
}
