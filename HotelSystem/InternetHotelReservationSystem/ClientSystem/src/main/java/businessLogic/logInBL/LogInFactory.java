package businessLogic.logInBL;

import businessLogic.userBL.userService.Guest;
import businessLogic.userBL.userService.HotelWorker;
import businessLogic.userBL.userService.WebManager;
import businessLogic.userBL.userService.WebMarketer;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.SpecialCharacterException;
import utilities.Detector;
import utilities.enums.UserType;

public class LogInFactory {
	
	private Detector detector;
	
	public LogInFactory(){
		this.detector = new Detector();
	}
	
	
	public UserType getUserType(String userID) throws SpecialCharacterException, InvalidLengthInputException{
		
		try {
			if(detector.idDetector(userID, Guest.IDLength)){
				return UserType.GUEST;
			}
			
			if(detector.idDetector(userID, HotelWorker.IDLength)){
				return UserType.HOTEL_WORKER;
			}
			
			if(detector.idDetector(userID, WebMarketer.IDLength)){
				return UserType.WEB_MARKETER;
			}
			
			if(detector.idDetector(userID, WebManager.IDLength)){
				return UserType.WEB_MANAGER;
			}
		} catch (SpecialCharacterException e) {
			e.printStackTrace();
			throw new SpecialCharacterException();
		}		
		return null;
	}

}
